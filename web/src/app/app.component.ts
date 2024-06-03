import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatExpansionModule } from '@angular/material/expansion';

import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { WebPushMessage, WebPushService } from './web-push.service';

const PREV_BACKEND_LOCAL_STORAGE = 'prevBackend';
const MSG_LOCAL_STORAGE = 'message';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    CommonModule,
    MatProgressSpinnerModule,
    MatExpansionModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  private ipFromStorage = localStorage.getItem(PREV_BACKEND_LOCAL_STORAGE);
  private messageFromStorage = localStorage.getItem(MSG_LOCAL_STORAGE);

  protected ip = new FormControl<string>( this.ipFromStorage ?? 'http://localhost:8080');
  protected message = new FormControl<string>(this.messageFromStorage ?? 'Hello world', Validators.required);

  protected pushRequested = false;

  constructor(
    protected webPushService: WebPushService
  ) { }

  ngOnInit(): void {
    // If there's both a stored IP and a stored pub key, first check if vapid key has changed.
    // If it hasn't, check to see if we already have a subscription and send that to the backend
    const ip = this.ipFromStorage
    const pubKey = this.webPushService.getPublicKey();
    if ( ip && pubKey) {
      this.webPushService.checkForExistingSubscription(ip, pubKey);
    }
  }

  protected subscribeClicked(): void {
    const ip = this.ip.value;
    if (ip) {
      localStorage.setItem(PREV_BACKEND_LOCAL_STORAGE, ip);
      this.webPushService.subscribe(ip);
    }
  }

  protected unsubscribeClicked(): void {
    this.webPushService.unsubscribe();
  }

  protected requestPushNotificationClicked() {
    if (this.message.value && this.ip.value) {
      localStorage.setItem(MSG_LOCAL_STORAGE, this.message.value);
      const message: WebPushMessage  = {
        urgency: 'NORMAL',
        title: this.message.value ?? ''
      };
      this.webPushService.requestPushNotification(this.ip.value, message).subscribe(
        () => this.pushRequested = true
      );;
    }
  }
}

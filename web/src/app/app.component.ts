import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { SwPush } from '@angular/service-worker';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

interface WebPushMessage {
  urgency: Urgency;
  message: string;
}

type Urgency = "VERY_LOW" | "LOW" | "NORMAL" | "HIGH";

const PUB_KEY_LOCAL_STORAGE = 'pubKeyLocal';
const PREV_BACKEND_LOCAL_STORAGE = 'prevBackend';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  ipFromStorage = localStorage.getItem(PREV_BACKEND_LOCAL_STORAGE);
  pubKeyFromStorage = localStorage.getItem(PUB_KEY_LOCAL_STORAGE);
  title = 'PwaNotificationTest';

  ip = new FormControl<string>( this.ipFromStorage ?? 'http://localhost:8080');
  message = new FormControl<string>('Hello world');

  pushRequested = false;
  subscription?: any;

  constructor(
    private http: HttpClient,
    private swPush: SwPush,
  ) { }

  ngOnInit(): void {
    // If there's both a stored IP and a stored pub key, first check if vapid key has changed.
    // If it hasn't, check to see if we already have a subscription and send that to the backend
    if (this.ipFromStorage && this.pubKeyFromStorage) {
      this.fetchVapidKey(this.ipFromStorage).subscribe(
        (fetchedKey) => {
          if (fetchedKey === this.pubKeyFromStorage) {
            this.swPush.subscription.subscribe({
              next: (sub) => {
                if (sub) {
                  this.notifyBackendOfSubsription(sub);
                }
              },
            });
          }
        }
      )
    }

  }

  subscribeClicked(): void {
    if (this.ip.value) {
      localStorage.setItem(PREV_BACKEND_LOCAL_STORAGE, this.ip.value);

      this.fetchVapidKey(`${this.ip.value}`).subscribe(
        (key) => {
          localStorage.setItem(PUB_KEY_LOCAL_STORAGE, key);

          if (key === undefined) {
            throw new Error('vapid public key was never set');
          }
          this.swPush.requestSubscription({
            serverPublicKey: key
          })
          .then(sub => this.notifyBackendOfSubsription(sub))
          .catch(err => console.error("Could not subscribe to notifications", err));
        });
      }
  }

  private notifyBackendOfSubsription(sub: PushSubscription) {
    this.addPushSubscriber(sub).subscribe(
      ()=> this.subscription = sub
    )
  }

  private fetchVapidKey(ip: string): Observable<string> {
    return this.http.get(`${ip}/api/vapid-public`, {responseType: 'text'});
  }

  unsubscribeClicked(): void {
	  this.swPush.unsubscribe();
    this.subscription = undefined;
  }

  addPushSubscriber(sub:PushSubscription) {
    return this.http.post(`${this.ip.value}/api/subscribe`, sub, {responseType: 'text'});
  }

  requestPushNotification() {
    const message: WebPushMessage  = {
      urgency: 'NORMAL',
      message: this.message.value ?? ''
    };
    return this.http.put(`${this.ip.value}/api/notify-all`, message).subscribe(
      () => this.pushRequested = true
    );
  }
}

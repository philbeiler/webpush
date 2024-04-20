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
  title = 'PwaNotificationTest';

  ip = new FormControl<string>('http://localhost:8080');
  message = new FormControl<string>('Hello world');

  vapidPublicKey?: string;
  pushRequested = false;
  subscription?: any;

  constructor(
    private http: HttpClient,
    private swPush: SwPush,
  ) {  }

  ngOnInit(): void {
    this.swPush.subscription.subscribe(
      (sub) => {
        this.subscription = sub;
      }
    )
  }

  subscribeClicked(): void {
    this.http.get(`${this.ip.value}/api/vapid-public`, {responseType: 'text'}).subscribe(
      (key) => {
        this.vapidPublicKey = key;
        if (this.vapidPublicKey === undefined) {
          throw new Error('vapid public key was never set');
        }
        this.swPush.requestSubscription({
          serverPublicKey: this.vapidPublicKey
        })
        .then(sub => this.addPushSubscriber(sub).subscribe(
          ()=> this.subscription = sub
        ))
        .catch(err => console.error("Could not subscribe to notifications", err)); 
      });
  }
  
  unsubscribeClicked(): void {
	  this.swPush.unsubscribe();
    this.subscription = undefined;
  }

  addPushSubscriber(sub:PushSubscription) {
    return this.http.post(`${this.ip.value}/api/subscribe`, sub);
  }

  requestPushNotification() {
    const message  = {
      "notification":{
        "title": this.message.value
      }
    };
      return this.http.put(`${this.ip.value}/api/notify-all`, message).subscribe(
        () => this.pushRequested = true
      );
  }
}

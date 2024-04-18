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
  vapidPublicKey?: string;
  subscribed = false;
  pushRequested = false;

  constructor(
    private http: HttpClient,
    private swPush: SwPush,
  ) {  }

  ngOnInit(): void {
    
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
          ()=> this.subscribed = true
        ))
        .catch(err => console.error("Could not subscribe to notifications", err)); 
          }
        );
  }

  addPushSubscriber(sub:PushSubscription) {
    return this.http.post(`${this.ip.value}/api/subscribe`, sub);
  }

  requestPushNotification() {
    const message  = {
      "notification":{
        "title": "hello world"
      }
    };
      return this.http.put(`${this.ip.value}/api/notify-all`, message).subscribe(
        () => this.pushRequested = true
      );
  }
}

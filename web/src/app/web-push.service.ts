import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SwPush } from '@angular/service-worker';
import { Observable } from 'rxjs';

const PUB_KEY_LOCAL_STORAGE = 'pubKeyLocal';

type OnActionClickOperation = 'openWindow' |
                              'focusLastFocusedOrOpen' |
                              'navigateLastFocusedOrOpen' |
                              'sendRequest'

export interface WebPushMessage {
  urgency: Urgency;
  title: string;
  body?: string;
  iconURI?: string;
  imageURI?: string;
  requireInteraction?: string;
  onActionClickOperation?: OnActionClickOperation;
  onActionClickURI?: string;
}

type Urgency = "VERY_LOW" | "LOW" | "NORMAL" | "HIGH";

@Injectable({
  providedIn: 'root'
})
export class WebPushService {
  private subscription?: PushSubscription;
  private pubKey = localStorage.getItem(PUB_KEY_LOCAL_STORAGE);

  constructor(
    private http: HttpClient,
    private swPush: SwPush,
  ) { }

  checkForExistingSubscription(ip: string, pubKey: string) {
    this.fetchVapidKey(ip).subscribe(
      (fetchedKey) => {
        if (fetchedKey === pubKey) {
          this.swPush.subscription.subscribe({
            next: (sub) => {
              if (sub) {
                this.notifyBackendOfSubscription(ip, sub);
              }
            },
          });
        }
      }
    );
  }

  private fetchVapidKey(ip: string): Observable<string> {
    return this.http.get(`${ip}/api/vapid-public`, {responseType: 'text'});
  }

  private notifyBackendOfSubscription(ip: string, sub: PushSubscription) {
    this.addPushSubscriber(ip, sub).subscribe(
      () => this.subscription = sub
    );
  }

  private addPushSubscriber(ip: string, sub: PushSubscription): Observable<string> {
    return this.http.post(`${ip}/api/subscribe`, sub, {responseType: 'text'});
  }

  subscribe(ip: string) {
    this.fetchVapidKey(ip).subscribe(
      (vapidKey) => {
        localStorage.setItem(PUB_KEY_LOCAL_STORAGE, vapidKey);

        if (vapidKey === undefined) {
          throw new Error('vapid public key was never set');
        }
        this.requestSubscription(ip, vapidKey);
      });
  }

  private requestSubscription(ip: string, vapidKey: string) {
    this.swPush.requestSubscription({
      serverPublicKey: vapidKey
    })
    .then(sub => this.notifyBackendOfSubscription(ip, sub))
    .catch(err => console.error("Could not subscribe to notifications", err));
  }

  unsubscribe(): void {
	  this.swPush.unsubscribe();
    this.subscription = undefined;
  }

  requestPushNotification(ip: string, message: WebPushMessage): Observable<unknown> {
    return this.http.put(`{ip}/api/notify-all`, message);//
  }

  isSubscribed(): boolean {
    return !!this.subscription;
  }

  getPublicKey(): string | null {
    return this.pubKey;
  }
}

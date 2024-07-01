To run, you must have node 20 installed. Get the latest version here: https://nodejs.org/en/download

After that's installed, in the web directory, run `npm install -g @angular/cli`.

Then run `npm ci`.

To run the Angular web server, run `ng serve --configuration=production --prebundle=true --host 0.0.0.0`

Note that you do NOT want to do this in a production context, but for development, it's probably fine until a more appropriate server can be set up.

To test Push Notifications, open chrome DevTools, go to Application tab. Then paste the following in the Push text box and click the Push button:

```
{
  "notification": {
    "title": "🔴Notification Title (with emoji)",
    "body": "Notification body (also with emoji) 🔴",
    "icon": "/assets/icons/icon-128x128.png",
    "data": {
      "onActionClick": {
        "default": {"operation": "openWindow", "url": "foo"}
      }
    }
  }
}
```

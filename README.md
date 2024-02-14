## Firebase Cloud Messaging Push Notifications
### This application requires [running a server](https://github.com/NORMss/FcmPushNotificationsServer) to process sent messages
### If there is an error `java.net.SocketTimeoutException: failed to connect to /10.0.2.2 (port 8080) from /<ip address of your device> (port 48146) after 10000ms` try changing the server address in the [ChatViewModel](https://github.com/NORMss/FcmPushNotifications/blob/master/app/src/main/java/com/norm/myfcmpushnotifications/ChatViewModel.kt) file and setting this address to the server in the [Application](https://github.com/NORMss/FcmPushNotificationsServer/blob/master/src/main/kotlin/com/plcoding/Application.kt) file
### I managed to fix the error when using my router's address

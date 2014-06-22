*This is simple application on android which accesses the REST apis created through loopback server application.
*Setting up the eclipse to run this application:
*There are two folder in this application.
*One is appcompat_v7 and other one is Loopbackandroid.
*First one is a library for compatibility.
*Import both the project into eclipse workspace. Set the appcompat_v7 as library for Loopbackandroid.
*download loopback-android sdk 1.3 and extract it
* now copy the contents of libs folder to libs folder in application project in eclipse.
*Now start the loopback-server application and mongodb.
*Change the ip address and port in LoopandroidApplication.java for restadapter. 
*I used my pc's ip. and the port on which my loopback-server application is running. 
	and now launch the android application on emulator or phone.
*The first screen that will open is a form to add a new user and with a button to see existing users.
*screenshots of the app are also present is screenshots folder.
*After you enter all the details and press create user button, new user will be added and the list of all the users will be opened.
*When you press existing users button, the list of already existing users will be shown.
*The model i have used in userdetails which i have created in my loopback-server application which you can find on the below link .
https://github.com/nitinsmarty/loopback-server-htmlclient


# Overview

This project was began as an attempt to learn more about networking, namely how to create a network program and send
data over said network. 

This program creates a Client Server that can chat with a client once the client connects. I used a TCP server because 
of the importance of maintaining data coherence in a chat. To start the program, initialize the server. A window will
pop up, but be blank. Next, start the ClientGUI module, and you will see the server window populate, as well as a new 
window pop up for Client Chat. You can now send messages back and forth between the client and server windows. The
IP Address and Port are hard coded currently. If your port 8192 is utilized, you will need to change that port in both
.java files, make sure they match.

In learning how to create this chat program, I was able to learn and demonstrate basic knowledge of 
creating a network, connecting to said network, and transferring data between a client and a server, as well as between 
the server and the client. I added to the functionality by creating a Graphical User Interface. This allows for a much 
more accessible user experience.

[Software Demo Video](https://youtu.be/qkXYnVS-4D8)

# Development Environment

I used IntelliJ to develop this project. I imported the following libraries:
* javax.swing
* AWT (Abstract Window Toolkit)
* IO Input/Output API
* Package net
* Package util

The imported libraries allowed me to create windows that should be similar and functional across Operating Systems 
(javax.swing), make and manipulate a GUI (AWT), send and receive Input and Output from users (IO), create and connect to
sockets (Package net) for a TCP connection, and scan the program for user inputs and outputs (Package util) which are 
stored as strings and sent back and forth.

# Useful Websites

* [YouTube.com The Cherno](https://www.youtube.com/watch?v=dHHu2zmXeuM&list=PLlrATfBNZ98cCvNtS7x4u1bZOS5FBwOSb&index=1)
* [Tutorialspoint.com](https://www.tutorialspoint.com/awt/awt_window_event.htm)
* [docs.oracle.com](https://docs.oracle.com/javase/7/docs/api/java/awt/event/WindowEvent.html)
* [cloudns.net](https://www.cloudns.net/blog/tcp-transmission-control-protocol-what-is-it-and-how-does-it-work/)

# Future Work
While the basic functionality of the Client Server Chat exists, I would love to make it more functional.

* Add Username option
* Add multiple client connection options
* Make port and IP Address assignment Dynamic
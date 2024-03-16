NSD Assignment Server
Project Overview
This project implements a prototype of a Twitter-like social media application tailored for the Sheffield Hallam University student community. Utilizing client-server architecture and network software development techniques, it allows clients to publish messages, subscribe to channels, and read messages from channels they've subscribed to. This server supports multiple concurrent client connections, message persistence, and multimedia attachments.

Project Structure
/src: Source code directory containing all Java classes organized into packages:
/assignment: Base package.
/drew: Contains subpackages for different components of the application.
/client: Single user input client.
/server: Server files and tester client.
/utils: Utility classes.
/tester: Auto tester client.
/enums: Enums used within the application.
/returnmessages: Return messages definitions.
/out: Compilation output directory.
Extensions
Searching Functionality: Enables searching for messages within a channel a user is subscribed to, displaying all relevant messages and rendering images if attached.
Compilation Instructions
Server Compilation:

bash
Copy code
javac -d out/production/Drew-NSD-Assignment -cp json-20201115.jar;sqlite-jdbc-3.32.3.2.jar src/assignment/drew/server/*.java src/assignment/drew/utils/enums/*.java src/assignment/drew/utils/returnmessages/*.java src/assignment/drew/utils/sql/*.java src/assignment/drew/client/*.java src/assignment/drew/server/tester/*.java
Server Execution:

bash
Copy code
java -cp "out\production\Drew-NSD-Assigment;json-20201115.jar;sqlite-jdbc-3.32.3.2.jar" assignment.drew.server.RunServer
Client Compilation for GUI:

vbnet
Copy code
javac -d out/production/NSD_assisngment_2 --module-path C:/Users/Adam/Desktop/Coding/java/NSD_assisngment_2/fx/lib --add-modules=javafx.controls -classpath :out/production/NSD_assisngment_2;json-20201115.jar;sqlite-jdbc-3.32.3.2.jar;fx\lib\src.zip;fx\lib\javafx-swt.jar;fx\lib\javafx.web.jar;fx\lib\javafx.base.jar;fx\lib\javafx.fxml.jar;fx\lib\javafx.media.jar;fx\lib\javafx.swing.jar;fx\lib\javafx.controls.jar;fx\lib\javafx.graphics.jar src/assignment/drew/client/gui/*.java src/assignment/drew/utils/Utils.java src/assignment/drew/utils/enums/*.java
Single Client Instance Execution:

vbnet
Copy code
java --module-path C:/Users/Adam/Desktop/Coding/java/NSD_assisngment_2/fx/lib --add-module
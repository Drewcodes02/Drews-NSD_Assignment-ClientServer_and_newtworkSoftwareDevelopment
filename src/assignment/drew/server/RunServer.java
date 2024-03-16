package Assigment.Server;

import Assigment.commands.SQLExecutor;

import java.io.IOException;

public class RunServer {

    public static void main(String[] args) throws IOException {
        SQLExecutor.createDatabase();
        Server server = new Server();
        server.getSocket();

    }

}

package com.dtu.security.prototype2.client;

import com.dtu.security.prototype2.server.ServerIF;
import com.dtu.security.prototype2.server.authentication.Ticket;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {


    public static void main(String args[]) {

        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServerIF server = (ServerIF) registry.lookup(name);
            Ticket ticket = server.authenticateUser("erica", "password5");

            server.start(ticket);
            server.print("test", "test", ticket);
            server.setConfig("test", "test", ticket);
            server.queue("test", ticket);
            server.stop(ticket);

        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }

}

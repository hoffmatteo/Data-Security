package com.dtu.security.client;

import com.dtu.security.server.ServerIF;
import com.dtu.security.server.authentication.Ticket;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {


    public static void main(String args[]) {

        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServerIF server = (ServerIF) registry.lookup(name);
            Ticket ticket = server.authenticateUser("matteoh", "test2");

            server.start(ticket);

            server.print("test", "test", ticket);

            Thread.sleep(30000);


            server.stop(ticket);

        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }

}

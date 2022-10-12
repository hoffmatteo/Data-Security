package com.dtu.security.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements ServerIF {

    public Server() {
        super();
    }

    public static void main(String[] args) {

        try {
            String name = "Compute";
            //create new Server object
            ServerIF engine = new Server();
            //create stub out of Server
            ServerIF stub =
                    (ServerIF) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.createRegistry(1099);

            //upload stub to Registry
            registry.bind(name, stub);
            System.out.println("Server bound");
        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }

    public <T> T executeTask(TaskIF<T> t) {
        System.out.println("anders");
        return t.execute();
    }
}

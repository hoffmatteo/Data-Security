package com.dtu.security.server;

import java.rmi.RemoteException;
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


    @Override
    public void topQueue(String printer, int job) throws RemoteException {
        System.out.println("topQueue");
    }

    @Override
    public void start() throws RemoteException {
        System.out.println("start");
    }

    @Override
    public void stop() throws RemoteException {
        System.out.println("stop");
    }

    @Override
    public void restart() throws RemoteException {
        System.out.println("restart");
    }

    @Override
    public void status(String printer) throws RemoteException {
        System.out.println("status");
    }

    @Override
    public void readConfig(String parameter) throws RemoteException {
        System.out.println("readConfig");
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {
        System.out.println("setConfig");
    }

    @Override
    public void queue(String printer) throws RemoteException {
        System.out.println("queue");
    }

    @Override
    public void print(String filename, String printer) throws RemoteException {
        System.out.println("print");
    }
}

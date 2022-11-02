package com.dtu.security.server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;

public class Server implements ServerIF {
    //TODO null check

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
            /*
            System.out.println(Authentication.hashPassword("test1", "andersh"));
            System.out.println(Authentication.hashPassword("test2", "matteoh"));
            System.out.println(Authentication.hashPassword("test3", "thomasg"));

             */


        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }

    @Override
    public void topQueue(String printer, int job, Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (ticket.isActive()) {
                System.out.println("topQueue");
            } else {
                System.out.println("error");
            }
        }
    }

    @Override
    public void start(Ticket ticket) throws RemoteException {
        if (Authentication.checkTicket(ticket)) {
            System.out.println("start");
        } else {
            System.out.println("error");
        }
    }

    @Override
    public void stop(Ticket ticket) throws RemoteException {
        if (Authentication.checkTicket(ticket)) {
            System.out.println("stop");
        } else {
            System.out.println("error");
        }

    }

    @Override
    public void restart(Ticket ticket) throws RemoteException {
        if (Authentication.checkTicket(ticket)) {
            System.out.println("restart");
        } else {
            System.out.println("error");
        }
    }

    @Override
    public void status(String printer, Ticket ticket) throws RemoteException {
        if (Authentication.checkTicket(ticket)) {
            System.out.println("status");
        } else {
            System.out.println("error");
        }
    }

    @Override
    public void readConfig(String parameter, Ticket ticket) throws RemoteException {
        if (Authentication.checkTicket(ticket)) {
            System.out.println("readConfig");
        } else {
            System.out.println("error");
        }
    }

    @Override
    public void setConfig(String parameter, String value, Ticket ticket) throws RemoteException {
        if (Authentication.checkTicket(ticket)) {
            System.out.println("setConfig");
        } else {
            System.out.println("error");
        }
    }

    @Override
    public void queue(String printer, Ticket ticket) throws RemoteException {
        if (Authentication.checkTicket(ticket)) {
            System.out.println("queue");
        } else {
            System.out.println("error");
        }
    }

    @Override
    public void print(String filename, String printer, Ticket ticket) throws RemoteException {
        if (Authentication.checkTicket(ticket)) {
            System.out.println("print");
        } else {
            System.out.println("error");
        }
    }

    @Override
    public Ticket authenticateUser(String username, String password) throws RemoteException, FileNotFoundException, NoSuchAlgorithmException {
        Ticket ticket = Authentication.verifyUser(username, password);
        if (ticket != null) {
            System.out.println("user successfully authenticated!");
            return ticket;
        } else {
            System.out.println("user could not be authenticated");
            return null;
        }
    }
}

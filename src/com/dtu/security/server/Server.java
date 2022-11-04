package com.dtu.security.server;

import com.dtu.security.server.authentication.Authentication;
import com.dtu.security.server.authentication.Ticket;

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
            if (Authentication.checkTicket(ticket)) {
                System.out.println("topQueue");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");
    }

    @Override
    public void start(Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (Authentication.checkTicket(ticket)) {
                System.out.println("start");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");


    }

    @Override
    public void stop(Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (Authentication.checkTicket(ticket)) {
                System.out.println("stop");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");


    }

    @Override
    public void restart(Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (Authentication.checkTicket(ticket)) {
                System.out.println("restart");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");
    }

    @Override
    public void status(String printer, Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (Authentication.checkTicket(ticket)) {
                System.out.println("status");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");
    }

    @Override
    public void readConfig(String parameter, Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (Authentication.checkTicket(ticket)) {
                System.out.println("readConfig");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");
    }

    @Override
    public void setConfig(String parameter, String value, Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (Authentication.checkTicket(ticket)) {
                System.out.println("setConfig");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");
    }

    @Override
    public void queue(String printer, Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (Authentication.checkTicket(ticket)) {
                System.out.println("queue");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");
    }

    @Override
    public void print(String filename, String printer, Ticket ticket) throws RemoteException {
        if (ticket != null) {
            if (Authentication.checkTicket(ticket)) {
                System.out.println("print");
                return;
            }
        }
        System.out.println("ERROR: ticket not valid");
    }

    @Override
    public Ticket authenticateUser(String username, String password) throws RemoteException, FileNotFoundException, NoSuchAlgorithmException {
        Ticket ticket = Authentication.verifyUser(username, password);
        if (ticket != null) {
            System.out.println("User successfully authenticated!");
            return ticket;
        } else {
            System.out.println("ERROR: User could not be authenticated");
            return null;
        }
    }
}

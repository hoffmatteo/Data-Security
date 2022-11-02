package com.dtu.security.server;

import com.dtu.security.server.authentication.Ticket;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

public interface ServerIF extends Remote {
    void topQueue(String printer, int job, Ticket ticket) throws RemoteException;   // moves job to the top of the queue

    void start(Ticket ticket) throws RemoteException;   // starts the print server

    void stop(Ticket ticket) throws RemoteException;   // stops the print server

    void restart(Ticket ticket) throws RemoteException;   // stops the print server, clears the print queue and starts the print server again

    void status(String printer, Ticket ticket) throws RemoteException;   // prints status of printer on the user's display

    void readConfig(String parameter, Ticket ticket) throws RemoteException;    // prints the value of the parameter on the user's display

    void setConfig(String parameter, String value, Ticket ticket) throws RemoteException;    // sets the parameter to value

    void queue(String printer, Ticket ticket) throws RemoteException;    // lists the print queue for a given printer on the user's display in lines of the form <job number>   <file name>

    void print(String filename, String printer, Ticket ticket) throws RemoteException;    // prints file filename on the specified printer

    Ticket authenticateUser(String username, String password) throws RemoteException, FileNotFoundException, NoSuchAlgorithmException;
}



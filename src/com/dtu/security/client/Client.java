package com.dtu.security.client;

import com.dtu.security.server.ServerIF;
import com.dtu.security.server.TaskIF;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {


    public static void main(String args[]) {

        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServerIF comp = (ServerIF) registry.lookup(name);
            TaskIF<String> task = new ClientTask();
            String pi = comp.executeTask(task);
            System.out.println(pi);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }

}

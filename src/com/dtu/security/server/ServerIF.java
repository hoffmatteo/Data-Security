package com.dtu.security.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
    <T> T executeTask(TaskIF<T> t) throws RemoteException;
}



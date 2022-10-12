package com.dtu.security.client;

import com.dtu.security.server.TaskIF;

import java.io.Serializable;

public class ClientTask implements TaskIF<String>, Serializable {

    @Override
    public String execute() {
        return "hello";
    }
}

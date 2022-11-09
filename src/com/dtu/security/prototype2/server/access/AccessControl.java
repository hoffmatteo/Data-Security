package com.dtu.security.prototype2.server.access;

import com.dtu.security.prototype1.server.access.Permissions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class AccessControl {
    private final String acl = System.getProperty("user.dir") + "/src/com/dtu/security/server/file/acl.csv";
    private HashMap<String, com.dtu.security.prototype1.server.access.Permissions> userTable = new HashMap<>();

    public AccessControl() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(acl));

        reader.lines().forEach(line -> {
            String[] components = line.split(",");
            if (components.length == 10) {
                userTable.put(components[0], new com.dtu.security.prototype1.server.access.Permissions(components));
            }
        });
    }

    public boolean checkAccess(String username, Operations operation) {
        if (userTable.containsKey(username)) {
            Permissions permissions = userTable.get(username);
            if (permissions.permissionsTable.containsKey(operation)) {
                return permissions.permissionsTable.get(operation);
            }
        }
        return false;

    }


    public enum Operations {
        print, queue, topQueue, start, stop, restart, status, readConfig, setConfig
    }


}

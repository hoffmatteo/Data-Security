package com.dtu.security.prototype2.server.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Role {
    private final static List<Operations> operationsList = new ArrayList<>(List.of(Operations.print,
            Operations.queue,
            Operations.topQueue,
            Operations.start,
            Operations.stop,
            Operations.restart,
            Operations.status,
            Operations.readConfig,
            Operations.setConfig
    ));

    public String inheritanceString;

    //username,print,queue,topQueue,start,stop,restart,status,readConfig,setConfig
    public String roleName;
    public HashMap<Operations, Boolean> permissionsTable = new HashMap<>();


    public Role(String[] components) {
        if (components.length == 11) {
            this.roleName = components[0];
            this.inheritanceString = components[1];
            for (int i = 2; i < components.length; i++) {
                if (Objects.equals(components[i], "1")) {
                    permissionsTable.put(operationsList.get(i - 2), true);
                }
            }
        }
    }

    public Role(String combinedRole) {
        this.roleName = combinedRole;
    }

    public void inheritFromRole(Role inherit) {
        if (inherit == null) {
            return;
        }
        inherit.permissionsTable.forEach((key, value) -> {
            if (value)
                permissionsTable.put(key, true);
        });
    }
}



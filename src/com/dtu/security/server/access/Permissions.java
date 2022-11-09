package com.dtu.security.server.access;

import java.util.HashMap;
import java.util.Objects;

public class Permissions {
    //username,print,queue,topQueue,start,stop,restart,status,readConfig,setConfig

    public HashMap<AccessControl.Operations, Boolean> permissionsTable = new HashMap<>();


    public Permissions(String[] components) {
        if (components.length == 10) {
            if (Objects.equals(components[1], "1")) permissionsTable.put(AccessControl.Operations.print, true);
            if (Objects.equals(components[2], "1")) permissionsTable.put(AccessControl.Operations.queue, true);
            if (Objects.equals(components[3], "1")) permissionsTable.put(AccessControl.Operations.topQueue, true);
            if (Objects.equals(components[4], "1")) permissionsTable.put(AccessControl.Operations.start, true);
            if (Objects.equals(components[5], "1")) permissionsTable.put(AccessControl.Operations.stop, true);
            if (Objects.equals(components[6], "1")) permissionsTable.put(AccessControl.Operations.restart, true);
            if (Objects.equals(components[7], "1")) permissionsTable.put(AccessControl.Operations.status, true);
            if (Objects.equals(components[8], "1")) permissionsTable.put(AccessControl.Operations.readConfig, true);
            if (Objects.equals(components[9], "1")) permissionsTable.put(AccessControl.Operations.setConfig, true);
        }
    }
}



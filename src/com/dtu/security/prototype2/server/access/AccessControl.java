package com.dtu.security.prototype2.server.access;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class AccessControl {
    private final String rbac = System.getProperty("user.dir") + "/src/com/dtu/security/prototype2/server/file/rbac.csv";
    private final String rbac_hierarchy = System.getProperty("user.dir") + "/src/com/dtu/security/prototype2/server/file/rbac_hierarchy.csv";
    private final HashMap<String, Role> userTable = new HashMap<>();
    private final HashMap<String, Role> roleTable = new HashMap<>();

    public AccessControl() throws FileNotFoundException {
        readRoles();
        readUsers();
    }

    private void readRoles() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(rbac_hierarchy));

        List<String> lines = reader.lines().toList();
        for (int i = 0; i < lines.size() - 1; i++) {
            String line = lines.get(i);
            String[] components = line.split(",");
            if (components.length == 11) {
                Role role = new Role(components);
                List<String> roles = List.of(components[1].split("\\+"));
                roles.forEach(inheritanceRole -> role.inheritFromRole(roleTable.get(inheritanceRole)));
                roleTable.put(components[0], role);
            }
        }
    }

    private void readUsers() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(rbac));
        reader.lines().forEach(line -> {
            String[] components = line.split(",");
            if (components.length == 2) {
                List<String> roles = List.of(components[1].split("\\+"));
                Role finalRole = new Role(components[1]);
                roles.forEach(role -> {
                    if (roleTable.containsKey(role)) {
                        finalRole.inheritFromRole(roleTable.get(role));
                    }
                });
                userTable.put(components[0], finalRole);
            }
        });
    }

    public boolean checkAccess(String username, Operations operation) {
        if (userTable.containsKey(username)) {
            Role permissions = userTable.get(username);
            if (permissions.permissionsTable.containsKey(operation)) {
                return permissions.permissionsTable.get(operation);
            }
        }
        return false;

    }


}

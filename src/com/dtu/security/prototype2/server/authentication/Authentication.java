package com.dtu.security.prototype2.server.authentication;

import com.dtu.security.prototype1.server.authentication.Ticket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;

public class Authentication {
    private static final String file_path = "/src/com/dtu/security/server/file/pswd.txt";

    private static HashMap<String, com.dtu.security.prototype1.server.authentication.Ticket> tickets = new HashMap<>();

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static com.dtu.security.prototype1.server.authentication.Ticket verifyUser(String username, String password) throws NoSuchAlgorithmException, FileNotFoundException {
        String hashedPassword = hashPassword(password, username);


        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + file_path));

        HashMap<String, String> publicFile = new HashMap<>();

        reader.lines().forEach(line -> {
            String[] components = line.split(",");
            if (components.length == 2) {
                publicFile.put(components[0], components[1]);
            }
        });

        if (publicFile.containsKey(username)) {
            if (Objects.equals(publicFile.get(username), hashedPassword)) {
                return createTicket(username);
            }
        }

        return null;

    }

    //https://www.baeldung.com/sha-256-hashing-java
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] encodedhash = digest.digest(
                (password + salt).getBytes(StandardCharsets.UTF_8));
        //String returnString = bytesToHex(encodedhash);
        //System.out.println(returnString);
        return bytesToHex(encodedhash);
    }

    public static com.dtu.security.prototype1.server.authentication.Ticket createTicket(String username) {
        com.dtu.security.prototype1.server.authentication.Ticket t = new com.dtu.security.prototype1.server.authentication.Ticket(System.currentTimeMillis(), username);
        tickets.put(username, t);
        return t;
    }

    public static boolean checkTicket(com.dtu.security.prototype1.server.authentication.Ticket t) {
        if (tickets.containsKey(t.getUsername())) {
            Ticket serverTicket = tickets.get(t.getUsername());
            return serverTicket.equalTicket(t) && serverTicket.isActive();
        }
        return false;

    }

}

package org.example;

import org.planner.session.Session;

public class Main {
    public static void main(String[] args) throws Exception {
        String credentials = args[0];
        new Session(credentials).run();
    }
}
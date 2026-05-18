package com.chronostore;

public class Main {
    public static void main(String[] args) throws Exception {
        Store store = new Store();
        Server server = new Server(6379, store);
        server.start();
    }
}

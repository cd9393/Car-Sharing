package com.company;

public class Main {

    public static void main(String[] args) {
	    com.company.DatabaseRepository db = com.company.DatabaseRepository.getInstance(args[1]);
        db.createDatabase();
    }
}

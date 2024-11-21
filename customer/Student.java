package customer;

import product.Media;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

// Copyright 2024 by Alvin Tran
// This program is free software: you can redistribute it and/or modify it under the terms 
// of the GNU General Public License as published by the Free Software Foundation,
// either version 3 of the License, or (at your option) any later version.

/**
 * Models the students information
 *
 * @author             Alvin Tran
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */

public class Student
{   

    /**
    * Creates a Student instance.
    *
    * @param name       The name of the student
    * @param id         The id of the student
    * @param email      The email of the question
    * @param unlimited  Decides what type of account the student has
    * 
    * @since 1.0
    */

    public Student(String name, int id, String email, boolean unlimited)
    {
        if(!(email.endsWith("@uta.edu") || email.endsWith("@mavs.uta.edu")))
            throw new  IllegalArgumentException("Non-UTA email address: " + email);
    
        this.name = name;
        this.id = id;
        this.email = email;
        this.account = unlimited ? new Unlimited() : new Alacarte();
    }

    public Student(BufferedReader br) throws IOException
    {
        this.name = br.readLine();
        this.id = Integer.parseInt(br.readLine());
        this.email = br.readLine();
        String accountName = br.readLine();
        this.account = switch(accountName)
        {
            case "customer.Unlimited" -> new Unlimited(br);
            case "customer.Alacarte" -> new Alacarte(br);
            default -> throw new IllegalArgumentException("Unknown account type: " + accountName);
        }; 
    }

    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
        bw.write("" + id + '\n');
        bw.write(email + '\n');
        bw.write(account.getClass().getName() + '\n');
        account.save(bw);
    }

    /**
    * Returns the requested media the customer chooses as a String
    *
    * @param media Indecates the type of media that will be played
    * 
    * @return the string value of media
    * 
    * @see Account
    * @see Media
    *  
    * @since 1.0
    */

    public String requestMedia(Media media)
    {
        return account.play(media);
    }

    /**
    * Returns the account information
    *
    * @return the account information
    * 
    * @see Account
    *  
    * @since 1.0
    */

    public Account getAccount()
    {
        return account;
    }

    /**
    * Returns the string representation of the Students information
    *
    * @return the string representation of the students 
    *           name, id, email, and account number
    * 
    * @see Account
    *  
    * @since 1.0
    */
    @Override
    public String toString()
    {
        return name + " (" + id + ", " + email + ", " + "Account #" + account.getAccountNumber() + ")";
    }

    private String name;
    private int id;
    private String email;
    private Account account;
}

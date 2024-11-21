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
 * Models the accounts created for Moes
 *
 * @author             Alvin Tran
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */

public abstract class Account
{
    /**
    * Creates an accounts instance
    *  
    * @since 1.0
    */
    public Account()
    {
        this.accountNumber = nextAccountNumber;
        nextAccountNumber++;
    }

    public Account(BufferedReader br) throws IOException
    {
        nextAccountNumber = Integer.parseInt(br.readLine());
        this.accountNumber = Integer.parseInt(br.readLine());
    }

    public void save(BufferedWriter bw) throws IOException
    {
        bw.write("" + nextAccountNumber + '\n'); 
        bw.write("" + accountNumber + '\n');
    }

    /**
    * Returns the account number of when of the order it was created
    *
    * @return The accounts number
    *  
    * @since 1.0
    */
    public int getAccountNumber()
    {
        return accountNumber;
    }


    /**
    * Abstract method of playing the media
    *
    * @param media Indecates the type of media that will be played
    * 
    * @see Media
    *  
    * @since 1.0
    */
    public abstract String play(Media media);

    private int accountNumber;
    private static int nextAccountNumber = 1;
}
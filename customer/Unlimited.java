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
 * Models the Unlimited type account
 *
 * @author             Alvin Tran
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Unlimited extends Account
{
    public Unlimited()
    {
        super();
    }

    public Unlimited(BufferedReader br) throws IOException
    {
        super(br);
    }

    public void save(BufferedWriter bw) throws IOException
    {
        super.save(bw);
    }
    /**
    * Returns the type of played media
    * 
    * @return A string representation of the played media
    * 
    * @see Media
    *  
    * @since 1.0
    */
    @Override
    public String play(Media media)
    {
        return "Playing " + media.toString();
    }
}
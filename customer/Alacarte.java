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
 * Models the Alacarte type account 
 *
 * @author             Alvin Tran
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */

public class Alacarte extends Account
{
    public Alacarte()
    {
        super();
        this.pointsRemaining = pointsRemaining;
    }

    public Alacarte(BufferedReader br) throws IOException
    {
        super(br);
        this.pointsRemaining = Integer.parseInt(br.readLine());
    }

    public void save(BufferedWriter bw) throws IOException
    {
        super.save(bw);
        bw.write("" + pointsRemaining + '\n');
    }

    /**
    * Adds the total of bought points to the remaining amount of points
    * 
    * @param points The value of points that were bought
    *  
    * @since 1.0
    */
    public void buyPoints(int points)
    {
        this.pointsRemaining += points;
    }

    /**
    * Returns the remaining amount of points within the count
    * 
    * @return The number of points in the account
    *  
    * @since 1.0
    */
    public int getPointsRemaining()
    {
        return pointsRemaining;
    }

    /**
    * Returns the string of the played media if the amount of points
    *           remaing is greater then the amount the media costs
    * 
    * @return The string representation of the played media
    *           if the user has enough points
    * 
    * @see Media
    *  
    * @since 1.0
    */

    @Override
    public String play(Media media)
    {
        if(pointsRemaining < media.getPoints())
        {
            return "Buy more points: Requires " + media.getPoints() + " points you have: " + pointsRemaining;
        }
        else
        {
            pointsRemaining -= media.getPoints();
            return "Playing " + media.toString();
        }  
    }

    private int pointsRemaining;
}
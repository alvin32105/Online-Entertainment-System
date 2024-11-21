package product;

import java.net.URI;
import java.net.URL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

// Copyright 2024 by Professor George F. Rice, modifications copyright 2024 by Alvin Tran
// This program is free software: you can redistribute it and/or modify it under the terms 
// of the GNU General Public License as published by the Free Software Foundation,
// either version 3 of the License, or (at your option) any later version.
/**
 * Models a Media url
 *
 * @author             Alvin Tran
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */


public class Media
{
    /**
    * Creates a Media instance.
    *
    * @param title the title of the website the url links too
    * @param url A string of the Url link
    * @param points a parameter representing how much points the media costs
    * 
    * @since 1.0
    */
    public Media(String title, String url, int points)
    {
        this.title = title;
        this.url = url;
        try{
            URL urls = new URI(url).toURL();
        }catch(Exception e){
            throw new IllegalArgumentException("FAIL: " + url + " is an invalid URL");
        }
        this.points = points;
    }

    public Media(BufferedReader br) throws IOException
    {
        this.title = br.readLine();
        this.url = br.readLine();
        this.points = Integer.parseInt(br.readLine());
    }

    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(title + '\n');
        bw.write(url + '\n');
        bw.write("" + points + '\n');

    }
    
    /**
    * Returns the points that the media value is equal too
    * 
    * @return the cost of the Media in points
    *
    * @since 1.0
    */
    public int getPoints()
    {
        return points;
    }

    /**
     * Returns a printed form of the Media title, url, and points
     * 
     * @return a String of the title, url, and points
     * 
     * @since 1.0
     */
    @Override
    public String toString()
    {
       return title + " (" + url + ", " + points + " points)";
    }

    private String title;
    private String url;
    private int points;
}
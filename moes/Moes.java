package moes;

import product.Media;
import customer.Student;
import customer.Alacarte;
import customer.Unlimited;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

// Copyright 2024 by Alvin Tran
// This program is free software: you can redistribute it and/or modify it under the terms 
// of the GNU General Public License as published by the Free Software Foundation,
// either version 3 of the License, or (at your option) any later version.

/**
 * Models the base system for the Mavs Online Entertainment System
 *
 * @author             Alvin Tran
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Moes
{

    public Moes(){}

    public Moes(BufferedReader br) throws IOException
    {
        int sizeOfMedia = Integer.parseInt(br.readLine());
        while(sizeOfMedia --> 0)
        {
            library.add(new Media(br));
        }

        int sizeOfCustomers = Integer.parseInt(br.readLine());
        while(sizeOfCustomers --> 0)
        {
            customers.add(new Student(br));
        }
    }

    public void save(BufferedWriter bw) throws IOException
    {
        bw.write("" + library.size() + '\n');
        for(Media mediaLibrary : library)
            mediaLibrary.save(bw);
        
        bw.write("" + customers.size() + '\n');
        for(Student student: customers)
            student.save(bw);
    }
    
    /**
    * Adds to the Array list media library
    *
    * @param media The information of an entire Media object
    * 
    * @see Media
    * 
    * @since 1.0
    */
    public void addMedia(Media media)
    {
        library.add(media);
    }

    /**
    * Displays the media library to the console
    *
    * @return Returns a String of the from the Media library
    *           and lists the order the media was added into the library
    * 
    * @see Media
    * 
    * @since 1.0
    */
    public String getMediaList()
    {
        int i = 0;
        StringBuilder result = new StringBuilder();
        for(Media mediaLibrary : library)
            result.append("" + i++ + ") " + mediaLibrary.toString() + "\n");  

        return result.toString();
    }

    /**
    * Adds the students information to an Arraylist called customers
    * 
    * @param student Houses the students name, id, email, and account information
    * 
    * @see Student
    * 
    * @since 1.0
    */

    public void addStudent(Student student)
    {
        customers.add(student);
    }

   /**
    * Adds the students information to an Arraylist called customers
    * 
    * @return Returns a String of the students information
    *           and lists the order the students information was added
    *           to the customers list
    * 
    * @see Student
    * 
    * @since 1.0
    */

    public String getStudentList()
    {
        int i = 0;
        StringBuilder result = new StringBuilder();
        for (Student student : customers) 
        {
             result.append("" + i++ + ") " + student.toString() + "\n");
        }
        return result.toString();
    }
/*
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<students.size(); ++i)
            sb.append("" + i + ") " + students.get(i) + '\n');
        return sb.toString();
*/
   /**
    * Returns the amount of points a student has in their account
    * 
    * @param studentIndex The index which the students information
    *                       is located within the customers list 
    * 
    * @return Returns the remaining number of points a student has
    *           if there account is an Alacarte type otherwise
    *           it will be an unlimited account with max number of points
    * 
    * @see Alacarte
    * @see Unlimited
    * 
    * @since 1.0
    */

    public int getPoints(int studentIndex)
    {
        if (studentIndex < 0 || studentIndex >= customers.size()) 
            throw new IndexOutOfBoundsException("Invalid student index: " + studentIndex);

        if(customers.get(studentIndex).getAccount() instanceof Alacarte)
        {
            Alacarte alacarte = (Alacarte) customers.get(studentIndex).getAccount();
            return alacarte.getPointsRemaining();
        }
        else if(customers.get(studentIndex).getAccount() instanceof Unlimited)
        {
             return Integer.MAX_VALUE;
        }
        else
        {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }
    }

    /**
    * Returns the amount of points a student has in their account
    * 
    * @param studentIndex The index which the students information
    *                       is located within the customers list 
    * @param points A value representing the points a student has.
    * 
    * @return A string indicated how many total points a student has 
    *           depending on the type of account owned
    * 
    * @see Alacarte
    * @see Unlimited
    * 
    * @since 1.0
    */

    public String buyPoints(int studentIndex, int points)
    {
        if (studentIndex < 0 || studentIndex >= customers.size()) 
            throw new IndexOutOfBoundsException("Invalid student index: " + studentIndex);

        if(customers.get(studentIndex).getAccount() instanceof Alacarte)
        {
            Alacarte alacarte = (Alacarte) customers.get(studentIndex).getAccount();
            alacarte.buyPoints(points);
            return "Available points: " + alacarte.getPointsRemaining() + '\n';
        }
        else if(customers.get(studentIndex).getAccount() instanceof Unlimited)
        {
            return "Unlimited Account";
        }
        else
        {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }

    }

   /**
    * Returns the string of the played media
    * 
    * @param studentIndex   The index which the students information
    *                           is located within the customers list 
    * @param mediaIndex     The index which the media is located within
    *                           the media library
    * 
    * @return the string of the requested media
    * 
    * @see Student
    * 
    * @since 1.0
    */

    public String playMedia(int studentIndex, int mediaIndex)
    {
        Media media = library.get(mediaIndex);
        Student student = customers.get(studentIndex);
        return student.requestMedia(media);
    }

    private ArrayList<Media> library = new ArrayList<>();
    private ArrayList<Student> customers = new ArrayList<>();
}

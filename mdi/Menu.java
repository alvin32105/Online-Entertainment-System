package mdi;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Menu {

    public void addMenuItem(MenuItem item) 
    {
        items.add(item);
    }

    public static String getString(String prompt, String cancelInput, String defaultInput) 
    {
        String s = null;
        while(true)
        {
            try{
                System.out.print(prompt);
                s = in.nextLine().trim();
                if(s.isEmpty() && defaultInput != null)
                    s = defaultInput;
                break;
            }catch(Exception e)
            {
                System.err.println("Invalid input");
            }
        }
        if(cancelInput != null && s.equals(cancelInput))
            s = null;
        return s;
    }

    public static String getString(String prompt, String cancelInput) 
    {
        return getString(prompt, cancelInput, null);
    }

    public static String getString(String prompt) 
    {
        return getString(prompt, null, null);
    }

    public static Integer getInt(String prompt, String cancelInput, String defaultInput) 
    {
        Integer i = null;
        while(true) 
        {
            try  {
                String s = getString(prompt, cancelInput, defaultInput);
                if(s != null && !s.isEmpty()) i = Integer.parseInt(s);
                break;
            } catch(Exception e) {
                System.err.println("Invalid input!");
            }
        }
        return i;
    }

    public static Integer getInt(String prompt, String cancelInput) 
    {
        return getInt(prompt, cancelInput, null);
    }

    public static Integer getInt(String prompt) 
    {
        return getInt(prompt, null, null);
    }

    public static Character getChar(String prompt, String cancelInput, String defaultInput) 
    {
        Character c = null;
        while(true) 
        {
            try  {
                String s = getString(prompt, cancelInput, defaultInput);
                if(s != null && !s.isEmpty()) c = s.charAt(0);
                break;
            } catch(Exception e) {
                System.err.println("Invalid input!");
            }
        }
        return c;
    }

    public static Character getChar(String prompt, String cancelInput) 
    {
        return getChar(prompt, cancelInput, null);
    }

    public static Character getChar(String prompt) 
    {
        return getChar(prompt, null, null);
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder(banner);

        for(int i=0 ; i<items.size() ; ++i)
            sb.append(" " + i + "] " + items.get(i) + "\n");

       return sb.toString();
    }

    

    public void run(int itemNumber) 
    {
        items.get(itemNumber).run();
    }
    private static String banner = 
    """
                        ':'.''.
                  ._.-.___.' (`\\
                  //(         \\`'
                  '/ )\\ ).__. )  
    .**************--**-*****-******************.            
    |      Mavs Online Entertainment (MOES)     |
    |*******************************************|
    |     Version 0.3.0   © 2024 Alvin Tran     |
    |     Default File: Untitled.txt            |
    '*******************************************'
    \n""";
    private List<MenuItem> items = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
}
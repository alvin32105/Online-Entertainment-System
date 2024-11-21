package mdi;

import moes.Moes;
import customer.Student;
import product.Media;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Main
{
    public Main(Moes moes)
    {
        this.moes = moes;
        this.output = "";
        this.menu = new Menu();
        this.running = running;

        menu.addMenuItem(new MenuItem("Exit\n",                ()-> endApp()));
        menu.addMenuItem(new MenuItem("Play Media",            ()-> playMedia()));
        menu.addMenuItem(new MenuItem("List Media",            ()-> listMedia()));
        menu.addMenuItem(new MenuItem("List available points", ()-> listAvailablePoints()));
        menu.addMenuItem(new MenuItem("Buy points",            ()-> buyPoints()));
        menu.addMenuItem(new MenuItem("Add media\n",           ()-> addMedia()));
        menu.addMenuItem(new MenuItem("List all students",     ()-> listStudent()));
        menu.addMenuItem(new MenuItem("Add a student\n",       ()-> addStudent()));

        this.filename = "Untitled.txt";
    }

    private boolean running = true;
    private void mdi()
    {
        while(running) 
        {
            try {
                Integer i = selectFromMenu();
                if(i == null) continue;
                menu.run(i); 
            } catch (Exception e) {
                System.err.print("#### Invalid command");
                output = menuPrint("#### Invalid command ");
            } 
        }
    }

    private void endApp()
    {
        running = false;
    }
    
    private String menuPrint(String s)
    {
        StringBuilder string = new StringBuilder
        ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" + 
        s + "\n\n" +
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");

        return string.toString();
    }


    private static String clearScreen = "\n".repeat(255);
    private int selectFromMenu() 
    {
        //System.out.println("\n\n" + menu + '\n' + output);
        System.out.println(clearScreen + "\n\n" + menu + '\n' + output);
        output = "";  
        int choice = menu.getInt("Selection? ", "");
        return choice;
    }

    public static void main(String[] args)
    {
        Moes moes = new Moes();
        Main mainInstance = new Main(moes);

        if(args.length > 0)
        {
            String newFile = args[0];
            mainInstance.open(newFile);
        }
        else
        {
            mainInstance.save();
        }

        mainInstance.mdi();
    }

    private void addStudent()
    {
        String name = menu.getString("Student name? ", "");
        int id =  menu.getInt("Student ID? ", "");
        String email = menu.getString("Student email? ", "");
        char accType =  menu.getChar("(a)lacarte or (u)nlimited? ", "");

        boolean type = (accType == 'a') ? false : (accType == 'u') ? true : true;
        String type_2 = (type) ? "Unlimited" : "Alacarte";

        Student placeholder = new Student(name, id, email, type);
        moes.addStudent(placeholder);

        save();
        output = menuPrint( "Added student " + placeholder.toString());
    }

    private void listStudent()
    {
        output = menuPrint(moes.getStudentList());
    }

    private void addMedia()
    {
        String title = menu.getString("Title? ", "");
        String url = menu.getString("URL? ", "");
        int points = menu.getInt("Points? ", "");

        Media placeholder = new Media(title, url, points);
        moes.addMedia(placeholder);

        save();
        output = menuPrint( "Added Media " + placeholder.toString());
    }

    private void playMedia()
    {
        System.out.println(moes.getStudentList());
        int studIndex =  menu.getInt("\nStudent number? ", "");

        System.out.println(moes.getMediaList());
        int medIndex =  menu.getInt("\nMedia? ", "");

        output = menuPrint(moes.playMedia(studIndex, medIndex));
    }

    private void listMedia()
    {
        output = menuPrint(moes.getMediaList());     
    }

    private void listAvailablePoints()
    {
        System.out.println(moes.getStudentList());
        int index =  menu.getInt("Access which account's points? ", "");

        output = menuPrint("Points remaining: " + moes.getPoints(index));
    }

    private void buyPoints()
    {
        System.out.println(moes.getStudentList());
        int points;
        int index =  menu.getInt("Buy points for which account? ", "");
        if(moes.getPoints(index) == Integer.MAX_VALUE)
        {
            points = 0;
        }
        else
        {
            points = menu.getInt("How many points? ", "");
        }

        save();
        output = menuPrint(""+moes.buyPoints(index, points));

    }

    private void newMoes()
    {
        this.moes = new Moes();
    }

    private void open(String newFile)
    {
        this.filename = newFile;

        if(filename.isEmpty())
        {
            System.out.println("no file name given");
            return;
        }

        if(!filename.endsWith(".txt"))
            filename += extension;
        
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String magicCookieTest = br.readLine();
            String fileVersionTest = br.readLine();

            if(!magicCookieTest.equals(magicCookie))
                throw new IOException("Incorrect cookie");

            if(!fileVersionTest.equals(fileVersion))
                throw new IOException("Incorrect Version");
            this.moes = new Moes(br);
            output = menuPrint("Opened with file: " + filename);
            
        }catch(Exception e){
            System.err.println("Error in getting file from" + filename + "\n" + e);
        }
    }

    private void save()
    {

        if(filename.isEmpty() || filename == null)
            this.filename = "Untitled.txt";

        File file = new File(filename);
        try
        {
            file.createNewFile();
        }catch(Exception e){
            System.err.println("Failed to create a new File " + e);
        }

        if(file.exists())
        {
            File backup = new File(filename + '~');
            file.renameTo(backup);

        }
        else
        {
            System.err.println("File does not exist");
            return;
        }
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            bw.write(magicCookie + '\n');
            bw.write(fileVersion + '\n');
            moes.save(bw);
            output = menuPrint("Saved with file: " + filename);
        }catch(Exception e){
            System.err.println("Failed to save in " + filename + "\n" + e);
        }
    }

    // private void saveAs()
    // {
    //     System.out.println("Current filename: " + filename);
    //     String newFile = menu.getString("Enter a new file name: ", "");
    //     if(newFile.isEmpty())
    //     {
    //         System.out.println("no file name given");
    //         return;
    //     }

    //     if(!newFile.contains("."))
    //         newFile += extension;

    //     filename = newFile;

    //     save();
    // }

    private Moes moes;
    private String output;
    private Menu menu;
    private String filename;
    private final String magicCookie = "#@asd!#";
    private final String extension = ".txt";
    private final String fileVersion = "1.0";
}


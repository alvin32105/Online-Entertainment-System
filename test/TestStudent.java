package test;
import customer.Student;
import product.Media;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TestStudent
{
    public static void main(String[] args)
    {
        int vector = 1, failure = 0;
        Student student1 = new Student("Alvin Tran", 1002098641, "aat8641@mavs.uta.edu", true);
        Media media = new Media("Yorushika - Fireworks Beneath My Shoes","https://www.youtube.com/watch?v=BCt9lS_Uv_Y", 100);
        String expectedStudent = "Alvin Tran (1002098641, aat8641@mavs.uta.edu, Account #1)";
        String expectedMedia = "Playing Yorushika - Fireworks Beneath My Shoes (https://www.youtube.com/watch?v=BCt9lS_Uv_Y, 100 points)";
        Student studentTest = null;

        String tempFile = "student_Stuff.txt";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));){
            student1.save(bw);
        }catch(IOException e){
            System.err.println("Failed to write into file");
        }

        try(BufferedReader br = new BufferedReader(new FileReader(tempFile));){
            Student student2 = new Student(br);
        }catch(IOException e){
            System.err.println("Failed to read into file");
        }


        if(!student1.toString().equals(expectedStudent))
        {
            System.err.println("FAIL: Incorrect user toString ");
            System.err.println("  returned '" + student1.toString() + "'");
            System.err.println("  expected '" + expectedStudent + "'");
            failure += vector;
        }

        try{
            Student c = new Student("Alvin Tran", 1002098641, "alvinisking01@gmail.com", true);
            System.err.println("FAIL: Student accepted NON-UTA email");
            failure += vector;
        }catch(IllegalArgumentException e){
        }catch(Exception e){
            System.err.println("FAIL: something went wrong\n" + e);
            failure += vector;
        }

        if(!student1.toString().equals(expectedStudent))
        {
            System.err.println("FAIL: Incorrect user toString ");
            System.err.println("  returned '" + student1.toString() + "'");
            System.err.println("  expected '" + expectedStudent + "'");
            failure += vector;
        }

        if(!student1.requestMedia(media).equals(expectedMedia))
        {
            System.err.println("FAIL: Incorrect user toString ");
            System.err.println("  returned '" + student1.requestMedia(media) + "'");
            System.err.println("  expected '" + expectedMedia + "'");
            failure += vector;
        }

        if(failure != 0)
        {
            System.err.println("\nFAILED with error code " + failure);
            System.exit(failure);
        }
    }
}
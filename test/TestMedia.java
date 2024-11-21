package test;

import product.Media;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TestMedia
{
    public static void main(String[] args)
    {
        int result = 0;
        int vector = 1;
        String title = "Youtube";
        String url = "https://www.youtube.com";
        int points = 100;
        Media media = new Media(title, url, points);
        String expectedCase = "Youtube (https://www.youtube.com, 100 points)";
        String[] validURL = {"https://youtube.com", "file://media/lib/garp.mp4"};
        String[] invalidURL = {"hello.world", "htt://badurl.com","flub://badurl.com"};
        
        String tempFile = "media_Stuff.txt";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));){
            media.save(bw);
        }catch(IOException e){
            System.err.println("Failed to write into file");
        }

        try(BufferedReader br = new BufferedReader(new FileReader(tempFile));){
            Media mediaNew = new Media(br);
        }catch(IOException e){
            System.err.println("Failed to read into file");
        }



        for(String validURLS: validURL)
            try{
                Media m = new Media("placeholder", validURLS, 100);
            }catch(Exception e){
                System.err.println("FAIL: something went wrong");
                result+=vector;
            }

        for(String invalidURLS: invalidURL)
            try{
                Media m = new Media("placeholder", invalidURLS, 100);
                result+=vector;
            }catch(Exception e){
                //System.err.println(e);
            }

        if(!media.toString().equals(expectedCase))
        {
            System.err.println("FAIL: Incorrect user toString ");
            System.err.println("  returned '" + media + "'");
            System.err.println("  expected '" + expectedCase + "'");
            result += vector;
        }



        if(result != 0)
        {
            System.err.println("\nFAILED with error code " + result);
            System.exit(result);
        }
    }
}
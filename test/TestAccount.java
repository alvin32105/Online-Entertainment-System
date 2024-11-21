package test;

import customer.Unlimited;
import customer.Alacarte;
import product.Media;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class TestAccount
{
    public static void main(String[] args)
    {
        int result = 0, vector = 1, expected1 = 1,expected2 = 2;
        Unlimited account1 = new Unlimited();
        Alacarte account2 = new Alacarte();
        String tempFile = "account_Stuff.txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));){
            account1.save(bw);
            account2.save(bw);
        }catch(IOException e){
            System.err.println("Failed to write into file");
        }

        try(BufferedReader br = new BufferedReader(new FileReader(tempFile));){
            String line;
            while((line=br.readLine())!=null){}
        }catch(IOException e){
            System.err.println("Failed to read into file");
        }
        
        if((account1.getAccountNumber() != expected1))
        {
            System.err.println("FAIL: Incorrect account number for Account 1");
            System.err.println("  returned '" + account1.getAccountNumber() + "'");
            System.err.println("  expected '" + expected1 + "'");
            result += vector;
        }

        if((account2.getAccountNumber() != expected2))
        {
            System.err.println("FAIL: Incorrect account number for Account 2");
            System.err.println("  returned '" + account2.getAccountNumber() + "'");
            System.err.println("  expected '" + expected2 + "'");
            result += vector;
        }

        if(result != 0)
        {
            System.err.println("\nFAILED with error code " + result);
            System.exit(result);
        }
    }
}
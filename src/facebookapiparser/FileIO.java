package facebookapiparser;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileIO {
       
      /*
       * This method serves to write a String to a file, with:
       * boolean true = append
       * boolean false = delete file and overwrite
       */
      public void writeToFile(String input, String path, boolean writeMode) throws Exception {
        BufferedWriter out = new BufferedWriter(new FileWriter(path, writeMode));
        out.write(input + "\n");
        out.close();
    }
    
      
    public void writeArrayToFile(ArrayList<String> input, String path) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileOutputStream(path));
            for (int i = 0; i < input.size(); i++) {
                printWriter.println(input.get(i));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
    
    
}

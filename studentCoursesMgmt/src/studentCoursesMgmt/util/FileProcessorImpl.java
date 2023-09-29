package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessorImpl implements FileProcessorI {
     private Scanner scanner;
     //java docstring

    /**
     *
     * @param fileName Str
     * @return scanner
     * @throws FileNotFoundException
     */
    public FileProcessorImpl(String fileName) {
        try{
            scanner = new Scanner(new File(fileName));
        } catch(FileNotFoundException fe)
        {
            System.err.println(fe.getMessage());
            //print stack trace
            fe.printStackTrace();
            System.exit(1);
        } finally {
        }
    }

    /**
     *
     * @param scanner
     * @return
     */
    public String nextLine(Scanner scanner){
        if(scanner.hasNextLine()){
            return scanner.nextLine();
        }
        return null;
    }

    // write Line method
    // docstring

    /**
     *
     * @param fileName
     * @param line
     */
    public void writeLine(String fileName, String line){
        FileWriter fileWriter = null;
        try {
            // open file in append mode and write to it, it has specific path
            File file = new File(fileName);
            fileWriter = new FileWriter(file, true);
            fileWriter.write(line + "\n");
            fileWriter.close();
        } catch (Exception e) {
            System.err.println("Error while writing to file: " + fileName + " System Message" + e.getMessage());
            //print stack trace
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            try {
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while closing file: " + fileName);
                System.exit(1);
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessorImpl implements FileProcessorI {


    public FileProcessorImpl() {
        /* Has two methods, storeStudentPrefs and storeCourseInfo.
         */

    }
    public List<String> storeStudentPrefs(String fileName){
        List<String> studentPrefs = new ArrayList<>();

        try{
            System.out.println(fileName);
            File file = new File("../" +fileName); // Create a File object from the file name
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
            studentPrefs.add(sc.nextLine());
            }
//            studentPrefs.add(sc.nextLine());

        }
        catch (FileNotFoundException fnf) {
            System.out.println("File Not Found" + fnf.getMessage()); // Print to console for debugging
            System.exit(1); // Exit the program
        }
        return studentPrefs;

    }
    public List<String> storeCourseInfo(String fileName){
        List<String> courseInfo = new ArrayList<>();
        try {
            File file = new File("../"+fileName); // Create a File object from the file name
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                courseInfo.add(sc.nextLine());
            }
//            courseInfo.add(sc.nextLine());
        }
        catch (FileNotFoundException fnf) {
            System.out.println("File Not Found" + fnf.getMessage()); // Print to console for debugging
            System.exit(1); // Exit the program
        }
    return courseInfo;
    }



}

package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class storeStudentInfo {
    public List<String> generateStudentList(FileProcessorImpl fileProcessor){
        List<String> studentPrefs = new ArrayList<>();
        Scanner scanner = fileProcessor.getScanner();


            while (scanner.hasNextLine()) {
                studentPrefs.add(scanner.nextLine());
            }
//            studentPrefs.add(sc.nextLine());
        return studentPrefs;

    }
}

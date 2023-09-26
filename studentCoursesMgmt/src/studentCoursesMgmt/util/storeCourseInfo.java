package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class storeCourseInfo {
    public List<String> generateCourseList(FileProcessorImpl fileProcessor){
        List<String> courseInfo = new ArrayList<>();
        Scanner scanner = fileProcessor.getScanner();


        while (scanner.hasNextLine()) {
            courseInfo.add(scanner.nextLine());
        }
//            studentPrefs.add(sc.nextLine());
        return courseInfo;

    }
}

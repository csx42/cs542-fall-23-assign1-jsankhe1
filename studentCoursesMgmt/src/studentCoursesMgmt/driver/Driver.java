package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.*;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * @author Jay Balaram Sankhe
 */
public class Driver {
    public static void main(String[] args) {

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         */

        if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
                || args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

            System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
            System.exit(0);
        }


        System.out.println("Hello World! Lets get started with the assignment");
        FileProcessorImpl studentFp = new FileProcessorImpl(args[0]);
        FileProcessorImpl courseFp = new FileProcessorImpl(args[1]);
        storeStudentInfo studentProcessor = new storeStudentInfo();
        storeCourseInfo courseProcessor = new storeCourseInfo();
        Results result = new Results(args[2], args[3], args[4]);


        List<String> studentPrefsData = studentProcessor.generateStudentList(studentFp);
        List<String> courseInfoPrefsData = courseProcessor.generateCourseList(courseFp);

        DataGeneratorInterface parsedObjectListDataGenerator = new DataGeneratorImpl();
        List<StudentImpl> studentPrefsArray = parsedObjectListDataGenerator.createStudentData(studentPrefsData);
        List<CourseImpl> courseDataArray = parsedObjectListDataGenerator.getCourseInfo(courseInfoPrefsData);
        System.out.println(studentPrefsData);
        System.out.println(studentPrefsArray);
        System.out.println("Below is course info\n");
        System.out.println(courseInfoPrefsData);
        System.out.println(courseDataArray);
        AssigningLogicInterface assigner = new AssigningLogicImpl();
        Map<Integer, List<String>> results = assigner.assignCourses(studentPrefsArray, courseDataArray, result, args[2], args[3], args[4]);
        System.out.println(results);
        System.out.println(courseDataArray);
        // course capacity data  in course prefs array isn't changing after assigning, check it out.
    }
}

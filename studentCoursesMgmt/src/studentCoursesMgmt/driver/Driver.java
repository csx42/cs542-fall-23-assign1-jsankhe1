package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.*;

import java.util.List;

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
        FileProcessorImpl fp = new FileProcessorImpl();
        List<String> studentPrefsData = fp.storeStudentPrefs(args[0]);
        List<String> courseInfoPrefsData = fp.storeCourseInfo(args[1]);
        StudentPrefsGeneratorInterface studentPrefs = new StudentPrefsGenerator();
        CourseInfoGeneratorInterface courseInfo = new CourseInfoGeneratorImpl();
        List<StudentImpl> studentPrefsArray = studentPrefs.createStudentData(studentPrefsData);
        List<CourseImpl> courseDataArray = courseInfo.getCourseInfo(courseInfoPrefsData);
        System.out.println(studentPrefsData);
        System.out.println(studentPrefsArray);
        System.out.println("Below is course info\n");
        System.out.println(courseInfoPrefsData);
        System.out.println(courseDataArray);

    }
}

package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    @Override
    public void writeRegistrationResultsToFile(String fileName, String line) {
        System.out.println("Writing to file: " + fileName + " line: " + line);
    }

    @Override
    public void writeRegistrationConflictsToFile(String registrationConflictFile, int studentId, String courseName, String reason) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(registrationConflictFile, true);
            fileWriter.write("Student with Student Id: " + studentId + " cannot be assigned course with Course Name: " +
                    courseName + reason + "\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error while writing to file: " + registrationConflictFile);
            System.exit(1);
        }
    }

    @Override
    public void writeErrorToFile(String fileName, String line) {
        FileWriter errorFileWriter = null;
        try {
            errorFileWriter = new FileWriter(fileName, true);
            errorFileWriter.write(line + "\n");
            errorFileWriter.close();
        } catch (Exception e) {
            System.out.println("Error while writing to file: " + fileName);
            //print stack trace
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                errorFileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while closing file: " + fileName);
                //print stack trace
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    @Override
    public void writeToStdout(String s) {
        System.out.println(s);
    }

}

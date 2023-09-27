package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    //Results constructor that takes string arguments from driver commandline
    public Results(String registration_results, String registration_conflicts, String errorLog) {

    }
    @Override
    public void writeRegistrationResultsToFile(String registration_results, String line) {
        FileWriter fileWriter = null;
        try {
            // open file in append mode and write to it, it has specific path
            File file = new File("../" + registration_results);
            fileWriter = new FileWriter(file, true);
            fileWriter.write(line + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + registration_results);
            System.exit(1);
        }
        finally {
            try {
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while closing file: " + registration_results);
                System.exit(1);
            }
        }
    }
    @Override
    public void writeRegistrationConflictsToFile(String registrationConflictFile, int studentId, String courseName, String reason) {
        FileWriter fileWriter = null;
        try {
            // open file in append mode and write to it, it has specific path
            File file = new File("../" + registrationConflictFile);
            fileWriter = new FileWriter(file, true);
            fileWriter.write("Student with Student Id: " + studentId + " cannot be assigned course with Course Name: " +
                    courseName + reason + "\n");
//            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + registrationConflictFile);
            System.exit(1);
        }
        finally {
            try {
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while closing file: " + registrationConflictFile);
                System.exit(1);
            }
        }
    }

    @Override
    public void writeErrorToFile(String errorLog, String line) {
        FileWriter errorFileWriter = null;
        try {
            File file = new File("../" + errorLog);
            errorFileWriter = new FileWriter(file, true);
            errorFileWriter.write(line + "\n");
            errorFileWriter.close();
        } catch (Exception e) {
            System.out.println("Error while writing to file: " + errorLog);
            //print stack trace
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                errorFileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while closing file: " + errorLog);
                //print stack trace
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

//    @Override
//    public void writeToStdout(String s) {
//        System.out.println(s);
//    }

}

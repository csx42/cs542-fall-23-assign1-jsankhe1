package studentCoursesMgmt.util;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Results implements StdoutDisplayInterface, FileDisplayInterface {

    private final String registrationResultsFile;
    private final String registrationConflictFile;
    private final String errorLog;

    /**
     * @param registrationResultsFile file name
     * @param registrationConflictFile file name
     * @param errorLog file name
     * @return void
     */
    public Results(String registrationResultsFile, String registrationConflictFile, String errorLog) {
        this.registrationResultsFile = registrationResultsFile;
        this.registrationConflictFile = registrationConflictFile;
        this.errorLog = errorLog;
    }


    /**
     * @param registrationConflictFile file name
     * @param reason                   reason for error
     * @return void
     */
    @Override
    public void writeRegistrationConflictsToFile(String registrationConflictFile, String reason) {
        FileWriter fileWriter = null;
        try {
            // open file in append mode and write to it, it has specific path
            File file = new File(registrationConflictFile);
            fileWriter = new FileWriter(file, true);
            fileWriter.write(reason + "\n");
//            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + registrationConflictFile);
            System.exit(1);
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while closing file: " + registrationConflictFile);
                System.exit(1);
            }
        }
    }


    /**
     * @param errorLog file name
     * @param reason   reason for error
     * @return void
     */
    @Override
    public void writeErrorToFile(String errorLog, String reason) {
        FileWriter errorFileWriter = null;
        try {
            File file = new File(errorLog);
            errorFileWriter = new FileWriter(file, true);
            errorFileWriter.write(reason + "\n");
            errorFileWriter.close();
        } catch (Exception e) {
            System.out.println("Error while writing to file: " + errorLog);
            //print stack trace
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                assert errorFileWriter != null;
                errorFileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while closing file: " + errorLog);
                //print stack trace
                e.printStackTrace();
                System.exit(1);
            }
        }

    }



    /**
     * @param scheduleFile    file name
     * @param assignedCourses Map DataStructure assigned courses
     * @param studentArray    Arraylist student array
     * @return void
     */
    @Override
    public void writeRegistrationResultsToFile(String scheduleFile, Map<Integer,
            List<String>> assignedCourses, List<StudentImpl> studentArray) {
        PrintWriter printWriter = null;
        double totalAverageSatisfactionRating = 0.0;
        try {
            // Open the file in append mode and write to it
            File file = new File(scheduleFile);
            printWriter = new PrintWriter(new FileWriter(file, true));

            // Iterate through the list of students
            for (StudentImpl student : studentArray) {
                // Check if the student's ID is in the assignedCourses map
                if (assignedCourses.containsKey(student.getStudentId())) {
                    // Write the student's ID
                    printWriter.print(student.getStudentId() + ": ");

                    // Iterate through the assigned courses for this student
                    for (String course : assignedCourses.get(student.getStudentId())) {
                        // Write each course followed by a space
                        printWriter.print(course + " ");
                    }

                    // Format and write the satisfaction rating with two decimal places
                    printWriter.printf(":: Satisfaction Rating = %.2f%n", student.getAverageSatisfactionRating());
                    totalAverageSatisfactionRating += student.getAverageSatisfactionRating();
                }
            }
            //calculate total average satisfaction rating
            double totalAverageSatisfactionRatingForAllStudents = totalAverageSatisfactionRating / studentArray.size();
            // writing the total average satisfaction rating for all students at the end of the file
            printWriter.printf("Average satisfaction rating = %.2f%n", totalAverageSatisfactionRatingForAllStudents);

            // Flush and close the PrintWriter
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            //write below to error log insteado f just system.err
            System.err.println("Error while writing to file: " + scheduleFile + " System Message" + e.getMessage());
            String reason = "Error while writing to file: " + scheduleFile +
                    " System Message" + e.getMessage() + "Stack Trace: " + Arrays.toString(e.getStackTrace());
            //print stack trace
            e.printStackTrace();
            writeErrorToFile("errorLog.txt", reason);
            System.exit(1);
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (Exception e) {
                System.out.println("Error while closing file: " + scheduleFile);
                System.exit(1);
            }
        }
    }

    /**
     * @return void
        */
    @Override
    public void toStdOut() {
        try {
            //get the
            File file = new File(registrationResultsFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error while reading file: " + registrationResultsFile);
            System.exit(1);
        }

    }
}

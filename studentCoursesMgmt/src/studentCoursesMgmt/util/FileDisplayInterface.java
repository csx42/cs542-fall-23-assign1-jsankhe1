package studentCoursesMgmt.util;

public interface FileDisplayInterface {
    void writeRegistrationResultsToFile(String fileName, String line);

    public void writeRegistrationConflictsToFile(String registrationConflictFile, int studentId, String courseName, String reason);

    void writeErrorToFile(String fileName, String line);
}

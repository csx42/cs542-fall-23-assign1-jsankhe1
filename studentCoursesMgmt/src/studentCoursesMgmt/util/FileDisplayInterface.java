package studentCoursesMgmt.util;

import java.util.List;
import java.util.Map;

public interface FileDisplayInterface {
    public void writeRegistrationResultsToFile(String scheduleFile, Map<Integer,
            List<String>> assignedCourses, List<StudentImpl> studentArray);
    public void writeRegistrationConflictsToFile(String registrationConflictFile, String reason);

    public void writeErrorToFile(String fileName, String reason);
}

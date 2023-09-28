package studentCoursesMgmt.util;

import java.util.List;
import java.util.Map;

public interface AssigningLogicInterface {

    public Map<Integer, List<String>> assignCourses(List<StudentImpl> studentPrefsArray,
                                                    List<CourseImpl> courseArray,
                                                    Results result,
                                                    String regResults,
                                                    String regConflicts,
                                                    String errorLog);

    public boolean noTimeClashCheck(String courseName, StudentImpl student, List<CourseImpl> courseArray, Map<Integer, List<String>> timeSlotMap);
    public Map<Integer, List<String>> timeSlotMapping(List<CourseImpl> courseArray);
    public boolean capacityCheck(String courseName, List<CourseImpl> courseArray);
}


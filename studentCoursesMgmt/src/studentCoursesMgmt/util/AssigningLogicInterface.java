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

}

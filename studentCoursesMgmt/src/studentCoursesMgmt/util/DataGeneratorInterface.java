package studentCoursesMgmt.util;

import java.util.List;

public interface DataGeneratorInterface {
    public List<CourseImpl> getCourseInfo(List<String> courseInfoList);
    public List<StudentImpl> createStudentData(List<String> studentPrefList);


}

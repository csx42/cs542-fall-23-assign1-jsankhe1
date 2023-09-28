package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.List;

public class DataGeneratorImpl implements  DataGeneratorInterface{
    private List<StudentImpl> studentPrefsArray = new ArrayList<>();

    /**
     * @param studentPrefs
     * @return studentPrefsArray (list)
     */
    public List<StudentImpl> createStudentData(List<String> studentPrefs) {
//        List<StudentImpl> studentPrefsArray = new ArrayList<>();
        for (int i = 0; i < studentPrefs.size(); i++) {

            StudentImpl student = new StudentImpl(studentPrefs.get(i));
            studentPrefsArray.add(student);
        }
        return studentPrefsArray;
    }

    /**
     * @param courseInfoList
     * @return courseInfoData (list
     */
    private List<CourseImpl> courseArray = new ArrayList<>();
    @Override
    public List<CourseImpl> getCourseInfo(List<String> courseInfoList) {
        for (int i = 0; i < courseInfoList.size(); i ++){
            CourseImpl course = new CourseImpl(courseInfoList.get(i));
            courseArray.add(course);
        }
        return courseArray;
    }

}

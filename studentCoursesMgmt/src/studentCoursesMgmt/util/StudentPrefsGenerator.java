package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.List;

public class StudentPrefsGenerator implements StudentPrefsGeneratorInterface{

    private List<StudentImpl> studentPrefsArray = new ArrayList<>();

    public List<StudentImpl> createStudentData(List<String> studentPrefs) {
//        List<StudentImpl> studentPrefsArray = new ArrayList<>();
        for (int i = 0; i < studentPrefs.size(); i++) {

            StudentImpl student = new StudentImpl(studentPrefs.get(i));
            studentPrefsArray.add(student);
        }
        return studentPrefsArray;
    }

}

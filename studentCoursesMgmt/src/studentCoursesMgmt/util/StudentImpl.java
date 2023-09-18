package studentCoursesMgmt.util;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentImpl implements StudentInterface {

    private  int studentId;
    private List<Integer> assignedCourses = new Array();

    List<String> preferences = new ArrayList<>();
    public StudentImpl(String line) {
        try
        {
            String[] stringSplit = line.split(" |;");
            studentId =  Integer.parseInt(stringSplit[0]);

            // adding the prefs to preference Arraylist
            for (int i = 1; i < stringSplit.length; i++) {
                preferences.add(stringSplit[i]);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Unable to parse Integer at beginning");
        }
    }

    public List<Integer> getAssignedCourses() {
        return assignedCourses;
    }

    public void setAssignedCourses(List<Integer> assignedCourses) {
        this.assignedCourses = assignedCourses;
    }

    ;
    public int getStudentId() {
        return studentId;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setStudentId() {
    }
    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "StudentImpl{" +
                "studentId=" + studentId +
                ", preferences=" + preferences +
                '}';
    }

    //
//    }



}

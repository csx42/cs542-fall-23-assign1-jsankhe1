package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.List;

public class StudentImpl implements StudentInterface {

    private  int studentId;
    private  double satisfactionRating = 0;
    private double averageSatisfactionRating = 0;
    private List<String> assignedCourses = new ArrayList<>(3);
    List<String> preferences = new ArrayList<>();
    private int studentCourseCount = 0;

    public int getStudentCourseCount() {
        return studentCourseCount;
    }

    public void setStudentCourseCount(int studentCourseCount) {
        this.studentCourseCount = studentCourseCount;
    }



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

    public List<String> getAssignedCourses() {
        return assignedCourses;
    }

    public void addCourse(String courseName) {
        assignedCourses.add(courseName);
        setStudentCourseCount(getStudentCourseCount() + 1);
    }
    public void setAssignedCourses(List<String> assignedCourses) {
        this.assignedCourses = assignedCourses;
    }



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
    public double  getSatisfactionRating() {
        return satisfactionRating;
    }

    public double getAverageSatisfactionRating() {
        if(this.studentCourseCount == 0 || this.satisfactionRating == 0)
        {
            return 0;
        }
        else {
            this.averageSatisfactionRating = this.satisfactionRating / this.studentCourseCount;
            return averageSatisfactionRating;
        }
    }

    public void setAverageSatisfactionRating(double averageSatisfactionRating) {
        this.averageSatisfactionRating = (double) averageSatisfactionRating / 3;
    }

    public void setSatisfactionRating(double satisfactionRating) {
        this.satisfactionRating = this.satisfactionRating + satisfactionRating;
    }

    //
//    }



}

package studentCoursesMgmt.util;

import java.util.Map;
import java.util.List;

public class CourseImpl implements CourseInterface{

    private int courseCapacity, courseTime;
    private String courseName;

    public CourseImpl(String line) {

        /*
        * Constructor that receives a line and processes it into the define fields.
        */

        try
        {
            String[] splitString =line.split(":");
            courseName = splitString[0];
            courseCapacity = Integer.parseInt(splitString[1]);
            courseTime = Integer.parseInt(splitString[2]);

        }
        catch (NumberFormatException ne)
        {
            System.out.println("Unable to parse integer");
        }

    }

    @Override
    public String getCourseName() {
        return courseName;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public int getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    @Override
    public int getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }

    @Override
    public String toString() {
        return "CourseImpl{" +
                "courseCapacity=" + courseCapacity +
                ", courseTime=" + courseTime +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}

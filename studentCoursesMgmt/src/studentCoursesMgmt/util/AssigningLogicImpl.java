package studentCoursesMgmt.util;

import java.util.*;

public class AssigningLogicImpl implements AssigningLogicInterface {

    //

//    public AssigningLogicImpl() {}
    public Map<Integer, List<String>> assignCourses(List<StudentImpl> studentPrefsArray, List<CourseImpl> courseArray) {
        Map<Integer, List<String>> assignedCourses = new HashMap<>();
        for (StudentImpl student : studentPrefsArray) {
            List<String> studentCoursePreferences = student.getPreferences();
            for (int preference = 0; preference < 9; preference++) {

                if (student.getStudentCourseCount() < 3) {
                    /* if(capacityCheck(studentCoursePreferences.get(preference), courseArray) && noTimeClashCheck(student, courseArray)){} */

                    if (capacityCheck(studentCoursePreferences.get(preference), courseArray)) {
                        student.addCourse(studentCoursePreferences.get(preference));
                        //adds student course to list and increments got course by 1
                        for (CourseImpl course : courseArray) {
                            if (course.getCourseName().equals(studentCoursePreferences.get(preference))){
                                course.setCourseCapacity(course.getCourseCapacity() - 1);
                            }
                        }

                    } else {
                        System.out.println("Course is full, assigning next preference");
                    }
                }
                // add to map
                assignedCourses.put(student.getStudentId(), student.getAssignedCourses());

            }
        }
        return assignedCourses;
    }

    public boolean capacityCheck(String courseName, List<CourseImpl> courseArray) {
        for (CourseImpl course : courseArray) {
            if (course.getCourseName().equals(courseName)) {
                if (course.getCourseCapacity() > 0) {
                    System.out.println("the course Capacity is " + course.getCourseCapacity());
//                    course.setCourseCapacity(course.getCourseCapacity() - 1);
//                    System.out.println("the course Capacity now, after assigning " + course.getCourseCapacity());
                    return true;
                } else {
                    return false;

                }
            }
        }
        return false;
    }
}

//    public boolean noTimeClashCheck(StudentImpl student, List<CourseImpl> courseArray) {
//        //if student assigned courses is empty
//        if (student.getAssignedCourses().isEmpty()){
//            //no timeClash
//            return true;
//        }
//        else {
//            List<String> assignedCourses = new ArrayList<>();
//            for (int courseAssigned = 0; courseAssigned < 3; courseAssigned++) //max assigned courses
//            {
//                for(int course = 0; course < 9; course ++ )
//                {
//
//                }
//            }
//        }
//    }



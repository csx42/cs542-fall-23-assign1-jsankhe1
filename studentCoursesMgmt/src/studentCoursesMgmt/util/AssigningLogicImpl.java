package studentCoursesMgmt.util;

import java.util.*;

public class AssigningLogicImpl implements AssigningLogicInterface {


    public List<String> regConflicts = new ArrayList<>();
    // CREATE a  result object


    public Map<Integer, List<String>> assignCourses(List<StudentImpl> studentPrefsArray,
                                                    List<CourseImpl> courseArray,
                                                    Results result,
                                                    String regResults,
                                                    String regConflicts,,
                                                    String errorLog) {
        Map<Integer, List<String>> assignedCourses = new HashMap<>();
        for (StudentImpl student : studentPrefsArray) {
            List<String> studentCoursePreferences = student.getPreferences();
            int satisfactionRating = 9; // 9 is the highest satisfaction rating, if first preference is assigned,
            // satisfaction rating is 9 and stored into student object, if not assigned, we decrement it without storing it.
            for (int preference = 0; preference < courseArray.size(); preference++) {


                if (student.getStudentCourseCount() < 3) {
                    if (capacityCheck(studentCoursePreferences.get(preference), courseArray) &&
                            noTimeClashCheck(studentCoursePreferences.get(preference), student, courseArray, timeSlotMapping(courseArray))) {
                        student.addCourse(studentCoursePreferences.get(preference));
                        student.setSatisfactionRating(satisfactionRating);
                        // update the course capacity
                        satisfactionRating--;
                        //adds student course to list and increments got course by 1, update the course capacity
                        //below loop is to ensure we decrement the correct course capacity.
                        for (CourseImpl course : courseArray) {
                            if (course.getCourseName().equals(studentCoursePreferences.get(preference))) {
                                course.setCourseCapacity(course.getCourseCapacity() - 1);
                            }
                        }

                    } else {
                        String reason = "";

                        //if capacity check fails or timeslotclash, we move to the next preference
                        // if capacity check fails, we write the student name, the course that couldnt be assigned and the reason why it couldnt be assigned
                        // if timeslot clash, we write the student name, the course that couldnt be assigned and the reason why it couldnt be assigned to regConflicts
                        if (!capacityCheck(studentCoursePreferences.get(preference), courseArray)) {
                            reason = " Capacity Check Failed, course is at full capacity";
                            //use writeRegistrationConflictsToFile to write to file
                            result.writeRegistrationConflictsToFile("registration_conflicts.txt", student.getStudentId(), studentCoursePreferences.get(preference), reason);
                        } else if (!noTimeClashCheck(studentCoursePreferences.get(preference), student, courseArray, timeSlotMapping(courseArray))) {
                            reason = " Time Clash Check Failed with another course";
                            //use writeRegistrationConflictsToFile to write to file
                            result.writeRegistrationConflictsToFile("registration_conflicts.txt", student.getStudentId(),
                                                                                            studentCoursePreferences.get(preference), reason);
                            regConflicts.add(student.getStudentId() + " couldn't be asignned course:" + studentCoursePreferences.get(preference) + ". Reason: " + "Time Clash Check Failed with another course");
                        }
                        // continue to next preference
                        satisfactionRating--;
                        continue;


                    }
                }
                assignedCourses.put(student.getStudentId(), student.getAssignedCourses());

            }
        }
        System.out.println(timeSlotMapping(courseArray));
        System.out.println("\nFollowing is the registration conflicts " + regConflicts + "\n");
        return assignedCourses;
    }

    public boolean capacityCheck(String courseName, List<CourseImpl> courseArray) {
        for (CourseImpl course : courseArray) {
            if (course.getCourseName().equals(courseName)) {
                if (course.getCourseCapacity() > 0) {
                    System.out.println("the course Capacity is " + course.getCourseCapacity());
                    return true;
                }
            }
        }
        return false;
    }

    public Map<Integer, List<String>> timeSlotMapping(List<CourseImpl> courseArray) {
        Map<Integer, List<String>> timeSlotMap = new HashMap<>();
        for (CourseImpl course : courseArray) {
            if (timeSlotMap.containsKey(course.getCourseTime())) {
                List<String> courseList = timeSlotMap.get(course.getCourseTime());
                courseList.add(course.getCourseName());
                timeSlotMap.put(course.getCourseTime(), courseList);
            } else {
                List<String> courseList = new ArrayList<>();
                courseList.add(course.getCourseName());
                timeSlotMap.put(course.getCourseTime(), courseList);
            }
        }
        return timeSlotMap;
    }

    public boolean noTimeClashCheck(String courseName, StudentImpl student, List<CourseImpl> courseArray, Map<Integer, List<String>> timeSlotMap) {
        //if student assigned courses is empty

        if (student.getAssignedCourses().isEmpty()) {
            //no timeClash
            return true;
        } else {
            //if student assigned courses is not empty
            //get the timeSlot of the courseName
            int courseTimeSlot = 0;
            for (CourseImpl course : courseArray) {
                if (course.getCourseName().equals(courseName)) {
                    courseTimeSlot = course.getCourseTime();
                }
            }
            //get the list of courses that have the same timeSlot
            List<String> coursesWithSameTimeSlot = timeSlotMap.get(courseTimeSlot);
            //iterate over the list of courses that have the same timeSlot
            for (String course : coursesWithSameTimeSlot) {
                //if the student assigned courses contains any of the courses with the same timeSlot
                if (student.getAssignedCourses().contains(course)) {
                    //timeClash
                    return false;
                }
            }
            //no timeClash
            return true;
        }
    }
}
//}



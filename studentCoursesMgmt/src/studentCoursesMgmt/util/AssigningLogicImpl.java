package studentCoursesMgmt.util;

import java.util.*;

public class AssigningLogicImpl implements AssigningLogicInterface {

    /**
     * Assign courses to students based on their preferences while considering
     * course capacities and time slot clashes.
     * 
     * @param studentPrefsArray List of StudentImpl objects with student
     *                          preferences.
     * @param courseArray       List of CourseImpl objects with course information.
     * @param result            Results object to handle writing to files.
     * @param regResults        The registration results file name.
     * @param regConflicts      The registration conflicts file name.
     * @param errorLog          The error log file name.
     * @return A map of student IDs to lists of assigned courses.
     */
    @Override
    public Map<Integer, List<String>> assignCourses(List<StudentImpl> studentPrefsArray,
            List<CourseImpl> courseArray,
            Results result,
            String regResults,
            String regConflicts,
            String errorLog) {
        Map<Integer, List<String>> assignedCourses = new HashMap<>();
        for (StudentImpl student : studentPrefsArray) {
            // if student id already exists in the map, we send that to error log as
            // duplicate entry and skip it
            if (assignedCourses.containsKey(student.getStudentId())) {
                String reason = "Duplicate entry for student id: " + student.getStudentId()
                        + ". Writing to ErrorLog, Skipping this entry.";
                result.writeErrorToFile(errorLog, reason);
                continue;
            }
            List<String> studentCoursePreferences = student.getPreferences();
            int satisfactionRating = 9; // 9 is the highest satisfaction rating, if first preference is assigned,
            // satisfaction rating is 9 and stored into student object, if not assigned, we
            // decrement it without storing it.
            for (int preference = 0; preference < courseArray.size(); preference++) {

                if (student.getStudentCourseCount() < 3) {
                    if (capacityCheck(studentCoursePreferences.get(preference), courseArray) &&
                            noTimeClashCheck(studentCoursePreferences.get(preference), student, courseArray,
                                    timeSlotMapping(courseArray))) {
                        student.addCourse(studentCoursePreferences.get(preference));
                        student.setSatisfactionRating(satisfactionRating);
                        // update the course capacity
                        satisfactionRating--;
                        // adds student course to list and increments got course by 1, update the course
                        // capacity
                        // below loop is to ensure we decrement the correct course capacity.
                        for (CourseImpl course : courseArray) {
                            if (course.getCourseName().equals(studentCoursePreferences.get(preference))) {
                                course.setCourseCapacity(course.getCourseCapacity() - 1);
                            }
                        }

                    } else {
                        String reason;

                        // if capacity check fails or timeslotclash, we move to the next preference
                        // if capacity check fails, we write the student name, the course that couldnt
                        // be assigned and the reason why it couldnt be assigned
                        // if timeslot clash, we write the student name, the course that couldnt be
                        // assigned and the reason why it couldnt be assigned to regConflicts
                        if (!capacityCheck(studentCoursePreferences.get(preference), courseArray)) {
                            reason = student.getStudentId() + " couldn't be assigned course:" +
                                    studentCoursePreferences.get(preference) + ". Reason: " +
                                    "Course is at capacity. Moving to next preference";
                            // use writeRegistrationConflictsToFile to write to file
                            result.writeErrorToFile(errorLog, reason);
                        } else if (!noTimeClashCheck(studentCoursePreferences.get(preference), student, courseArray,
                                timeSlotMapping(courseArray))) {
                            reason = student.getStudentId() + " couldn't be assigned course:" +
                                    studentCoursePreferences.get(preference) + ". Reason: " +
                                    "Time Clash Check Failed with another course";
                            // use writeRegistrationConflictsToFile to write to file
                            result.writeRegistrationConflictsToFile(regConflicts, reason);
                            // regConflicts.add(student.getStudentId() + " couldn't be asignned course:" +
                            // studentCoursePreferences.get(preference) + ". Reason: " + "Time Clash Check
                            // Failed with another course");
                        }
                        // continue to next preference
                        satisfactionRating--;
                        continue;

                    }
                }
                assignedCourses.put(student.getStudentId(), student.getAssignedCourses());

            }
            // if after checking all preferences and student has only 1 or 2 courses or none
            // at all, we just add empty spaces to the list where
            // the course should have been assigned.
            // if student has 3 courses assigned, we dont need to do anything.

            if (student.getStudentCourseCount() < 3) {
                int emptySpaces = 3 - student.getStudentCourseCount();
                for (int i = 0; i < emptySpaces; i++) {
                    student.addCourse(" ");
                }
                // System.out.println("Debug-> less than 3 student courses:" +
                // student.getAssignedCourses());
                assignedCourses.put(student.getStudentId(), student.getAssignedCourses());
            }
        }
        return assignedCourses;
    }

    /**
     * Check if the course capacity is greater than 0, if yes, return true, else
     * return false
     * 
     * @param courseName  name of the course
     * @param courseArray List of CourseImpl objects with course information.
     * @return true if course capacity is greater than 0, else false
     */
    @Override
    public boolean capacityCheck(String courseName, List<CourseImpl> courseArray) {
        for (CourseImpl course : courseArray) {
            if (course.getCourseName().equals(courseName)) {
                if (course.getCourseCapacity() > 0) {
                    // System.out.println("the course Capacity is " + course.getCourseCapacity());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Create a map of timeSlot to list of courses that have the same timeSlot
     * 
     * @param courseArray List of CourseImpl objects with course information.
     * @return timeSlotMap Map of timeSlot to list of courses that have the same
     *         timeSlot
     */
    @Override
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

    /**
     * Return a boolean True if no tiime clash between two courses exists, else
     * return false
     * 
     * @param courseName  String name of the course
     * @param student     StudentImpl object with student information.
     * @param courseArray List of CourseImpl objects with course information.
     * @param timeSlotMap Map of timeSlot to list of courses that have the same
     *                    timeSlot
     * @return true if no time clash between two courses exists, else return false
     */
    @Override
    public boolean noTimeClashCheck(String courseName, StudentImpl student, List<CourseImpl> courseArray,
            Map<Integer, List<String>> timeSlotMap) {
        // if student assigned courses is empty

        if (student.getAssignedCourses().isEmpty()) {
            // no timeClash
            return true;
        } else {
            // if student assigned courses is not empty
            // get the timeSlot of the courseName
            int courseTimeSlot = 0;
            for (CourseImpl course : courseArray) {
                if (course.getCourseName().equals(courseName)) {
                    courseTimeSlot = course.getCourseTime();
                }
            }
            // get the list of courses that have the same timeSlot
            List<String> coursesWithSameTimeSlot = timeSlotMap.get(courseTimeSlot);
            // iterate over the list of courses that have the same timeSlot
            for (String course : coursesWithSameTimeSlot) {
                // if the student assigned courses contains any of the courses with the same
                // timeSlot
                if (student.getAssignedCourses().contains(course)) {
                    // timeClash
                    return false;
                }
            }
            // no timeClash
            return true;
        }
    }
}

# CSX42: Assignment 1
## Name: Jay Balaram Sankhe
-----------------------------------------------------------------------
-----------------------------------------------------------------------

## IMPORTANT NOTES:
### * Program expects the coursePrefs.txt and courseInfo.txt to be given in the exact order as mentioned in run command.


Following are the commands and the instructions to run ANT on your project.
* #### Note: build.xml is present in studentCoursesMgmt/src folder.

* ####  regResults.txt, regConflicts.txt and errorLog.txt are the output files generated in the studentCoursesMgmt folder
* #### They should be manually deleted before running the program again.
* #### The program will not overwrite the files if they already exist, it will append the data to the existing files.

-----------------------------------------------------------------------
## Instruction to clean:

#### Command: ant -buildfile studentCoursesMgmt/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.


-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:
## RECOMMENDED COMMAND TO RUN THE PROGRAM IS BELOW
#### ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=coursePrefs.txt -Darg1=courseInfo.txt -Darg2=regResults.txt -Darg3=regConflicts.txt -Darg4=errorLog.txt

-----------------------------------------------------------------------

####Command: ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=<input_file.txt> -Darg1=<delete_file.txt> -Darg2=<output1_file.txt> -Darg3=<output2_file.txt> -Darg4=<output3_file.txt>

## Replace <fileName.txt> with real file names. For example, if the files are available in the path,
## you can run it in the following manner:

## RECOMMENDED COMMAND TO RUN THE PROGRAM IS BELOW
ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=coursePrefs.txt -Darg1=courseInfo.txt -Darg2=regResults.txt -Darg3=regConflicts.txt -Darg4=errorLog.txt

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:
* The program takes the command line arguments of 5 file names, 
the first two file names are the input files in the directory.
* The third, fourth and fifth file names are the names that will be used to create the output files.
* The Driver program  invokes the FileProcessor class to read the input files and store the data in the data structures.
* The Driver program then invokes the Results class to write the output to the output files.
* The Driver program then invokes the AssignningLogic class to assign the courses to the students.
* The AssigningLogic class has the following methods
* * assignCourses() - This method assigns the courses to the students based on the preferences and the availability of the courses.
* * * returns a Map of the student and the courses assigned to them.
* * noTimeClashCheck() - This method checks if there is a time clash between the courses to be assigned to the student.
* * capacityCheck() - This method checks if the capacity of the course is full or not.
* * timeSlotMapping() - This method maps the time slot to the courses.
* The Driver program then sends the map obtained from the AssigningLogic class 
to the Results class to write the output to the output files.
* Asks User if they want the output to be printed on the console via Y or N input.
---------------------------------------------------------------------------------
## Algorithm Used: 
* The algorithm used to assign the courses to the students is a FCFS
* It resides in AssigningLogic class.
* The algorithm is as follows:
### Initialization:

A hashmap (assignedCourses) is initialized to hold the assigned courses against each student's ID.
The program loops through each student's preferences.
Duplicate Student Entry Check:

For every student, the program first checks if the student ID is already processed (exists in assignedCourses). If so, the student is considered a duplicate, logged as an error, and skipped.
### Course Assignment:

For each course preference of a student, the algorithm checks:
If the student has already been assigned 3 courses. If not:
If the preferred course is available (using the capacityCheck method).
If there is no time clash with already assigned courses to the student (using the noTimeClashCheck method).
If both checks pass, the course is assigned to the student, and the course capacity is decremented.
### Error Logging:

If a student cannot be assigned a course due to capacity or time clash, an appropriate error message is logged.
Capacity issues are logged to errorLog.
Time clashes are logged to regConflicts.
### Incomplete Assignments Handling:

After checking all course preferences for a student:
If the student is assigned fewer than 3 courses, the remaining course slots are filled with empty spaces.
The student's final course assignment list is added to the assignedCourses hashmap.
### Supporting Functions:

capacityCheck checks if there's any remaining capacity for a given course.
timeSlotMapping creates a map of courses based on their time slots, which is used to detect clashes.
noTimeClashCheck ensures there's no time clash between courses that a student is taking.
-----------------------------------------------------------------------
## DATASTRUCTURES USED
### The data structures used are as follows:
* **HashMap** - To store the student ID and the courses assigned to them.
  * Justification - HashMap is used because 
  it provides constant time performance for the basic operations like get and put.
  * The studenId was used as the key and the courses assigned to them were used as the value.
  * As there were multiple courses assigned to a student, the value was a list of courses.
  * Thus, I used a HashMap<String, List<String>> to store the data.
* **ArrayList** - To store the student objects and the course objects.
  *  Arrays are fixed in size. 
  * Opting for an ArrayList provides the flexibility of a dynamically resizing array, 
  * which is ideal for scenarios where we aren't certain about the number of elements in advance, 
  * such as number of students. (The number of students was not fixed, changes according to input)

-----------------------------------------------------------------------
## Complexity Analysis of Algorithm:
* 1st LOOP - runs for each student in studentList, let size of studentList be n
* 2nd inner loop runs for size of coursePrefsList, let size of coursePrefsList be m
* in the 2nd loop, the following methods are called:
* * capacityCheck() - runs in constant time for size of coursePrefsList, let size of coursePrefsList be m
* * timeSlotMapping() - runs in constant time for size of coursePrefsList, let size of coursePrefsList be m
* * noTimeClashCheck() - runs in constant time for size of coursePrefsList and
* **Thus the complexity of the algorithm is O(n*m)**
-----------------------------------------------------------------------
## Ending Notes:
* storeCourseInfo and storeStudentInfo methods in FileProcessor class are used to store the data in the data structures.
* They are purely utility methods and thus I did not create a interface for them.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.""

Date: -- 09/28/2023



package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.List;

public class CourseInfoGeneratorImpl implements CourseInfoGeneratorInterface {
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

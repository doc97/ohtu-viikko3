package ohtu;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel Riissanen on 14.11.2018.
 */
public class CourseLibrary {

    private Map<String, Course> courses;

    public CourseLibrary() {
        courses = new HashMap<>();
    }

    public void setCourses(Course[] courses) {
        this.courses.clear();
        for (Course course : courses)
            this.courses.putIfAbsent(course.getName(), course);
    }

    public Course getCourseByName(String name) {
        return courses.getOrDefault(name, null);
    }
}

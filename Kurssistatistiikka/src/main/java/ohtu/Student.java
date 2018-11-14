package ohtu;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Daniel Riissanen on 14.11.2018.
 */
public class Student {
    private String studentNumber;
    private CourseSubmissions[] submissions;
    private CourseLibrary courseLib;
    private int exerciseCount;
    private int hourCount;

    public Student(String studentNumber, CourseLibrary courseLib) {
        this.studentNumber = studentNumber;
        this.courseLib = courseLib;
    }

    private Course[] getSubmittedCourses(Submission[] submissions) {
        Set<String> courseSet = new HashSet<>();
        for (Submission sub : submissions)
            courseSet.add(sub.getCourse());
        String[] courseNames = courseSet.toArray(new String[0]);
        Course[] courses = new Course[courseNames.length];
        for (int i = 0; i < courseNames.length; i++)
            courses[i] = courseLib.getCourseByName(courseNames[i]);
        return courses;
    }

    private static CourseSubmissions[] convertToCourseSubmissions(Submission[] submissions, Course[] courses) {
        CourseSubmissions[] courseSubs = new CourseSubmissions[courses.length];
        for (int i = 0; i < courses.length; i++) {
            courseSubs[i] = new CourseSubmissions();
            courseSubs[i].setCourse(courses[i]);
            for (Submission sub : submissions) {
                if (sub.getCourse().equals(courses[i].getName()))
                    courseSubs[i].addSubmission(sub);
            }
        }
        return courseSubs;
    }

    public void setSubmissions(Submission[] subs) {
        exerciseCount = 0;
        hourCount = 0;
        for (Submission sub : subs) {
            exerciseCount += sub.getExerciseCount();
            hourCount += sub.getHours();
        }
        Course[] courses = getSubmittedCourses(subs);
        submissions = convertToCourseSubmissions(subs, courses);
    }

    public CourseSubmissions[] getSubmissions() {
        return submissions;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getExerciseCount() {
        return exerciseCount;
    }

    public int getHourCount() {
        return hourCount;
    }
}

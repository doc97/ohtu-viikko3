package ohtu;

/**
 * Created by Daniel Riissanen on 14.11.2018.
 */
public class CourseSubmissions {
    private Course course;
    private Submission[] weeklySubs;

    public CourseSubmissions() {
        weeklySubs = new Submission[0];
    }

    public void setCourse(Course course) {
        this.course = course;
        weeklySubs = new Submission[course.getWeekCount()];
    }

    public Course getCourse() {
        return course;
    }

    public void addSubmission(Submission sub) {
        weeklySubs[sub.getWeek()] = sub;
    }

    public Submission getWeeklySubmission(int week) {
        if (week < 0 || week >= weeklySubs.length)
            return null;
        return weeklySubs[week];
    }

    public int getSubmittedExerciseCount() {
        int count = 0;
        for (Submission sub : weeklySubs) {
            if (sub != null)
                count += sub.getExerciseCount();
        }
        return count;
    }

    public int getSubmittedHourCount() {
        int count = 0;
        for (Submission sub : weeklySubs) {
            if (sub != null)
                count += sub.getHours();
        }
        return count;
    }
}

package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ohtu.stats.CourseStats;
import ohtu.stats.WeekStats;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class Main {

    private static Submission[] loadSubmissions(String studentNr) throws IOException {
        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        return mapper.fromJson(bodyText, Submission[].class);
    }

    private static Course[] loadCourses() throws IOException {
        String url = "https://studies.cs.helsinki.fi/courses/courseinfo/";
        String body = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        return mapper.fromJson(body, Course[].class);
    }

    private static CourseStats loadCourseStats(String name) throws IOException {
        String url = "https://studies.cs.helsinki.fi/courses/" + name + "/stats";
        String text = Request.Get(url).execute().returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonObject data = parser.parse(text).getAsJsonObject();

        CourseStats stats = new CourseStats();
        WeekStats[] weeklyStats = new WeekStats[data.keySet().size()];
        Gson mapper = new Gson();
        int i = 0;
        for (String key : data.keySet()) {
            JsonObject statsData = data.getAsJsonObject(key);

            WeekStats weekStats = mapper.fromJson(statsData, WeekStats.class);
            weeklyStats[i++] = weekStats;
        }
        stats.setWeeklyStats(weeklyStats);
        return stats;
    }

    private static void printStudentData(Student student) throws IOException {
        System.out.println("Opiskelijanumero: " + student.getStudentNumber() + "\n");
        for (CourseSubmissions courseSub : student.getSubmissions()) {
            Course course = courseSub.getCourse();
            CourseStats stats = loadCourseStats(course.getName());

            System.out.println(course.getTitle() + "\n");
            for (int weekNr = 1; weekNr <= course.getWeekCount(); weekNr++) {
                Submission sub = courseSub.getWeeklySubmission(weekNr);
                if (sub == null)
                    continue;

                System.out.println("viikko " + weekNr + ":");
                System.out.println(" tehtyjä tehtäviä " + sub.getExerciseCount() + "/" + course.getExerciseCount(weekNr)
                        + ", aikaa kului " + sub.getHours() + " tuntia"
                        + ", tehdyt tehtävät: " + sub.getExerciseString());
            }
            System.out.println("\nYhteensä: " + courseSub.getSubmittedExerciseCount() + "/" + course.getTotalExerciseCount()
                    + " tehtävää, " + courseSub.getSubmittedHourCount() + " tuntia");
            System.out.println("\nKursilla yhteensä " + stats.getTotalSubmissions() + " palautusta"
                    + ", palautettuja tehtäviä " + stats.getTotalExercisesReturned() + " kpl"
                    + ", aikaa käytetty yhteensä " + stats.getTotalHours() + " tuntia");
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = args.length > 0 ? args[0] : "012345678";
        Submission[] subs = loadSubmissions(studentNr);
        Course[] courses = loadCourses();

        CourseLibrary courseLib = new CourseLibrary();
        courseLib.setCourses(courses);

        Student student = new Student(studentNr, courseLib);
        student.setSubmissions(subs);

        printStudentData(student);
    }
}

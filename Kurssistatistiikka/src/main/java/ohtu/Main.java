package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class Main {

    private static void printStudentData(String studentNr, Submission[] submissions) {
        int nExercises = 0;
        int nHours = 0;
        System.out.println("opiskelijanumero: " + studentNr + "\n");
        for (Submission sub : submissions) {
            nExercises += sub.getExerciseCount();
            nHours += sub.getHours();
            System.out.println("  " + sub);
        }
        System.out.println("\nYhteensä: " + nExercises + " tehtävää, " + nHours + " tuntia");
    }

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        printStudentData(studentNr, subs);
    }
}

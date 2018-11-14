package ohtu;

import java.util.Arrays;

public class Submission {

    private int week;
    private int hours;
    private int[] exercises;
    private String course;

    private Submission() {
        exercises = new int[0];
    }

    public int getHours() {
        return hours;
    }

    public int getExerciseCount() {
        return exercises.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(course).append(" (viikko ").append(week).append("): ")
                .append("Tehtäviä tehty ").append(exercises.length)
                .append(", aikaa käytetty ").append(hours).append(" tuntia")
                .append(", tehtävät: ").append(Arrays.toString(exercises));
        return builder.toString();
    }
}
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

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public int getExerciseCount() {
        return exercises.length;
    }

    public String getCourse() {
        return course;
    }

    public String getExerciseString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < exercises.length; i++) {
            builder.append(exercises[i]);
            if (i + 1 < exercises.length)
                builder.append(", ");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("viikko ").append(week).append(":\n")
                .append(" tehtyjä tehtäviä ").append(exercises.length)
                .append(", aikaa käytetty ").append(hours).append(" tuntia")
                .append(", tehtävät: ").append(Arrays.toString(exercises));
        return builder.toString();
    }
}
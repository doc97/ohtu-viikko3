package ohtu;

/**
 * Created by Daniel Riissanen on 14.11.2018.
 */
public class Course {
    private String fullName;
    private String name;
    private String url;
    private String term;
    private int year;
    private int[] exercises;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return fullName + " " + term + " " + year;
    }

    public int getExerciseCount(int week) {
        if (week < 0 || week >= exercises.length)
            return 0;
        return exercises[week];
    }

    public int getTotalExerciseCount() {
        int sum = 0;
        for (int count : exercises)
            sum += count;
        return sum;
    }

    public int getWeekCount() {
        return exercises.length - 1;
    }

    @Override
    public String toString() {
        return name + " (" + url + ")";
    }
}

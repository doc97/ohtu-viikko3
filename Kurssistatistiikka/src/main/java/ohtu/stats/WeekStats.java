package ohtu.stats;

/**
 * Created by Daniel Riissanen on 14.11.2018.
 */
public class WeekStats {
    private int students;
    private float hour_total;
    private int exercise_total;
    private Integer[] hours;
    private Integer[] exercises;

    public float getTotalHours() {
        return hour_total;
    }

    public int getTotalExercises() {
        return exercise_total;
    }

    public int getStudentCount() {
        return students;
    }
}

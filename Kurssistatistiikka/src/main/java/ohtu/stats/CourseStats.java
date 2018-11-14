package ohtu.stats;

/**
 * Created by Daniel Riissanen on 14.11.2018.
 */
public class CourseStats {

    private WeekStats[] weeklyStats;
    private float totalHours;
    private int totalSubmissions;
    private int totalExercisesReturned;

    public CourseStats() {
        weeklyStats = new WeekStats[0];
    }

    private void refresh() {
        totalHours = 0;
        totalSubmissions = 0;
        totalExercisesReturned = 0;
        for (WeekStats stats : weeklyStats) {
            totalHours += stats.getTotalHours();
            totalSubmissions += stats.getStudentCount();
            totalExercisesReturned += stats.getTotalExercises();
        }
    }

    public void setWeeklyStats(WeekStats[] weeklyStats) {
        this.weeklyStats = weeklyStats;
        refresh();
    }

    public float getTotalHours() {
        return totalHours;
    }

    public int getTotalSubmissions() {
        return totalSubmissions;
    }

    public int getTotalExercisesReturned() {
        return totalExercisesReturned;
    }
}
package seedu.jaweeper;

/**
 * Tracks elapsed time for a game session.
 */
public class GameTimer {
    private long startTime;
    private long elapsedMillis;
    private boolean isRunning;

    public GameTimer() {
        this.startTime = 0;
        this.elapsedMillis = 0;
        this.isRunning = false;
    }

    /**
     * Starts or resumes timing, keeping any time already counted.
     * Does nothing if the timer is already running, so a running interval is never lost.
     */
    public void start() {
        if (isRunning) {
            return;
        }
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }

    /**
     * Begins timing a brand-new game: clears any earlier time, then starts from zero.
     */
    public void restart() {
        reset();
        start();
    }

    public void stop() {
        if (isRunning) {
            long endTime = System.currentTimeMillis();
            elapsedMillis += (endTime - startTime);
            isRunning = false;
        }
    }

    public void reset() {
        this.startTime = 0;
        this.elapsedMillis = 0;
        this.isRunning = false;
    }
    public void resumeFrom(long previousElapsedMillis) {
        assert previousElapsedMillis >= 0 : "Elapsed time cannot be negative";
        this.elapsedMillis = previousElapsedMillis;
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }

    /**
     * Returns the exact elapsed time in milliseconds, including any running interval.
     */
    public long getElapsedMillis() {
        long totalMillis = elapsedMillis;
        if (isRunning) {
            totalMillis += (System.currentTimeMillis() - startTime);
        }
        return totalMillis;
    }


    public long getElapsedTimeSeconds() {
        long totalMillis = elapsedMillis;
        if (isRunning) {
            totalMillis += (System.currentTimeMillis() - startTime);
        }
        // Ensure quick games register at least 1 second instead of rounding down to 0
        if (totalMillis > 0 && totalMillis < 1000) {
            return 1;
        }
        return totalMillis / 1000;
    }
}

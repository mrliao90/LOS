package cn.los.common.util;

public class TimeRecorder {
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void end() {
        endTime = System.currentTimeMillis();
    }

    public long getDuration() {
        return endTime - startTime;
    }
}

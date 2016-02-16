package sample.GardenKit.src.Common;

/**
 * Created by nayan on 2/15/16.
 */
public interface ScheduledJob {

    public void setScheduledStart(int startTime);

    public void setScheduledEnd(int endTime);

    public int getScheduledStart();

    public int getScheduledEnd();

}

package sample.GardenKit.src.Nature.GardenCalendar;

/**
 * Created by nayan on 2/14/16.
 */
public interface TimeListener {
    public void hourChanged(int hour);
    public void dayChanged(int day);
    public void monthChanged(int month);
}

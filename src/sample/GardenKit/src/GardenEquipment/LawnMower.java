package sample.GardenKit.src.GardenEquipment;

import sample.GardenKit.src.Common.ElectronicDevice;
import sample.GardenKit.src.Common.ScheduledJob;

/**
 * Created by nayan on 2/12/16.
 */
public class LawnMower implements ElectronicDevice,ScheduledJob{

    private int scheduledStart;
    private int scheduledEnd;

    @Override
    public boolean isON() {
        return false;
    }

    @Override
    public void switchON() {

    }

    @Override
    public void switchOFF() {

    }

    public void setScheduledStart(int startTime){
        this.scheduledStart = startTime;
    }

    public void setScheduledEnd(int endTime){
        this.scheduledEnd = endTime;
    }

    public int getScheduledStart(){
        return this.scheduledStart;
    }

    public int getScheduledEnd(){
        return this.scheduledEnd;
    }


}

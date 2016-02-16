package sample.GardenKit.src.WateringSystem;

import sample.GardenKit.src.Nature.Phenomena.RainListener;

/**
 * Created by nayan on 2/12/16.
 */
public class WaterTank{

    private int capacity;
    private int waterlevel;
    private boolean outleton;
    private boolean lidopen;

    WaterTank(){

        setTankCapacity(5000);
        setWaterlevel(5000);
        closeLid();
    }

    protected void setWaterlevel(int waterlevel){
        this.waterlevel = waterlevel;
    }
    public void setTankCapacity(int capacity){
        this.capacity = capacity;
    }

    public int getTankCapacity() {
        return this.capacity;
    }

    public int getWaterLevel(){
        return this.waterlevel;
    }

    public void switchONOutlet(){
        this.outleton = true;
    }

    public void switchOFFOutlet(){
        this.outleton = false;
    }

    public boolean isOutletON(){
        if(outleton) return true;
        else return false;
    }

    public void openLid(){
        this.lidopen = true;
    }

    public void closeLid(){
        this.lidopen = false;
    }

    public boolean isLidOpen(){
        if(lidopen) return true;
        else return false;
    }

}

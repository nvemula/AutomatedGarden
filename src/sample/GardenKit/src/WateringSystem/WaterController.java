package sample.GardenKit.src.WateringSystem;

import sample.GardenKit.src.Common.ScheduledJob;
import sample.GardenKit.src.Common.SwitchBoard;
import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.Phenomena.RainListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nayan on 2/14/16.
 */
public class WaterController implements SwitchBoard, RainListener, ScheduledJob {

    private List<Sprinkler> sprinklers;
    private static WaterController board;
    private WaterTank tank;
    private int scheduledStart;
    private int scheduledEnd;

    private WaterController()
    {
        sprinklers = new ArrayList<Sprinkler>();
        tank = new WaterTank();

    }

    public static WaterController getInstance(){

        if(board == null)
            board = new WaterController();
        return board;
    }

    @Override
    public void masterOFF() {
        for(Sprinkler sprinkler : sprinklers)
            sprinkler.switchOFF();
        Log log = new Log();
        String size = String.valueOf(sprinklers.size());
        log.record(size+ " Sprinklers switched off");

    }

    @Override
    public void masterON() {
        for(Sprinkler sprinkler : sprinklers)
            sprinkler.switchON();

        Log log = new Log();
        String size = String.valueOf(sprinklers.size());
        log.record(size+ " Sprinklers switched on");
    }

    @Override
    public void removeSwitch(Object sprinkler) {
        Sprinkler sp = (Sprinkler)sprinkler;
        for(Sprinkler sprinkler1: sprinklers){
            if(sprinkler1 == sp){
                sprinklers.remove(sprinkler1);
                break;
            }
        }

        Log log = new Log();
        log.record("One Sprinkler removed");
    }

    @Override
    public void addSwitch(Object sprinkler) {
        sprinklers.add((Sprinkler) sprinkler);

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

    @Override
    public void raining() {

        Log log = new Log();

        // Turn off outlet
        if(tank.isOutletON()){
            tank.switchOFFOutlet();
            log.record("Water Tank outlet closed");

        }

        //Open lid to store water

        if(!tank.isLidOpen()){
            tank.openLid();
            log.record("Water Tank lid opened");

        }

        masterOFF();
    }

    @Override
    public void rainStopped() {

        Log log = new Log();
        // Turn on outlet
        if(!tank.isOutletON()){
            tank.switchONOutlet();
            log.record("Water Tank outlet opened");

        }

        //Open lid to store water

        if(tank.isLidOpen()){
            tank.closeLid();
            log.record("Water Tank lid closed");

        }
    }
}

package sample.GardenKit.src.Nature.GardenCalendar;

import java.util.*;

/**
 * Created by nayan on 2/13/16.
 */
public class GardenTime {

    private int day = 0;
    private int hours = 0;
    private int minutes = 0;
    private int month = 1;
    private Thread t;
    private static GardenTime clock;
    private List<TimeListener> timeListeners;

    private GardenTime(){
            timeListeners = new ArrayList<TimeListener>();
    }

    public static GardenTime getInstance(){
        if(clock == null){
            clock = new GardenTime();
        }
        return clock;
    }

    public void registerForTime(TimeListener listener){
        timeListeners.add(listener);
    }

    public void dead(TimeListener listener){
        for(TimeListener listener1: timeListeners){
            if(listener == listener1)
                timeListeners.remove(listener);
                break;
        }
    }

    public void runClock(){


        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date());
                incrementTime();



            }
        }, 10*1000,10*1000);
    }

    public void stopClock(){

        if(t.isAlive()){
            t = null;
        }
    }

    public int getDay(){
        return this.day;
    }

    public int getHours(){
        return this.hours;
    }

    public void incrementTime(){
        hours++;

        for(TimeListener listener: timeListeners){
            listener.hourChanged(hours);
        }

        if(hours % 24 == 0){
            day++;
            hours = 0;
            for(TimeListener listener: timeListeners){

                listener.dayChanged(day);
            }
        }

        if(day % 4 == 0){
            month++;
            for(TimeListener listener: timeListeners){

                listener.monthChanged(month);
            }
        }


    }


}

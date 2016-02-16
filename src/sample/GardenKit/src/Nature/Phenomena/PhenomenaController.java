package sample.GardenKit.src.Nature.Phenomena;

import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.GardenCalendar.TimeListener;

import java.util.ArrayList;
import java.util.List;
import sample.GardenKit.src.Nature.Phenomena.ClimateController.*;

/**
 * Created by nayan on 2/15/16.
 */
public class PhenomenaController implements TimeListener{

    private List<RainListener> rainListeners;
    private List<AnimalListener> animalListeners;
    private List<WindListener> windListeners;
    private static PhenomenaController phenomena;
    private List<PhenomenaListener> pListeners;
    private Climate lastClimate;


    private PhenomenaController(){
        rainListeners = new ArrayList<RainListener>();
        animalListeners = new ArrayList<AnimalListener>();
        windListeners = new ArrayList<WindListener>();
        pListeners = new ArrayList<PhenomenaListener>();
        lastClimate = Climate.OVERCAST;
    }

    public static PhenomenaController getInstance(){
        if(phenomena == null){
            phenomena = new PhenomenaController();
        }
        return phenomena;
    }

    public void registerPhenomena(PhenomenaListener p){
        pListeners.add(p);
    }

    public void registerRain(RainListener r){
        rainListeners.add(r);
    }

    public void registerAnimal(AnimalListener a){
        animalListeners.add(a);
    }

    public void registerWind(WindListener w){
        windListeners.add(w);
    }

    @Override
    public void hourChanged(int hour) {

        int rval = (int)(Math.random() * 24 - 0);
        Log log = new Log();

        Climate climate = Climate.SUNNY;

        if(rval % 13 == 0){
            climate = Climate.OVERCAST;
            log.record("Climate changed. It's Overcast");

        }

        else if(rval % 3 == 0){
            climate = Climate.SUNNY;
            log.record("What a bright and Sunny day :)");

        }

        else if(rval % 5 == 0){
            climate = Climate.WINDY;
            log.record("Gosh! It's windy");

            for (WindListener listener: windListeners)
                listener.windy();

        }

        else if(rval % 11 == 0){
            climate = Climate.RAINING;
            log.record("Yay! It's raining :):)");
            for(RainListener listener: rainListeners)
                listener.raining();
        }

        else if(rval % 7 == 0){
            climate = Climate.THUNDER;
            log.record("Thunder!!!!!");

        }

        if(lastClimate != climate) {

            lastClimateChanged(lastClimate);

            lastClimate = climate;

        }
        else {

            // Do nothing

        }

        for(PhenomenaListener listener: pListeners)
            listener.climateChanged(climate);


    }

    @Override
    public void dayChanged(int day) {

    }

    @Override
    public void monthChanged(int month) {

    }

    public void lastClimateChanged(Climate c){

        switch(c) {
            case RAINING:
                for(RainListener r : rainListeners)
                r.rainStopped();

            case WINDY:
                for(WindListener w: windListeners)
                    w.windStopped();


        }
    }

}

package sample.GardenKit.src.Nature.Phenomena;

import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.GardenCalendar.TimeListener;

/**
 * Created by nayan on 2/14/16.
 */
public class ClimateController implements TimeListener,PhenomenaListener {

    public enum Season{
        WINTER,
        SPRING,
        SUMMER,
        FALL
    }

    public enum Climate {
        SUNNY,
        OVERCAST,
        WINDY,
        RAINING,
        THUNDER
    }

    public enum Temperature {
        VERYCOOL,
        COOL,
        NORMAL,
        HOT,
        VERYHOT
    }

    private Season currentSeason;
    private Climate currentClimate;
    private Temperature currentTemperature;
    private static ClimateController climate;

    private ClimateController(){

        currentSeason = Season.WINTER;
        currentClimate = Climate.OVERCAST;
        currentTemperature = Temperature.COOL;

    }

    public static ClimateController getInstance(){

        if(climate == null)
            climate = new ClimateController();
        return climate;
    }

    public Season getCurrentSeason(){
        return this.currentSeason;
    }

    public Climate getCurrentClimate(){
        return this.currentClimate;
    }

    public Temperature getCurrentTemperature(){
        return this.currentTemperature;
    }


    @Override
    public void temperatureChanged(Temperature temp) {

        currentTemperature = temp;
    }

    @Override
    public void climateChanged(Climate climate) {

        currentClimate = climate;

    }

    @Override
    public void hourChanged(int hour) {


    }

    @Override
    public void dayChanged(int days) {


    }

    @Override
    public void monthChanged(int months) {

        String season = "Winter";
        if(months<=3 && months == 12){
            currentSeason = Season.WINTER;
            currentClimate = Climate.OVERCAST;
            currentTemperature = Temperature.VERYCOOL;
            season = "Winter";
        }

        else if(months > 3 && months <=6 ){
            currentSeason = Season.SPRING;
            currentClimate = Climate.SUNNY;
            currentTemperature = Temperature.COOL;
            season = "Spring";
        }

        else if(months > 6 && months <=8){
            currentSeason = Season.SUMMER;
            currentClimate = Climate.SUNNY;
            currentTemperature = Temperature.NORMAL;
            season = "Summer";
        }

        else if(months >= 9 && months < 12 ){

            currentSeason = Season.FALL;
            currentClimate = Climate.OVERCAST;
            currentTemperature = Temperature.NORMAL;
            season = "Fall";
        }

        Log log = new Log();
        log.record("Season changed. Welcome "+ season);
    }


}

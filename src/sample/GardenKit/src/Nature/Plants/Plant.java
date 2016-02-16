package sample.GardenKit.src.Nature.Plants;

import sample.GardenKit.src.HeatingSystem.HeaterListener;
import sample.GardenKit.src.LightingSystem.LightBulb;
import sample.GardenKit.src.LightingSystem.LightListener;
import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.GardenCalendar.TimeListener;
import sample.GardenKit.src.Nature.Phenomena.ClimateController;
import sample.GardenKit.src.Nature.Phenomena.ClimateController.*;
import sample.GardenKit.src.Sensors.Sensor;
import sample.GardenKit.src.WateringSystem.Sprinkler;
import sample.GardenKit.src.WateringSystem.Sprinkler.WaterPressure;
import sample.GardenKit.src.WateringSystem.SprinklerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nayan on 2/13/16.
 */
public class Plant implements TimeListener, SprinklerListener, HeaterListener, LightListener {

    private String name ;
    private Season growthTime;
    private int age;
    private int leaves;
    private int waterlevel;
    private int heatlevel;
    private int lightlevel;
    private int healthlevel;
    private int dirtlevel;
    private List<Sensor> sensors;

    public Plant(String name){

        setName(name);
        age = 1;
        leaves = 0;
        waterlevel = 1;
        heatlevel = 1;
        lightlevel = 1;
        healthlevel = 1;
        dirtlevel = 0;
        Log log = new Log();
        log.record("Planted " + name+" plant");
        sensors = new ArrayList<Sensor>();
    }

    private void setName(String name){
        this.name = name;

    }
    public String getName(){

        return this.name;
    }

    public void addSensor(Sensor sensor){
        sensors.add(sensor);
    }

    public void removeSensor(Sensor sensor){
        for(Sensor s: sensors){
            if(s == sensor){
                sensors.remove(s);
            }
        }
    }

    private void increaseAge(){
        age++;
    }

    private void growLeaves(){
        leaves++;
    }

    public void cutLeaves(){
        leaves--;
    }

    private void absorbWater(WaterPressure pressure){

        if(pressure == WaterPressure.HIGH){
            waterlevel = waterlevel + 3;
        }
        else if(pressure == WaterPressure.MEDIUM){
            waterlevel = waterlevel + 2;
        }
        else if(pressure == WaterPressure.LOW){
            waterlevel = waterlevel + 1;
        }
    }

    private void absorbHeat(){
        heatlevel++;
    }

    private void absorbLight(){
        lightlevel++;
    }

    private void increaseHealth(){
        healthlevel++;
    }

    private void unHealthy(){
        healthlevel--;
    }

    public int getDirtLevel(){

        return this.dirtlevel;
    }

    public int getWaterlevel(){

        return this.waterlevel;
    }

    public void makeDirty(){
        dirtlevel++;
    }

    public void cleanDirt(){
        dirtlevel--;
    }


    @Override
    public void hourChanged(int hour) {
        ClimateController cc = ClimateController.getInstance();
        Climate currentClimate = cc.getCurrentClimate();

        if(currentClimate == Climate.SUNNY){
            absorbHeat();
            absorbLight();
            increaseHealth();
        }

        else if(currentClimate == Climate.RAINING){
            absorbWater(WaterPressure.LOW);
            cleanDirt();
        }

    }

    @Override
    public void dayChanged(int day) {
        increaseAge();
        increaseHealth();
        growLeaves();
    }


    @Override
    public void monthChanged(int month) {
        ClimateController cc = ClimateController.getInstance();

        Season season = cc.getCurrentSeason();

        // If the tree grows only in Fall, kill the plant

        if(season == Season.FALL){
            cutLeaves();
        }
    }

    @Override
    public void watering() {
        absorbWater(WaterPressure.LOW);
        increaseHealth();
        cleanDirt();
    }

    @Override
    public void heating() {
        absorbHeat();
        increaseHealth();
    }


    @Override
    public void lighting() {
        absorbLight();
        increaseHealth();
        growLeaves();
    }
}

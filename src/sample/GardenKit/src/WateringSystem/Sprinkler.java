package sample.GardenKit.src.WateringSystem;

import sample.GardenKit.src.Common.ElectronicDevice;
import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.Plants.Plant;
import sample.GardenKit.src.Sensors.DirtSensor;
import sample.GardenKit.src.Sensors.MoistureSensor;

/**
 * Created by nayan on 2/12/16.
 */
public class Sprinkler implements ElectronicDevice {

    public enum WaterPressure {
        LOW,
        MEDIUM,
        HIGH
    }

    private boolean poweron;
    private WaterPressure pressure;
    private Plant plant;
    private int scheduledStart;
    private int scheduledEnd;
    private MoistureSensor sensor;
    private DirtSensor dirtsensor;

    public Sprinkler(Plant plant){
        setPlant(plant);
        Log log = new Log();
        log.record("New Sprinkler installed");


    }

    private void setPlant(Plant plant){
        this.plant = plant;
    }

    public Plant getPlant(){
        return this.plant;
    }

    public void setMoistureSensor (MoistureSensor sensor){
        this.sensor = sensor;
    }

    public MoistureSensor getMoistureSensor(){
        return this.sensor;
    }

    public void setDirtsensor(DirtSensor sensor){
        this.dirtsensor = sensor;
    }

    public DirtSensor getDirtsensor(){
        return this.dirtsensor;
    }

    public void checkPlantStatus(){
        int a = dirtsensor.getReading();
        int value = (int) getMoistureSensor().getValue();

        if(a > 5 || value < 20){
            switchON();
            plant.cleanDirt();
            plant.watering();
        }
    }

    @Override
    public boolean isON() {
        return false;
    }

    @Override
    public void switchON() {
        this.poweron = true;
    }

    @Override
    public void switchOFF() {
        this.poweron = false;
    }

    public void setWaterPressure(WaterPressure pressure){

        this.pressure = pressure;
    }

    public WaterPressure getWaterPressure(){
        return this.pressure;
    }


}

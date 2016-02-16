package sample.GardenKit.src.Sensors;

import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.Plants.Plant;

/**
 * Created by nayan on 2/12/16.
 */
public class MoistureSensor implements Sensor {

    public enum MoistureUnit {
        PERCENTAGE
    }

    private String name;
    private int value;
    private boolean poweron;
    private MoistureUnit unit;
    private Plant plant;


    public MoistureSensor(Plant plant){
        switchON();
        setName("MoistureSensor");
        setValue(0);
        setUnit(MoistureUnit.PERCENTAGE);
        setPlant(plant);
        Log log = new Log();
        log.record("Moisture Sensor installed for "+plant.getName());

    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object value) {
        int val = (int)value;
        this.value = val;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setUnit(Object unit) {
        MoistureUnit ut = (MoistureUnit) unit;
        this.unit = ut;
    }

    public int getReading(){

        int dlevel = getPlant().getWaterlevel();
        setValue(dlevel);

        return dlevel;
    }

    @Override
    public Object getUnit() {
        return this.unit;
    }

    @Override
    public boolean isON() {

        if(poweron) return true;
        else return false;
    }

    public Plant getPlant(){
        return this.plant;
    }

    private void setPlant(Plant p){
        this.plant = p;
    }

    @Override
    public void switchON() {
        this.poweron = true;
    }

    @Override
    public void switchOFF() {
        this.poweron = false;
    }




}

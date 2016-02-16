package sample.GardenKit.src.Sensors;

import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.Plants.Plant;

/**
 * Created by nayan on 2/12/16.
 */
public class DirtSensor implements Sensor{

    public enum DirtUnit {
        PERCENTAGE
    }

    private String name;
    private int value;
    private boolean poweron;
    private DirtUnit unit;
    private Plant plant;


    public DirtSensor(Plant plant){
        Log log = new Log();
        switchON();
        setName("DirtSensor");
        setValue(0);
        setUnit(DirtUnit.PERCENTAGE);
        setPlant(plant);
        log.record("Dirt Sensor installed for "+plant.getName());

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

    private void setPlant(Plant plant){
        this.plant = plant;
    }

    public Plant getPlant(){
        return this.plant;
    }

    @Override
    public void setUnit(Object unit) {
            DirtUnit du = (DirtUnit) unit;
        this.unit = du;
    }

    @Override
    public Object getUnit() {
        return this.unit;
    }

    public int getReading(){

        int dlevel = getPlant().getDirtLevel();
        setValue(dlevel);

        return dlevel;
    }

    @Override
    public boolean isON() {
        if(poweron) return true;
        else return false;
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

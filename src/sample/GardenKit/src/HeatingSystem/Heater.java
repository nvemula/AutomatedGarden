package sample.GardenKit.src.HeatingSystem;

import sample.GardenKit.src.Common.ElectronicDevice;
import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.Plants.Plant;
import sample.GardenKit.src.Sensors.TemperatureSensor;
import sample.GardenKit.src.Sensors.TemperatureSensor.TemperatureUnit;

/**
 * Created by nayan on 2/12/16.
 */
public class Heater implements ElectronicDevice{

    private int value;
    private TemperatureSensor.TemperatureUnit unit;
    private boolean poweron;
    private Plant plant;

    public Heater(Plant plant){
        setValue(27);
        setPlant(plant);

        setUnit(TemperatureUnit.CELSIUS);
        Log log = new Log();

        log.record("New Heater installed for "+ plant.getName());
    }

    @Override
    public boolean isON() {
        if(poweron) return true;
        else return false;
    }

    @Override
    public void switchON() {
        this.poweron = true;
        plant.heating();


    }

    @Override
    public void switchOFF() {
        this.poweron = false;
    }

    public void setValue(int value){
        this.value = value;
    }

    public float getValue(){
        return this.value;
    }


    private void setPlant(Plant plant){
        this.plant = plant;
    }

    public Plant getPlant(){
        return this.plant;
    }

    public void setUnit(TemperatureUnit unit) {
        TemperatureUnit tu = (TemperatureUnit) unit;
        this.unit = tu;
    }


    public TemperatureUnit getUnit() {
        return this.unit;
    }


}

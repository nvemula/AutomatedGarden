package sample.GardenKit.src.LightingSystem;

import sample.GardenKit.src.Common.ElectronicDevice;
import sample.GardenKit.src.Nature.Plants.Plant;

/**
 * Created by nayan on 2/12/16.
 */
public class LightBulb implements ElectronicDevice {

    public enum ColorTemperature {
        COOL,
        WARM
    }

    public LightBulb(Plant plant){
        setColorTemperature(ColorTemperature.COOL);
        setPlant(plant);
    }

    private boolean poweron;
    private ColorTemperature temperature;
    private Plant plant;


    @Override
    public boolean isON() {
        if(poweron) return true;
        else return false;
    }

    private void setPlant(Plant plant){
        this.plant = plant;
    }

    public Plant getPlant(){
        return this.plant;
    }

    @Override
    public void switchON() {
        this.poweron = true;
        getPlant().lighting();
    }

    @Override
    public void switchOFF() {
        this.poweron = false;
    }

    public void setColorTemperature(ColorTemperature temperature){
        this.temperature = temperature;
    }

    public ColorTemperature getColorTemperature(){
        return this.temperature;
    }
}

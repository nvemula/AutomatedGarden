package sample.GardenKit.src.ProtectionSystem;

import sample.GardenKit.src.Common.ElectronicDevice;
import sample.GardenKit.src.Nature.Plants.Plant;

/**
 * Created by nayan on 2/12/16.
 */
public class MeshProtector implements ElectronicDevice {

    private boolean poweron;
    private Plant plant;

    public MeshProtector(Plant plant){
        switchON();
        setPlant(plant);
    }

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
    }

    @Override
    public void switchOFF() {
        this.poweron = false;
    }
}

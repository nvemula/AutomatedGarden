package sample.GardenKit.src.GardenEquipment;

import sample.GardenKit.src.Common.ElectronicDevice;

/**
 * Created by nayan on 2/12/16.
 */
public class Drone implements ElectronicDevice {

    @Override
    public boolean isON() {
        return false;
    }

    @Override
    public void switchON() {

    }

    @Override
    public void switchOFF() {

    }
}

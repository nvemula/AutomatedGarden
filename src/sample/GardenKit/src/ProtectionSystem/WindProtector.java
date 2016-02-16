package sample.GardenKit.src.ProtectionSystem;

import sample.GardenKit.src.Common.ElectronicDevice;
import sample.GardenKit.src.LoggingSystem.Log;

/**
 * Created by nayan on 2/12/16.
 */
public class WindProtector implements ElectronicDevice{

    private boolean poweron;

    WindProtector(){
        switchON();
    }

    @Override
    public boolean isON() {
        return true;
    }

    @Override
    public void switchON() {
        this.poweron = true;
        Log log = new Log();
        log.record("Wind protector turned on");
    }

    @Override
    public void switchOFF() {
        Log log = new Log();
        log.record("Wind protector turned off");
    }
}

package sample.GardenKit.src.HeatingSystem;

import sample.GardenKit.src.Common.ScheduledJob;
import sample.GardenKit.src.Common.SwitchBoard;
import sample.GardenKit.src.Nature.Phenomena.ClimateController;
import sample.GardenKit.src.Nature.Phenomena.PhenomenaListener;
import sample.GardenKit.src.Sensors.TemperatureSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nayan on 2/12/16.
 */
public class HeatController implements SwitchBoard,ScheduledJob, PhenomenaListener{

    private List<Heater> heaters;
    private static HeatController board;
    private int scheduledStart;
    private int scheduledEnd;

    private HeatController(){

        heaters = new ArrayList<Heater>();

    }

    public static HeatController getInstance(){
        if(board == null){
            board = new HeatController();
        }
        return board;
    }

    public void setScheduledStart(int startTime){
        this.scheduledStart = startTime;
    }

    public void setScheduledEnd(int endTime){
         this.scheduledEnd = endTime;
    }

    public int getScheduledStart(){
        return this.scheduledStart;
    }

    public int getScheduledEnd(){
        return this.scheduledEnd;
    }
    @Override
    public void masterOFF() {

        for(Heater heater : heaters){
            heater.switchOFF();
        }

    }

    @Override
    public void masterON() {

        for(Heater heater : heaters){
            heater.switchON();
        }
    }

    @Override
    public void removeSwitch(Object heater) {

        Heater h = (Heater)heater;
        for(Heater heater1: heaters){
            if(heater1 == h){
                heaters.remove(heater1);
                break;
            }
        }
    }

    @Override
    public void addSwitch(Object heater) {
        heaters.add((Heater) heater);
    }

    @Override
    public void temperatureChanged(ClimateController.Temperature temp) {

        if(temp == ClimateController.Temperature.COOL || temp == ClimateController.Temperature.VERYCOOL){
            int value = (int)TemperatureSensor.getInstance().getValue();

            if(value < 9){
                masterON();
                for(Heater h : heaters){
                    h.setValue(30);
                }
            }
        }

        else {
            masterOFF();
        }
    }

    @Override
    public void climateChanged(ClimateController.Climate climate) {

    }
}

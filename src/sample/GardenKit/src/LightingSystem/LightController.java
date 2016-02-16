package sample.GardenKit.src.LightingSystem;

import sample.GardenKit.src.Common.ScheduledJob;
import sample.GardenKit.src.Common.SwitchBoard;
import sample.GardenKit.src.Nature.Phenomena.ClimateController;
import sample.GardenKit.src.Nature.Phenomena.PhenomenaListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nayan on 2/12/16.
 */
public class LightController implements SwitchBoard, PhenomenaListener{

    private List<LightBulb> lights;
    private static LightController board;

    private LightController(){

        lights = new ArrayList<LightBulb>();

    }

    public static LightController getInstance(){
        if(board == null){
            board = new LightController();
        }
        return board;
    }

    @Override
    public void masterOFF() {
        for(LightBulb bulb : lights){
            bulb.switchOFF();
        }
    }

    @Override
    public void masterON() {
        for(LightBulb bulb : lights){
            bulb.switchON();
        }
    }

    @Override
    public void removeSwitch(Object blb){

        LightBulb bulb = (LightBulb)blb;
        for(LightBulb lb: lights){
            if(lb == bulb){
                lights.remove(lb);
                break;
            }
        }
    }

    @Override
    public void addSwitch(Object bulb) {
        LightBulb lb = (LightBulb) bulb;
        lights.add(lb);
    }

    @Override
    public void temperatureChanged(ClimateController.Temperature temp) {
    }

    @Override
    public void climateChanged(ClimateController.Climate climate) {

        if(climate == ClimateController.Climate.OVERCAST){
            masterON();
        }

        else {
            masterOFF();
        }
    }
}

package sample.GardenKit.src.Nature;

import sample.GardenKit.src.HeatingSystem.HeatController;
import sample.GardenKit.src.HeatingSystem.Heater;
import sample.GardenKit.src.LightingSystem.LightBulb;
import sample.GardenKit.src.LightingSystem.LightController;
import sample.GardenKit.src.Nature.GardenCalendar.GardenTime;
import sample.GardenKit.src.Nature.Plants.Plant;
import sample.GardenKit.src.ProtectionSystem.MeshProtector;
import sample.GardenKit.src.ProtectionSystem.ProtectionController;
import sample.GardenKit.src.Sensors.DirtSensor;
import sample.GardenKit.src.Sensors.MoistureSensor;
import sample.GardenKit.src.WateringSystem.Sprinkler;
import sample.GardenKit.src.WateringSystem.WaterController;

import java.util.List;

/**
 * Created by nayan on 2/13/16.
 */
public class Gardener {


    public void plantTree(Plant plant){

        Garden garden = Garden.getInstance();
        garden.addPlant(plant);

        ProtectionController protectionController = ProtectionController.getInstance();
        protectionController.addSwitch(new MeshProtector(plant));
        MoistureSensor ms = new MoistureSensor(plant);
        plant.addSensor(ms);
        garden.addMoistureSensor(ms);
        Sprinkler sp = new Sprinkler(plant);
        sp.setMoistureSensor(ms);
        WaterController waterController = WaterController.getInstance();
        waterController.addSwitch(sp);
        Heater h = new Heater(plant);
        HeatController heatController = HeatController.getInstance();
        heatController.addSwitch(h);
        DirtSensor ds = new DirtSensor(plant);
        plant.addSensor(ds);
        sp.setDirtsensor(ds);
        garden.addDirtSensor(ds);

        LightBulb lb = new LightBulb(plant);
        LightController lightController = LightController.getInstance();
        lightController.addSwitch(lb);
        GardenTime time = GardenTime.getInstance();
        time.registerForTime(plant);

    }

    public void waterPlants(){

    }

    public void sprayPesticide(){

    }

    public void cleanDirt(){

    }

}

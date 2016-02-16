package sample.GardenKit.src.Nature;

import sample.GardenKit.src.HeatingSystem.HeatController;
import sample.GardenKit.src.HeatingSystem.Heater;
import sample.GardenKit.src.LightingSystem.LightBulb;
import sample.GardenKit.src.LightingSystem.LightController;
import sample.GardenKit.src.Nature.GardenCalendar.GardenTime;
import sample.GardenKit.src.Nature.Insects.Insect;
import sample.GardenKit.src.Nature.Phenomena.ClimateController;
import sample.GardenKit.src.Nature.Phenomena.PhenomenaController;
import sample.GardenKit.src.Nature.Plants.Plant;
import sample.GardenKit.src.ProtectionSystem.MeshProtector;
import sample.GardenKit.src.ProtectionSystem.ProtectionController;
import sample.GardenKit.src.Sensors.DirtSensor;
import sample.GardenKit.src.Sensors.HumiditySensor;
import sample.GardenKit.src.Sensors.MoistureSensor;
import sample.GardenKit.src.Sensors.TemperatureSensor;
import sample.GardenKit.src.WateringSystem.Sprinkler;
import sample.GardenKit.src.WateringSystem.WaterController;

import java.util.ArrayList;
import java.util.List;
import sample.GardenKit.src.LoggingSystem.Log;


/**
 * Created by nayan on 2/13/16.
 */
public class Garden {

        private List<Plant> plants;
        private HeatController heatController;
        private LightController lightController;
        private WaterController waterController;
        private ProtectionController protectionController;
        private static Garden garden;
        private List<Insect> insects;
        private List<DirtSensor> dirtSensors;
        private List<MoistureSensor> moistureSensors;
        private HumiditySensor humiditySensor;
        private TemperatureSensor tempSensor;
        private Garden(){

        }



        public static Garden getInstance(){
                if(garden == null) {
                        garden = new Garden();
                }
                return garden;
        }

        public void bootGarden(){
                GardenTime time = GardenTime.getInstance();
                time.runClock();
                ClimateController climateController = ClimateController.getInstance();
                time.registerForTime(climateController);
                PhenomenaController pC = PhenomenaController.getInstance();
                time.registerForTime(pC);
                pC.registerPhenomena(climateController);

                dirtSensors = new ArrayList<DirtSensor>();

                waterController = WaterController.getInstance();
                pC.registerRain(waterController);

                heatController = HeatController.getInstance();
                protectionController = ProtectionController.getInstance();
                pC.registerWind(protectionController);
                pC.registerPhenomena(heatController);
                lightController = LightController.getInstance();
                pC.registerPhenomena(heatController);

                moistureSensors = new ArrayList<MoistureSensor>();

                plants = new ArrayList<Plant>();
                insects = new ArrayList<Insect>();


                tempSensor =  TemperatureSensor.getInstance();
                tempSensor.switchON();
                time.registerForTime(tempSensor);

                for(int i=0;i<9;i++){
                        Plant plant = new Plant("Orange");
                        plants.add(plant);
                        protectionController.addSwitch(new MeshProtector(plant));
                        MoistureSensor ms = new MoistureSensor(plant);
                        plant.addSensor(ms);
                        moistureSensors.add(ms);
                        Sprinkler sp = new Sprinkler(plant);
                        sp.setMoistureSensor(ms);
                        waterController.addSwitch(sp);
                        Heater h = new Heater(plant);
                        heatController.addSwitch(h);
                        DirtSensor ds = new DirtSensor(plant);
                        plant.addSensor(ds);
                        sp.setDirtsensor(ds);
                        dirtSensors.add(ds);

                        LightBulb lb = new LightBulb(plant);
                        lightController.addSwitch(lb);
                        time.registerForTime(plant);
                }
        }

        public void addPlant(Plant plant){
                plants.add(plant);
        }

        public void addMoistureSensor(MoistureSensor sensor){
                moistureSensors.add(sensor);
        }

        public void addDirtSensor(DirtSensor sensor){
                dirtSensors.add(sensor);
        }

        public void stopGarden(){
                GardenTime time = GardenTime.getInstance();
                time.stopClock();
        }
}

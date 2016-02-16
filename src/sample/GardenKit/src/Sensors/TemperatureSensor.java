package sample.GardenKit.src.Sensors;

import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.GardenCalendar.TimeListener;
import sample.GardenKit.src.Nature.Phenomena.ClimateController;

import java.util.Date;
import java.util.StringJoiner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nayan on 2/12/16.
 */
public class TemperatureSensor implements Sensor,TimeListener{



    public enum TemperatureUnit {

        CELSIUS, FAHREINHEIT
    }

    private String name;
    private int value;
    private TemperatureUnit unit;
    private boolean poweron;
    private static TemperatureSensor sensor;


    private TemperatureSensor(){
        switchON();
        setName("TemperatureSensor");
        setValue(27);
        setUnit(TemperatureUnit.CELSIUS);
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object value) {

        int val = (int) value;
        this.value = val;

    }

    public static TemperatureSensor getInstance(){
        if(sensor == null)
            sensor = new TemperatureSensor();
        return sensor;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setUnit(Object unit) {
        TemperatureUnit tu = (TemperatureUnit) unit;
        this.unit = tu;
    }

    @Override
    public Object getUnit() {
        return this.unit;
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


    private void findTemp(){
        ClimateController controller = ClimateController.getInstance();
        ClimateController.Temperature temp = controller.getCurrentTemperature();
        Log log = new Log();

        if(temp == ClimateController.Temperature.VERYCOOL){
            setValue((int)(Math.random() * 5 - 0));

        }
        if(temp == ClimateController.Temperature.COOL){
            setValue((int)(Math.random() * 10 - 5));

        }
        if(temp == ClimateController.Temperature.NORMAL){
            setValue((int)(Math.random() * 23 - 11));

        }

        if(temp == ClimateController.Temperature.HOT){
            setValue((int)(Math.random() * 24 - 30));


        }
        if(temp == ClimateController.Temperature.VERYHOT){
            setValue((int)(Math.random() * 30 - 45));

        }

        String val = String.valueOf((int)getValue());
        log.record("Current Temperature: "+ val);
    }

    @Override
    public void hourChanged(int hour) {

        if(isON())
        findTemp();

    }

    @Override
    public void dayChanged(int day) {

    }

    @Override
    public void monthChanged(int month) {

    }
}

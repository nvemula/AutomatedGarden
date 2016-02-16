package sample.GardenKit.src.Sensors;

/**
 * Created by nayan on 2/13/16.
 */
public class HumiditySensor implements Sensor {

    public enum HumidityUnit {
        PERCENTAGE
    }

    private String name;
    private float value;
    private boolean poweron;
    private HumidityUnit unit;


    HumiditySensor(){
        switchON();
        setName("HumiditySensor");
        setValue(0.0);
        setUnit(HumidityUnit.PERCENTAGE);
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object value) {
        float val = (float)value;
        this.value = val;
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
        HumidityUnit ut = (HumidityUnit) unit;
        this.unit = ut;
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




}



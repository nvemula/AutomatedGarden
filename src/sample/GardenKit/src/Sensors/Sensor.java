package sample.GardenKit.src.Sensors;

import sample.GardenKit.src.Common.ElectronicDevice;

import java.util.Objects;

/**
 * Created by nayan on 2/12/16.
 */
public interface Sensor extends ElectronicDevice {

    public void setValue(Object value);

    public String getName();

    public Object getValue();

    public void setUnit(Object unit);

    public Object getUnit();

}

package sample.GardenKit.src.Nature.Phenomena;

/**
 * Created by nayan on 2/15/16.
 */
public interface PhenomenaListener {

    public void temperatureChanged(ClimateController.Temperature temp);
    public void climateChanged(ClimateController.Climate climate);
}

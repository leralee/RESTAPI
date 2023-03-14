package dto;



/**
 * @author valeriali on {28.02.2023}
 * @project FirstRestApp
 */
public class MeasurementDTO {

    private Double value;


    private boolean raining;

    private SensorDTO sensor;
    

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}

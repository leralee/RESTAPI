package ru.li.RestServerApp.dto;

import ru.li.RestServerApp.models.Sensor;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author valeriali on {28.02.2023}
 * @project FirstRestApp
 */
public class MeasurementDTO {

    @Column(name = "value")
    @DecimalMin(value = "-100", inclusive = false)
    @DecimalMax(value = "100.0", inclusive = true)
    private Double value;

    @NotNull
    @Column(name = "raining")
    private boolean raining;

    private Sensor sensor;
    

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}

package ru.li.RestServerApp.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * @author valeriali on {28.02.2023}
 * @project FirstRestApp
 */
@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "value")
    @DecimalMin(value = "-100", inclusive = false)
    @DecimalMax(value = "100.0", inclusive = true)
    private Double value;

    @Column(name = "raining")
    @NotNull
    private boolean raining;

    @Column(name = "measurement_date")
    @NotNull
    private LocalDateTime measurementDateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement(Double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Measurement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getMeasurementDateTime() {
        return measurementDateTime;
    }

    public void setMeasurementDateTime(LocalDateTime measurementDateTime) {
        this.measurementDateTime = measurementDateTime;
    }
}

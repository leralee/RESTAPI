package ru.li.RestServerApp.dto;

import java.util.List;

/**
 * @author valeriali on {04.03.2023}
 * @project FirstRestApp
 */
public class MeasurementResponse {

    private List<MeasurementDTO> measurements;

    public MeasurementResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}

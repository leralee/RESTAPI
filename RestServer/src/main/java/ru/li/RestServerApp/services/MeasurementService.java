package ru.li.RestServerApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.li.RestServerApp.models.Measurement;
import ru.li.RestServerApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author valeriali on {01.03.2023}
 * @project FirstRestApp
 */
@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void addMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementDateTime(LocalDateTime.now());
    }
}

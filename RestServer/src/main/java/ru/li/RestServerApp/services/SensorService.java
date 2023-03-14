package ru.li.RestServerApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.li.RestServerApp.repositories.SensorRepository;
import ru.li.RestServerApp.models.Sensor;

import java.util.List;
import java.util.Optional;

/**
 * @author Li Valeria
 */
@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void register(Sensor sensor){
        sensorRepository.save(sensor);
    }
}

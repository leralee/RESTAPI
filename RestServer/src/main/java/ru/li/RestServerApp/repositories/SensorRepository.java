package ru.li.RestServerApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.li.RestServerApp.models.Sensor;

import java.util.Optional;

/**
 * @author Li Valeria
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}

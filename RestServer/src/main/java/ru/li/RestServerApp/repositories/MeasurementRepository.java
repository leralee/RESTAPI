package ru.li.RestServerApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.li.RestServerApp.models.Measurement;

/**
 * @author valeriali on {01.03.2023}
 * @project FirstRestApp
 */
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}

package ru.li.RestServerApp.util;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.li.RestServerApp.dto.SensorDTO;
import ru.li.RestServerApp.models.Measurement;
import ru.li.RestServerApp.services.SensorService;

/**
 * @author valeriali on {28.02.2023}
 * @project FirstRestApp
 */
@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SensorDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;
        if (measurement.getSensor() == null){
            return;
        }
        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "", "Нет зарегистрированного сенсора с таким именем!");
        }
    }
}

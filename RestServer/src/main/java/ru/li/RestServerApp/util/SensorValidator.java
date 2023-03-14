package ru.li.RestServerApp.util;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.li.RestServerApp.dto.SensorDTO;
import ru.li.RestServerApp.models.Sensor;
import ru.li.RestServerApp.services.SensorService;

/**
 * @author valeriali on {28.02.2023}
 * @project FirstRestApp
 */
@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SensorDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;
        if (sensorService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "", "Это сенсор уже существует");
        }
    }
}

package ru.li.RestServerApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.li.RestServerApp.dto.MeasurementResponse;
import ru.li.RestServerApp.dto.SensorDTO;
import ru.li.RestServerApp.dto.MeasurementDTO;
import ru.li.RestServerApp.models.Measurement;
import ru.li.RestServerApp.services.MeasurementService;
import ru.li.RestServerApp.services.SensorService;
import ru.li.RestServerApp.models.Sensor;
import ru.li.RestServerApp.util.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static ru.li.RestServerApp.util.ErrorsUtil.returnErrorsToClient;


/**
 * @author Li Valeria
 */
@RestController // @Controller + @ResponseBody над каждым методом
public class SensorController {

    private final SensorService sensorService;
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;
    @Autowired
    public SensorController(SensorService sensorService, MeasurementService measurementService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public MeasurementResponse getMeasurement() {
        return new MeasurementResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));

    }

    @GetMapping("/measurements/rainyDaysCount")
    public long getRainyDaysCount() {
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }


    @PostMapping("/sensors/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult){
        Sensor sensorToAdd = convertToSensor(sensorDTO);
        sensorValidator.validate(sensorToAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);
        sensorService.register(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/measurements/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult){
        Measurement measurementToAdd = convertToMeasurement(measurementDTO);
        if (bindingResult.hasErrors()){
            returnErrorsToClient(bindingResult);
        }
        measurementService.addMeasurement(measurementToAdd);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
    private Measurement convertToMeasurement(MeasurementDTO measureDTO) {
        return modelMapper.map(measureDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }



    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

package ru.li.RestServerApp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author valeriali on {04.03.2023}
 * @project FirstRestApp
 */
public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult){
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error: errors){
            errorMsg.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";\n");
        }
        throw new MeasurementException(errorMsg.toString());
    }
}

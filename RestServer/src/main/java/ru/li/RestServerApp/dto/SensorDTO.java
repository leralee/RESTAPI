package ru.li.RestServerApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author valeriali on {28.02.2023}
 * @project FirstRestApp
 */
public class SensorDTO {

    @Size(min = 3, max = 30, message = "Name should be not least 3 symbols or more than 30 symbols")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

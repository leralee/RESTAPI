package ru.li.RestServerApp.util;

/**
 * @author valeriali on {28.02.2023}
 * @project FirstRestApp
 */
public class MeasurementErrorResponse {
    private String name;
    private long timestamp;

    public MeasurementErrorResponse(String name, long timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

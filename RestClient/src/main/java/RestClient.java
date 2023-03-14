import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author valeriali on {13.03.2023}
 * @project RestClient
 */
public class RestClient {
    public static void main(String[] args) {
        final String sensorName = "Sensor_3";
        registerSensor(sensorName);


        Random random = new Random();
        double minTemperature = 0.0;
        double maxTemperature = 45.0;
        for (int i=0; i<500; i++){
            System.out.println(minTemperature+(random.nextDouble() * (maxTemperature-minTemperature)));
            sendMeasurement(minTemperature+(random.nextDouble() * (maxTemperature-minTemperature)),
                    random.nextBoolean(), sensorName);
        }
    }

    private static void registerSensor(String sensorName) {
        final String url = "http://localhost:8080/sensors/registration";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);
        makePostRequestWithJSONData(url, jsonData);
    }

    private static void sendMeasurement(double value, boolean raining, String sensorName){
        final String url = "http://localhost:8080/measurements/add";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));
        makePostRequestWithJSONData(url, jsonData);
    }

    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData){
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);
        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Изменение успешно занесено на сервер!");
        } catch(HttpClientErrorException e) {
            System.out.println("Ошибка");
            System.out.println(e.getMessage());
        }
    }
}

import dto.MeasurementDTO;
import dto.MeasurementResponse;
import org.knowm.xchart.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author valeriali on {14.03.2023}
 * @project RestClient
 */
public class XChart {
    public static void main(String[] args) {
        List<Double> temperatures = getTemperaturesFromServer();
        drawChart(temperatures);
    }

    private static List<Double> getTemperaturesFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/";

        MeasurementResponse jsonResponse = restTemplate.getForObject(url, MeasurementResponse.class);

        if (jsonResponse == null || jsonResponse.getMeasurements() == null)
            return Collections.emptyList();

        return jsonResponse.getMeasurements().stream().map(MeasurementDTO::getValue)
                .collect(Collectors.toList());
    }

    private static void drawChart(List<Double> temperatures) {
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yData = temperatures.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperature",
                xData, yData);

        new SwingWrapper(chart).displayChart();
    }
}

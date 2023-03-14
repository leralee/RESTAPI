package ru.li.RestServerApp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Li Valeria
 */
@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be not least 3 symbols or more than 30 symbols")
    private String name;

//    @OneToMany(mappedBy = "sensor")
//    private List<SensorMeasure> sensorMeasureList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

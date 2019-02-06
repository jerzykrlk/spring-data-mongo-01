package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.beans.ConstructorProperties;
import java.util.List;

@Document("cupboards")
public class Cupboard {

    @Id
    private final String id;
    private final String name;
    private final List<Cup> cups;

    @ConstructorProperties({"id", "name", "cups"})
    public Cupboard(String id, String name, List<Cup> cups) {
        this.id = id;
        this.name = name;
        this.cups = cups;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Cup> getCups() {
        return cups;
    }

}

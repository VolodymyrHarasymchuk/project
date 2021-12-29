package main.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaxiData implements Serializable {
    public List<TaxiStation> taxiStations = new ArrayList<>();
}
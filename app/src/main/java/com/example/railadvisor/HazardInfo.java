package com.example.railadvisor;

import java.util.HashMap;
import java.util.Map;

public class HazardInfo {

    // Define the main map to store warning and danger data
    private Map<String, Map<String, String>> hazardData;

    // Constructor to initialize the data
    public HazardInfo() {
        hazardData = new HashMap<>();

        // Create "warning" data
        Map<String, String> warning = new HashMap<>();
        warning.put("id", "PPGX 3053");
        warning.put("estado", "cargado");
        warning.put("unna", "1017");
        warning.put("contenido", "cloro");
        warning.put("clase_peligro", "2");
        warning.put("ferrocarril", "Ferrosur");
        warning.put("telefono", "8009111393");

        // Create "danger" data
        Map<String, String> danger = new HashMap<>();
        danger.put("id", "XXXX XXXX");
        danger.put("estado", "cargado");
        danger.put("unna", "1017");
        danger.put("contenido", "cloro");
        danger.put("clase_peligro", "2");
        danger.put("ferrocarril", "Ferrosur");
        danger.put("telefono", "8009111393");

        // Add the data to the main map
        hazardData.put("warning", warning);
        hazardData.put("danger", danger);
    }

    // Function to get data for a requested key
    public Map<String, String> getHazardData(String key) {
        return hazardData.getOrDefault(key, null); // Return null if key not found
    }
}

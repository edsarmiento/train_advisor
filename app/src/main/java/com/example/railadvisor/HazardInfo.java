package com.example.railadvisor;

import java.util.HashMap;
import java.util.Map;

public class HazardInfo {

    // Define the main map to store warning and danger data
    private Map<String, Map<String, String>> hazardData;

    // Constructor to initialize the data
    public HazardInfo() {
        hazardData = new HashMap<>();

        // Data for PPGX 3053
        Map<String, String> ppgx3053 = new HashMap<>();
        ppgx3053.put("id", "PPGX 3053");
        ppgx3053.put("estado", "cargado");
        ppgx3053.put("unna", "1080");
        ppgx3053.put("contenido", "cloro");
        ppgx3053.put("clase_peligro", "2");
        ppgx3053.put("ferrocarril", "Ferrosur");
        ppgx3053.put("telefono", "8009111393");
        ppgx3053.put("icon", "flamable");

        // Add PPGX 3053 data to the main map
        hazardData.put("PPGX 3053", ppgx3053);

        // Data for PPGX 3054
        Map<String, String> ppgx3054 = new HashMap<>();
        ppgx3054.put("id", "PPGX 3054"); // Corrected to a unique id
        ppgx3054.put("estado", "cargado");
        ppgx3054.put("unna", "1017");
        ppgx3054.put("contenido", "PUTITAS");
        ppgx3054.put("clase_peligro", "2");
        ppgx3054.put("ferrocarril", "Ferrosur");
        ppgx3054.put("telefono", "8009111393");
        ppgx3054.put("icon", "rpbi");

        // Add PPGX 3054 data to the main map
        hazardData.put("PPGX 3054", ppgx3054);
    }

    // Function to get data for a requested key
    public Map<String, String> getHazardData(String key) {
        return hazardData.getOrDefault(key, null); // Return null if key not found
    }
}

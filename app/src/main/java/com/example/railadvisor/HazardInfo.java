package com.example.railadvisor;

import java.util.HashMap;
import java.util.Map;

public class HazardInfo {

    // Define the main map to store warning and danger data
    private Map<String, Map<String, String>> hazardData;

    // Constructor to initialize the data
    public HazardInfo() {
        hazardData = new HashMap<>();

        Map<String, String> aamx2014 = new HashMap<>();
        aamx2014.put("id", "AAMX 2014");
        aamx2014.put("estado", "Cargado");
        aamx2014.put("unna", "1005");
        aamx2014.put("contenido", "Amoniaco");
        aamx2014.put("clase_peligro", "2");
        aamx2014.put("ferrocarril", "Ferrosur");
        aamx2014.put("telefono", "(800)9111393");
        aamx2014.put("icon", "un1005");
        aamx2014.put("file_name", "amoniaco.pdf");
        hazardData.put("AAMX 2014", aamx2014);

        Map<String, String> acfx73616 = new HashMap<>();
        acfx73616.put("id", "ACFX 73616");
        acfx73616.put("estado", "Cargado");
        acfx73616.put("unna", "1017");
        acfx73616.put("contenido", "Cloro");
        acfx73616.put("clase_peligro", "2");
        acfx73616.put("ferrocarril", "Ferrosur");
        acfx73616.put("telefono", "(800)9111393");
        acfx73616.put("icon", "un1017");
        hazardData.put("ACFX 73616", acfx73616);

        Map<String, String> utlx83895 = new HashMap<>();
        utlx83895.put("id", "UTLX 83895");
        utlx83895.put("estado", "Cargado");
        utlx83895.put("unna", "1040");
        utlx83895.put("contenido", "Oxido de Etileno");
        utlx83895.put("clase_peligro", "2");
        utlx83895.put("ferrocarril", "CPKC");
        utlx83895.put("telefono", "(828)3057911");
        utlx83895.put("icon", "un1040");
        hazardData.put("UTLX 83895", utlx83895);

        Map<String, String> dowx49355 = new HashMap<>();
        dowx49355.put("id", "DOWX 49355");
        dowx49355.put("estado", "Cargado");
        dowx49355.put("unna", "1086");
        dowx49355.put("contenido", "Cloruro de Vinilo");
        dowx49355.put("clase_peligro", "2");
        dowx49355.put("ferrocarril", "CPKC");
        dowx49355.put("telefono", "(828)3057911");
        dowx49355.put("icon", "un1086");
        hazardData.put("DOWX 49355", dowx49355);

        Map<String, String> pxcx200081 = new HashMap<>();
        pxcx200081.put("id", "PXCX 200081");
        pxcx200081.put("estado", "Cargado");
        pxcx200081.put("unna", "1049");
        pxcx200081.put("contenido", "Hidrogeno");
        pxcx200081.put("clase_peligro", "2");
        pxcx200081.put("ferrocarril", "FIT");
        pxcx200081.put("telefono", "(921)1636618");
        pxcx200081.put("icon", "un1049");
        hazardData.put("PXCX 200081", pxcx200081);

        Map<String, String> utlx82302 = new HashMap<>();
        utlx82302.put("id", "UTLX 82302");
        utlx82302.put("estado", "Cargado");
        utlx82302.put("unna", "1090");
        utlx82302.put("contenido", "Acetona");
        utlx82302.put("clase_peligro", "3");
        utlx82302.put("ferrocarril", "FIT");
        utlx82302.put("telefono", "(921)1636618");
        utlx82302.put("icon", "un1090");
        hazardData.put("UTLX 82302", utlx82302);

        Map<String, String> mikixx300 = new HashMap<>();
        mikixx300.put("id", "MIKI 300");
        mikixx300.put("estado", "Vacio");
        mikixx300.put("unna", "2733");
        mikixx300.put("contenido", "Gas LP - Vacio");
        mikixx300.put("clase_peligro", "2");
        mikixx300.put("ferrocarril", "FIT");
        mikixx300.put("telefono", "(921)1636618");
        mikixx300.put("icon", "un2733");
        hazardData.put("MIKI 300", mikixx300);

        Map<String, String> gatx8434 = new HashMap<>();
        gatx8434.put("id", "GATX 8434");
        gatx8434.put("estado", "Cargado");
        gatx8434.put("unna", "2733");
        gatx8434.put("contenido", "Aminas");
        gatx8434.put("clase_peligro", "3");
        gatx8434.put("ferrocarril", "Ferromex");
        gatx8434.put("telefono", "(800)9111393");
        gatx8434.put("icon", "un2733");
        hazardData.put("GATX 8434", gatx8434);
    }

    // Function to get data for a requested key
    public Map<String, String> getHazardData(String key) {
        return hazardData.getOrDefault(key, null); // Return null if key not found
    }
}

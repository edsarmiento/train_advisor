package com.example.railadvisor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Define variables
    private static final String REGEX = "^[A-Z]{4} \\d{1,6}$";
    private Button buttonToast;
    private EditText container_input;
    private TextView details_id, details_estado, details_unna, details_contenido, details_clase_peligro, details_ferrocarril, details_emergency_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge UI
        EdgeToEdge.enable(this);

        // Set content view
        setContentView(R.layout.activity_main);

        // Initialize Views
        buttonToast = findViewById(R.id.search_button);
        container_input = findViewById(R.id.container_input);

        details_id = findViewById(R.id.details_id);
        details_estado = findViewById(R.id.details_estado);
        details_unna = findViewById(R.id.details_unna);
        details_contenido = findViewById(R.id.details_contenido);
        details_clase_peligro = findViewById(R.id.details_clase_peligro);
        details_ferrocarril = findViewById(R.id.details_ferrocarril);
        details_emergency_phone = findViewById(R.id.details_emergency_phone);

        // Handle window insets for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the button's onClick listener
        buttonToast.setOnClickListener(v -> {
            // Get the text input as a string
            String value_input = container_input.getText().toString();

            // Validate the input using regex
            if (Pattern.matches(REGEX, value_input)) {
                Toast.makeText(MainActivity.this, "Valid input!", Toast.LENGTH_SHORT).show();

                // Create an instance of HazardInfo (mock data class)
                HazardInfo hazardInfo = new HazardInfo();

                // Get data for "warning" and populate UI
                Map<String, String> warningData = hazardInfo.getHazardData("warning");
                if (warningData != null) {
                    populateDetails(warningData);
                } else {
                    Toast.makeText(MainActivity.this, "No data found for 'warning'", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Formato inválido! Use 'ABCD 123'", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helper method to populate TextViews with data
    private void populateDetails(Map<String, String> data) {
        details_id.setText(data.getOrDefault("id", "N/A"));
        details_estado.setText(data.getOrDefault("estado", "N/A"));
        details_unna.setText(data.getOrDefault("unna", "N/A"));
        details_contenido.setText(data.getOrDefault("contenido", "N/A"));
        details_clase_peligro.setText(data.getOrDefault("clase_peligro", "N/A"));
        details_ferrocarril.setText(data.getOrDefault("ferrocarril", "N/A"));
        details_emergency_phone.setText(data.getOrDefault("telefono", "N/A"));
    }
}

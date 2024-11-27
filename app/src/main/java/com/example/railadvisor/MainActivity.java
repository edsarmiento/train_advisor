package com.example.railadvisor;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;

import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Define variables
    private static final String REGEX = "^[A-Z]{4} \\d{1,6}$";
    private Button buttonSearch, details_emergency_phone;
    private EditText container_input;
    private TextView details_id, details_estado, details_unna, details_contenido, details_clase_peligro, details_ferrocarril;
    private ImageView details_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge UI
        EdgeToEdge.enable(this);

        // Set content view
        setContentView(R.layout.activity_main);

        // Initialize Views
        buttonSearch = findViewById(R.id.search_button);
        container_input = findViewById(R.id.container_input);

        details_id = findViewById(R.id.details_id);
        details_estado = findViewById(R.id.details_estado);
        details_unna = findViewById(R.id.details_unna);
        details_contenido = findViewById(R.id.details_contenido);
        details_clase_peligro = findViewById(R.id.details_clase_peligro);
        details_ferrocarril = findViewById(R.id.details_ferrocarril);
        details_emergency_phone = findViewById(R.id.details_emergency_phone);
        details_icon = findViewById(R.id.icon);

        // Handle window insets for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the button's onClick listener
        buttonSearch.setOnClickListener(v -> {
            // Get the text input as a string
            String value_input = container_input.getText().toString();

            // Validate the input using regex
            if (Pattern.matches(REGEX, value_input)) {
                // Create an instance of HazardInfo (mock data class)
                HazardInfo hazardInfo = new HazardInfo();

                // Get data for "warning" and populate UI
                Map<String, String> mockData = hazardInfo.getHazardData(value_input);

                if (mockData != null) {
                    populateDetails(mockData);

                    details_emergency_phone.setEnabled(true);
                    details_emergency_phone.setBackgroundColor(Color.RED);

                    Drawable drawableLeft = ContextCompat.getDrawable(this, R.drawable.ic_phone);
                    if (drawableLeft != null) {
                        drawableLeft.setBounds(0, 0, drawableLeft.getIntrinsicWidth(), drawableLeft.getIntrinsicHeight());
                        details_emergency_phone.setCompoundDrawables(drawableLeft, null, null, null);
                        details_emergency_phone.setCompoundDrawablePadding(16); // Adjust as needed (16dp)
                    }
                    details_emergency_phone.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);

                } else {
                    Toast.makeText(MainActivity.this, "ID '"+value_input+"' no encontrado", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Formato inválido! Use 'ABCD 123'", Toast.LENGTH_SHORT).show();
            }
        });

        details_emergency_phone.setOnClickListener(view -> {
            String phoneNumber = "tel:" + details_emergency_phone.getText().toString(); // Ensure it's a valid URI
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phoneNumber));

            // Check for CALL_PHONE permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // Request permission if not granted
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            }

            // Start the call
            startActivity(callIntent);
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


        String iconName = data.getOrDefault("icon", "N/A");
        int resourceId = getResources().getIdentifier(iconName, "drawable", getPackageName());
        details_icon.setImageResource(resourceId);
    }
}

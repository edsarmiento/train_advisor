package com.example.railadvisor;

import android.Manifest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Define variables
    private static final String REGEX = "^[A-Z]{4} \\d{1,6}$";
    private Button buttonSearch, details_emergency_phone;
    private EditText container_input;
    private TextView details_id, details_estado, details_unna, details_contenido, details_clase_peligro, details_ferrocarril, more_info;
    private ImageView details_icon;
    private String file_name;

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

        more_info = findViewById(R.id.more_info);
        details_icon = findViewById(R.id.icon);

        // Set up the Toolbar
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("My App");
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

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
                    setCallButton();
                    setMoreInfoColorText();
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

        more_info.setOnClickListener(v -> openPdfFromRaw());
    }

    private void openPdfFromRaw() {
        try {
            // Get InputStream from raw resource
            InputStream inputStream = getResources().openRawResource(R.raw.amoniaco);

            // Save it to a temporary file in internal storage
            File tempFile = new File(getCacheDir(), "sample.pdf");
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            }

            // Use FileProvider to get URI for the file
            Uri pdfUri = FileProvider.getUriForFile(this, "com.example.railadvisor.fileprovider", tempFile);

            // Create an Intent to view the PDF
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to open PDF", Toast.LENGTH_SHORT).show();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No PDF viewer found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Add items programmatically to the menu
        menu.add(0, 1, 0, "Item 1").setIcon(android.R.drawable.ic_menu_camera).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add(0, 2, 1, "Item 2").setIcon(android.R.drawable.ic_menu_info_details).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item click events
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setMoreInfoColorText(){
        more_info.setTextColor(Color.parseColor("#000000"));
    }

    public void setCallButton(){
        details_emergency_phone.setEnabled(true);
        details_emergency_phone.setBackgroundColor(Color.RED);

        Drawable drawableLeft = ContextCompat.getDrawable(this, R.drawable.ic_phone);
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, drawableLeft.getIntrinsicWidth(), drawableLeft.getIntrinsicHeight());
            details_emergency_phone.setCompoundDrawables(drawableLeft, null, null, null);
            details_emergency_phone.setCompoundDrawablePadding(16); // Adjust as needed (16dp)
        }
        details_emergency_phone.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
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

        file_name = data.getOrDefault("file_name", "N/A");

        String iconName = data.getOrDefault("icon", "N/A");
        int resourceId = getResources().getIdentifier(iconName, "drawable", getPackageName());
        details_icon.setImageResource(resourceId);
    }
}

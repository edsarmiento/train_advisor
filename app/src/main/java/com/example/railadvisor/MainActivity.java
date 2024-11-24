package com.example.railadvisor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Define variables here, but initialize them in onCreate
    String regex = "^[A-Z]{4} \\d{1,6}$";
    Button buttonToast;
    EditText container_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize Views AFTER setContentView
        buttonToast = findViewById(R.id.search_button);
        container_input = findViewById(R.id.container_input);

        // Handle Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set OnClickListener on the button
        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text input as a String
                String value_input = container_input.getText().toString();

                // Validate the input with regex
                if (Pattern.matches(regex, value_input)) {
                    Toast.makeText(MainActivity.this, "Valid input!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "formato! Use 'abcd 123'", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

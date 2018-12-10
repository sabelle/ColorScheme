package com.example.colorscheme;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Second page of app, displays color palette created.
 */
public class ColorActivity extends AppCompatActivity {
    /** Message at top of screen. */
    TextView message;

    /** color1 */
    View firstColor;

    /**
     * Run when this activity comes to the foreground.
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        //text display "Song" by Artist
        message = findViewById(R.id.message);
        message.setText(getIntent().getStringExtra("message"));

        // Sets background color of "color1" to hexadecimal value
        // not working... line 43 runs before the hexa gets a value assigned to it :(
        firstColor = findViewById(R.id.color1);
        System.out.println("first color: " + firstColor);
        firstColor.setBackgroundColor(Color.parseColor("#" + MainActivity.hexa));
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }
}


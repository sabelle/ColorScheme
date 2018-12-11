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

    /** Message displayed if error occurs */
    TextView error;

    /** View component for color1 */
    View firstColor;

    /** View component for color2 */
    View secondColor;

    /** View component for color3 */
    View thirdColor;

    /** color1 as an int */
    int color1;

    /** color2 as an int */
    int color2;

    /** color3 as an int */
    int color3;

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
        error = findViewById(R.id.error_view);

        message.setText(getIntent().getStringExtra("message"));
        error.setText(getIntent().getStringExtra("error"));
        //access colors
        firstColor = findViewById(R.id.color1);
        secondColor = findViewById(R.id.color2);
        thirdColor = findViewById(R.id.color3);
        try {
            setColors();
            firstColor.setBackgroundColor(color1);
            secondColor.setBackgroundColor(color2);
            thirdColor.setBackgroundColor(color3);
        } catch (Exception e) { }
    }

    /**
     * Sets the value of the colors
     * Handles hexadecimal values with 7 characters instead of 8.
     * Turns them into 6-value hexadecimal representations of color (instead of 8).
     */
    public void setColors() {
        if (MainActivity.hexa1.length() == 8) {
            color1 = Color.parseColor(MainActivity.hexa1.substring(0, 7));
        } else {
            color1 = Color.parseColor(MainActivity.hexa1);
        }
        if (MainActivity.hexa2.length() == 8) {
            color2 = Color.parseColor(MainActivity.hexa2.substring(0, 7));
        } else {
            color2 = Color.parseColor(MainActivity.hexa2);
        }
        if (MainActivity.hexa3.length() == 8) {
            color3 =Color.parseColor(MainActivity.hexa3.substring(0, 7));
        } else {
            color3 = Color.parseColor(MainActivity.hexa3);
        }
    }
    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }
}


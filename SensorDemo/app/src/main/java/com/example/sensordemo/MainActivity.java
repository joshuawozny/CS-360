package com.example.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView accelerometerValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SensorManager and accelerometer
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Initialize TextView
        accelerometerValues = findViewById(R.id.accelerometerValues);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register accelerometer sensor listener
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister accelerometer sensor listener to avoid battery drain
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Update TextView with accelerometer values
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        accelerometerValues.setText(String.format("Accelerometer Values:\nX: %.2f\nY: %.2f\nZ: %.2f", x, y, z));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}

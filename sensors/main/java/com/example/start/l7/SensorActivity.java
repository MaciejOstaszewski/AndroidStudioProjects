package com.example.start.l7;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mAccelerometer;
    private float[] mGravity = new float[3];
    private float[] mAccel = new float[3];
    float mAlpha = 0.8f;

    TextView XValueView;
    TextView YValueView;
    TextView ZValueView;
    TextView XGravity;
    TextView YGravity;
    TextView ZGravity;
    TextView XAccel;
    TextView YAccel;
    TextView ZAccel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        XValueView = (TextView) findViewById(R.id.XValueView);
        YValueView = (TextView) findViewById(R.id.YValueView);
        ZValueView = (TextView) findViewById(R.id.ZValueView);
        XGravity = (TextView) findViewById(R.id.XGravity);
        YGravity = (TextView) findViewById(R.id.YGravity);
        ZGravity = (TextView) findViewById(R.id.ZGravity);
        XAccel = (TextView) findViewById(R.id.XAccel);
        YAccel = (TextView) findViewById(R.id.YAccel);
        ZAccel = (TextView) findViewById(R.id.ZAccel);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorList();
        mSensorManager.unregisterListener(this, mAccelerometer);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float rawX = sensorEvent.values[0], rawY = sensorEvent.values[1], rawZ =
                    sensorEvent.values[2];
            mGravity[0] = lowPass(rawX, mGravity[0]);
            mGravity[1] = lowPass(rawY, mGravity[1]);
            mGravity[2] = lowPass(rawZ, mGravity[2]);
            mAccel[0] = highPass(rawX, mGravity[0]);
            mAccel[1] = highPass(rawY, mGravity[1]);
            mAccel[2] = highPass(rawZ, mGravity[2]);
            XValueView.setText(String.valueOf(rawX));
            YValueView.setText(String.valueOf(rawY));
            ZValueView.setText(String.valueOf(rawZ));
            XGravity.setText(String.valueOf(mGravity[0]));
            YGravity.setText(String.valueOf(mGravity[1]));
            ZGravity.setText(String.valueOf(mGravity[2]));
            XAccel.setText(String.valueOf(mAccel[0]));
            YAccel.setText(String.valueOf(mAccel[1]));
            ZAccel.setText(String.valueOf(mAccel[2]));
        }
    }

    public void sensorList(){
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s : sensors){
            Log.d("SENSOR", s.getName());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private float lowPass(float current, float gravity) {
        return gravity * mAlpha + current * (1 - mAlpha);
    }

    private float highPass(float current, float gravity) {
        return current - gravity;
    }
}

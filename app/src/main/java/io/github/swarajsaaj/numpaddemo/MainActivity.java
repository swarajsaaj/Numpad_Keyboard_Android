package io.github.swarajsaaj.numpaddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import swarajsaaj.numpad.Numpad;
import swarajsaaj.numpad.NumpadHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Numpad numpad = (Numpad) findViewById(R.id.numpad);
        numpad.bindNextStepHandler(new NumpadHandler() {
            @Override
            public void nextStep() {
                Toast.makeText(MainActivity.this, "Next Task", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

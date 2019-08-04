package me.mortuza.fragmentanimations.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import me.mortuza.fragmentanimations.Gen.GO;
import me.mortuza.fragmentanimations.Gen.GOTO;
import me.mortuza.fragmentanimations.ModelX.ModelX;
import me.mortuza.fragmentanimations.ModelX.ModelXX;
import me.mortuza.fragmentanimations.R;
import me.mortuza.fragmentanimations.abs.Abs;
import me.mortuza.fragmentanimations.abs.MainClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModelX modelX = new ModelX();

        GO<ModelX> modelXGO = new GO<>(modelX);
        Log.d("MainActivity", "onCreate: " + modelXGO.getItem().getX());


        ModelXX modelXX = new ModelXX();
        GO<ModelXX> modelXGOs = new GO<>(modelXX);

        Log.d("MainActivity", "onCreate: " + modelXGOs.getItem().getX());

        Abs abs = new MainClass();
        Log.d("MainActivity", "onCreate: " + abs.genTen());

    }
}

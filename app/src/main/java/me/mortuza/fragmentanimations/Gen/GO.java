package me.mortuza.fragmentanimations.Gen;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

import me.mortuza.fragmentanimations.ModelX.ModelX;
import me.mortuza.fragmentanimations.ModelX.ModelXX;

public class GO<T> {
    private T item;

    public GO(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }


}

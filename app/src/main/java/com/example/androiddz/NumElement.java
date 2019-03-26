package com.example.androiddz;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.TextView;

public class NumElement {
    String text;
    int color;

    public NumElement(int num) {
        text = num + "";
        if (num % 2 == 1) {
            color = Color.BLUE;
        } else {
            color = Color.RED;
        }
    }

    public static void updateView(@NonNull NumElement el, @NonNull TextView v) {
        v.setTextColor(el.color);
        v.setText(el.text);
    }

}

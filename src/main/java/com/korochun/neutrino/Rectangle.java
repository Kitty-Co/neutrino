package com.korochun.neutrino;

public class Rectangle {
    public final float x0, y0;
    public final float x1, y1;

    public Rectangle(float x0, float y0, float x1, float y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public Rectangle inset(float x, float y, float w, float h) {
        return new Rectangle(x0 + (x1 - x0) * x, y0 + (y1 - y0) * y, x0 + (x1 - x0) * w, y0 + (y1 - y0) * h);
    }
}

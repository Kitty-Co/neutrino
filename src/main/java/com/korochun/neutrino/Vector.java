package com.korochun.neutrino;

public class Vector implements Cloneable {
    public float x = 0, y = 0;

    public Vector() {
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector clone() throws CloneNotSupportedException {
        return (Vector) super.clone();
    }

    public Vector sub(Vector other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector mul(float fac) {
        this.x *= fac;
        this.y *= fac;
        return this;
    }
}

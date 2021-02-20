package com.korochun.neutrino;

public class Transform implements Cloneable {
    public Vector translation = new Vector(), scale = new Vector(), center = new Vector();
    public float a = 0, b = 0;

    public Transform() {

    }

    public Transform clone() throws CloneNotSupportedException {
        return (Transform) super.clone();
    }

    public Transform rotate(Vector center, float angle) {
        return null;
    }

    public Transform scale(Vector center, Vector fac) {
        return null;
    }

    public Transform translate(Vector translation) {
        return null;
    }

    public Transform skew(Vector skew) {
        return null;
    }
}

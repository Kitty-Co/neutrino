package com.korochun.neutrino;

import org.joml.Matrix3x2f;

import java.util.Hashtable;

public abstract class Layer {
    static final Hashtable<String, Layer> layers = new Hashtable<>();
    public Matrix3x2f transform = new Matrix3x2f();

    public Layer(String name) {
        if (layers.containsKey(name)) {
            throw new IllegalStateException("Layer with name " + name + " exists");
        }
        layers.put(name, this);
    }

    public static Layer fromName(String name) {
        return layers.get(name);
    }

    protected abstract void render(Neutrino neutrino);

    public abstract void destroy();
}
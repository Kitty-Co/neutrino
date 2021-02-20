package com.korochun.neutrino;

import java.util.Hashtable;

public abstract class Layer {
    static final Hashtable<String, Layer> layers = new Hashtable<>();
    public Transform transform = new Transform();

    public Layer(String name) {
        if (layers.containsKey(name)) {
            throw new IllegalStateException("Layer with name " + name + " exists");
        }
        layers.put(name, this);
    }

    public static Layer fromName(String name) {
        return layers.get(name);
    }

    protected abstract void render();

    public abstract void destroy();
}
package com.korochun.neutrino;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Image {
    protected static final ArrayList<Integer> handles = new ArrayList<>();
    private static final Hashtable<String, Image> registry = new Hashtable<>();

    public static Image fromName(String name) {
        return registry.get(name);
    }

    protected abstract int getHandle(Neutrino neutrino);

    protected abstract Rectangle getUV(Neutrino neutrino);

    public Image register(String name) {
        registry.put(name, this);
        return this;
    }
}

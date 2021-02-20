package com.korochun.neutrino;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.lwjgl.opengl.GL11.glClearColor;

public class Scene {
    private static final Hashtable<String, Scene> scenes = new Hashtable<>();
    public final ArrayList<Layer> layers = new ArrayList<>();
    public float backgroundRed = 0, backgroundGreen = 0, backgroundBlue = 0;
    public Scene(String name) {
        scenes.put(name, this);
    }

    public static Scene fromName(String name) {
        return scenes.get(name);
    }

    protected void render() {
        glClearColor(backgroundRed, backgroundGreen, backgroundBlue, 0);
        for (Layer layer : layers) layer.render();
    }

    public Scene setBackground(float red, float green, float blue) {
        this.backgroundRed = red;
        this.backgroundGreen = green;
        this.backgroundBlue = blue;
        return this;
    }
}

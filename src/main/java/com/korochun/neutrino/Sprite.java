package com.korochun.neutrino;

import java.util.Hashtable;

class Sprite {
    private static final Hashtable<String, Sprite> sprites = new Hashtable<>();
    private boolean visible = false;
    public Vector pos = new Vector();
    private Image texture;

    public Sprite(String name, Image texture) {
        this.texture = texture;
        sprites.put(name, this);
    }

    public Sprite setTexture(Image texture) {
        this.texture = texture;
        return this;
    }

    public Sprite setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public static Sprite fromName(String name) {
        return sprites.get(name);
    }
}

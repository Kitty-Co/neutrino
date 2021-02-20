package com.korochun.neutrino;

import java.util.Hashtable;

class Tile {
    private static final Hashtable<String, Tile> tiles = new Hashtable<>();
    private final Image texture;

    public Tile(String name, Image texture) {
        this.texture = texture;
        tiles.put(name, this);
    }

    public static Tile fromName(String name) {
        return tiles.get(name);
    }

    private Image getTexture() {
        return texture;
    }
}
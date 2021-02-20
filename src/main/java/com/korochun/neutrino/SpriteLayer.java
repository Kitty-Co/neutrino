package com.korochun.neutrino;

import java.util.ArrayList;

public class SpriteLayer extends Layer {
    public final ArrayList<Sprite> sprites = new ArrayList<>();

    public SpriteLayer(String name) {
        super(name);
    }

    @Override
    protected void render() {
        // TODO
    }

    @Override
    public void destroy() {

    }
}

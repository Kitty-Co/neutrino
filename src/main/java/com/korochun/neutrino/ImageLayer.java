package com.korochun.neutrino;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

public class ImageLayer extends Layer {
    private final int buffer;
    private Image texture;

    public ImageLayer(String name, Image texture) {
        super(name);
        this.texture = texture;
        buffer = glGenBuffers();
    }

    public ImageLayer setTexture(Image texture) {
        this.texture = texture;
        return this;
    }

    @Override
    protected void render() {
        // TODO
    }

    @Override
    public void destroy() {
        glDeleteBuffers(buffer);
    }
}
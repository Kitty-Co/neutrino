package com.korochun.neutrino;

import static org.lwjgl.opengl.GL15.*;

public class ImageLayer extends Layer {
    private final int buffer;
    private Image texture;

    public ImageLayer(String name, Image texture) {
        super(name);
        this.texture = texture;
        buffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glBufferData(GL_ARRAY_BUFFER, new float[] {0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1}, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public ImageLayer setTexture(Image texture) {
        this.texture = texture;
        return this;
    }

    @Override
    protected void render(Neutrino neutrino) {
        neutrino.draw(transform, texture, buffer, 6);
    }

    @Override
    public void destroy() {
        glDeleteBuffers(buffer);
    }
}
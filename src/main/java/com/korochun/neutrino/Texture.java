package com.korochun.neutrino;

public class Texture extends Image {
    protected Texture(int handle, Rectangle uv) {

    }

    public Texture(String filename) {

    }

    protected int getHandle(Neutrino neutrino) {
        return 0;
    }

    protected Rectangle getUV(Neutrino neutrino) {
        return null;
    }

    public Texture subImage(int x, int y, int width, int height) {
        return null;
    }
}

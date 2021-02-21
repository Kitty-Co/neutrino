package com.korochun.neutrino;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_BGRA;

public class Texture extends Image {
    private final int handle;
    private final Rectangle uv;
    private final int width, height;

    protected Texture(int handle, Rectangle uv, int width, int height) {
        this.handle = handle;
        this.uv = uv;
        this.width = width;
        this.height = height;
    }

    public Texture(String filename) {
        BufferedImage image;
        try {
            image = ImageIO.read(ClassLoader.getSystemResourceAsStream(filename));
        } catch (IOException ex) {
            width = 0;
            height = 0;
            throw new IllegalStateException("Unable to load texture " + filename, ex);
        }
        uv = new Rectangle(0, 0, 1, 1);
        width = image.getWidth();
        height = image.getHeight();

        handle = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, handle);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_BGRA, GL_UNSIGNED_BYTE, image.getRGB(0, 0, width, height, null, 0, width));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glBindTexture(GL_TEXTURE_2D, 0);
        Image.handles.add(handle);
    }

    protected int getHandle(Neutrino neutrino) {
        return handle;
    }

    protected Rectangle getUV(Neutrino neutrino) {
        return uv;
    }

    public Texture subImage(int x, int y, int w, int h) {
        return new Texture(handle, uv.inset((float) x / width, (float) y / height, (float) (x + w) / width, (float) (y + h) / height), w, h);
    }

    public Texture register(String name) {
        return (Texture) super.register(name);
    }
}

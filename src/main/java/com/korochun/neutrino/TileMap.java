package com.korochun.neutrino;

class TileMap extends Layer {
    public final Tile[][] map;

    public TileMap(String name, int width, int height) {
        super(name);
        map = new Tile[width][height];
    }

    @Override
    protected void render(Neutrino neutrino) {
        // TODO
    }

    @Override
    public void destroy() {

    }
}


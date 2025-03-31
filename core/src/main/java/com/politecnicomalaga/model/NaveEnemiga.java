package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class NaveEnemiga extends Nave {
    private static final float VELOCIDAD = 50;
    private Texture textura;

    public NaveEnemiga(float x, float y) {
        super(x, y, 30, 20);
        textura = new Texture("NaveEnemiga.png");
    }

    @Override
    public void actualizar() {
        bounds.y -= VELOCIDAD * Gdx.graphics.getDeltaTime();
        if (bounds.y < 0) {
            bounds.y = Gdx.graphics.getHeight();
        }
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, bounds.x, bounds.y, bounds.width, bounds.height);
    }
}

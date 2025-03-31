package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class DisparoAmigo extends Disparo {
    private static final float VELOCIDAD = 300;
    private Texture textura;

    public DisparoAmigo(float x, float y) {
        super(x, y, 5, 10);
        textura = new Texture("disparo.png");
    }

    @Override
    public void actualizar() {
        bounds.y += VELOCIDAD * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, bounds.x, bounds.y, bounds.width, bounds.height);
    }
}


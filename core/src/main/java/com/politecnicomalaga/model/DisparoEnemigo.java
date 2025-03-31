package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class DisparoEnemigo extends Disparo {
    private static final float VELOCIDAD = 200;
    private Texture textura;

    public DisparoEnemigo(float x, float y) {
        super(x, y, 5, 10);
        textura = new Texture("disparo_enemigo.png");
    }

    @Override
    public void actualizar() {
        bounds.y -= VELOCIDAD * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, bounds.x, bounds.y, bounds.width, bounds.height);
    }
}

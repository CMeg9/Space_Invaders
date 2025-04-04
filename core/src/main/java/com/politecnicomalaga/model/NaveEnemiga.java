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
        limites.y -= VELOCIDAD * Gdx.graphics.getDeltaTime();
        if (limites.y < 0) {
            limites.y = Gdx.graphics.getHeight();
        }
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, limites.x, limites.y, limites.width, limites.height);
    }
}

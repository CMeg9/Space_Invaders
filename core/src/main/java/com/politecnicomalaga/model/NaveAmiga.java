package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class NaveAmiga extends Nave {
    private static final float VELOCIDAD = 200;
    private Texture textura;

    public NaveAmiga() {
        super(100, 50, 30, 20);
        textura = new Texture("NaveAmiga.png");
    }

    public void moverIzquierda() {
        limites.x -= VELOCIDAD * Gdx.graphics.getDeltaTime();
    }

    public void moverDerecha() {
        limites.x += VELOCIDAD * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void actualizar() {
        // Lógica de actualización de la nave amiga
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, limites.x, limites.y, limites.width, limites.height);
    }
}

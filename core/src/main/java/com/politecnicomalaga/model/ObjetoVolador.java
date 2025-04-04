package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoVolador {
    protected Rectangle limites;

    public ObjetoVolador(float x, float y, float width, float height) {
        limites = new Rectangle(x, y, width, height);
    }

    public float getX() {
        return limites.x;
    }

    public float getY() {
        return limites.y;
    }

    public float getWidth() {
        return limites.width;
    }

    public float getHeight() {
        return limites.height;
    }

    public abstract void actualizar();
    public abstract void dibujar(SpriteBatch batch);
}

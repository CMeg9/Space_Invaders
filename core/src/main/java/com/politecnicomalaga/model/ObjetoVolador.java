package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoVolador {
    protected Rectangle bounds;

    public ObjetoVolador(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public float getX() {
        return bounds.x;
    }

    public float getY() {
        return bounds.y;
    }

    public float getWidth() {
        return bounds.width;
    }

    public float getHeight() {
        return bounds.height;
    }

    public abstract void actualizar();
    public abstract void dibujar(SpriteBatch batch);
}

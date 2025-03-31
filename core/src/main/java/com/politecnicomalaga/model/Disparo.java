package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Disparo extends ObjetoVolador {
    private boolean esAmigo;

    public Disparo(int posX, int posY, int ancho, int alto, int velocidad, Texture imagen, boolean esAmigo) {
        super(posX, posY, ancho, alto, velocidad, imagen);
        this.esAmigo = esAmigo;
    }

    public boolean isAmigo() {
        return esAmigo;
    }


    public void moverse() {
        setPosY(getPosY() + (isAmigo() ? getVelocidad() : -getVelocidad()));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getImagen(), getPosX(), getPosY(), getAncho(), getAlto());
    }

    @Override
    public static boolean colisionaCon(ObjetoVolador objeto) {
        return super.colisionaCon(objeto);
    }
}

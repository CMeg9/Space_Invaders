package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Nave extends ObjetoVolador {

    public Nave(int iPosicionX, int iPosicionY, int iAlto, int iAncho, int iVelocidad, Texture tImg) {
        super(iPosicionX, iPosicionY, iAlto, iAncho, iVelocidad, true, tImg);
    }

    public void disparar() {
        // Crear un disparo en la posición actual de la nave
        DisparoAmigo disparo = new DisparoAmigo(getiPosicionX() + getiAncho() / 2, getiPosicionY() + getiAlto(), new Texture("disparo.png"), 5);
        // Aquí deberíamos añadir el disparo a una lista de disparos en la clase principal del juego
    }

    public boolean fallece() {
        // Si la nave es golpeada, dejará de estar viva
        setbVivo(false);
        return true; // Indica que la nave ha sido destruida
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
}


package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemigo extends Nave{

    public Enemigo(int iPosicionX, int iPosicionY, int iAlto, int iAncho, int iVelocidad, Texture tImg) {
        super(iPosicionX, iPosicionY, iAlto, iAncho, iVelocidad, tImg);
    }


    public void disparar() {
        // Crear un disparo en la posición actual de la nave
        DisparoEnemigo disparo = new DisparoEnemigo(getiPosicionX() + getiAncho() / 2, getiPosicionY() + getiAlto(), new Texture("disparo.png"), 5);
        // Aquí deberíamos añadir el disparo a una lista de disparos en la clase principal del juego
    }


}

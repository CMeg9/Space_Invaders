package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Disparo extends ObjetoVolador {

    // Creacion de enum para dar Origen sobre el enemigo y jugador
    public enum Origen {NaveAmiga, NaveEnemiga}
    // Nombra de origen
    private Origen origen;

    // Herencia
    public Disparo(int iPosicionX, int iPosicionY, int iAlto, int iAncho, int iVelocidad, Texture tImg, Origen origen) {
        super(iPosicionX, iPosicionY, iAlto, iAncho, iVelocidad, true, tImg);
        this.origen = origen;
    }

    // Mover el disparo, si es player se mueve arriba y hacia abajo si es enemigo
    public void moverseY() {
        // Mueve el disparo en Y según la velocidad
        if (origen == Origen.NaveAmiga) { // Si es jugador
            setiPosicionY(getiPosicionY() - getiVelocidad()); // Disparo hacia arriba
        } else if (origen == Origen.NaveEnemiga) { // Si es enemigo
            setiPosicionY(getiPosicionY() + getiVelocidad()); // Disparo hacia abajo
        }
    }
    public Origen getOrigen() {
        return origen;
    }
    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    //Metodo para dibujar el disparo
    public void draw(SpriteBatch batch) {
        batch.draw(gettImg(), getiPosicionX(), getiPosicionY(), getiAncho(), getiAlto());
    }

}

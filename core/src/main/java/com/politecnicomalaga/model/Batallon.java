package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;



public class Batallon extends Escuadron{

    private final float velocidadx;
    private final float velocidady;
        ;
    private Escuadron [] escuadrones;

    private int posicionx;
    private int posiciony;


    public Batallon(int iPosicionY, float velocidadX, float velocidadY, Texture tImgNaveEnemiga, Texture imgDisparoEnemigo, int anchoPantalla, int numNaves, float velocidadx, float velocidady) {
        super(iPosicionY, velocidadX, velocidadY, tImgNaveEnemiga, imgDisparoEnemigo, anchoPantalla, numNaves);
        this.velocidadx = velocidadx;
        this.velocidady = velocidady;

        this.posicionx = posicionx;
        this.posiciony = posiciony;
        Escuadron [] escuadrones = new Escuadron[2];

        escuadrones[0] = new Escuadron(posiciony,velocidadx, velocidady, tImgNaveEnemiga, imgDisparoEnemigo, anchoPantalla, numNaves);
        escuadrones[1] = new Escuadron(posiciony,velocidadx, velocidady, tImgNaveEnemiga, imgDisparoEnemigo, anchoPantalla, numNaves);
    }

    public void moverse (int direcX, int direcY) {
        for (Escuadron escuadron : escuadrones) {
            escuadron.moverse(direcX, direcY);
        }
    }

    public void disparar() {
        for (Escuadron escuadron : escuadrones) {
            escuadron.disparar();
        }
    }


    public boolean isEliminado() {
        for (Escuadron escuadron : escuadrones) {
            if (!escuadron.isEliminado()) {
                return false; // Si al menos un escuadrón sigue activo, el batallón no está eliminado
            }
        }
        return true; // Todos los escuadrones han sido eliminados
    }


}

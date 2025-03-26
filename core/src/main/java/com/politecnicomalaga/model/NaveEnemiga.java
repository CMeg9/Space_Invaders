package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.TimerTask;


public class NaveEnemiga extends Nave{
    ArrayList<Disparo> Disparos;
    private Disparo Disparo;


    public NaveEnemiga(int iPosicionX, int iPosicionY, int iAlto, int iAncho, int iVelocidad, Texture tImg) {
        super(iPosicionX, iPosicionY, iAlto, iAncho, iVelocidad, tImg);
    }

    private void iniciarDisparos() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                disparar();
            }
        }, 0, 1); // Primer disparo inmediato, luego cada 5 segundos
    }
    //Aqui usamos un timer lo cual no se si va a servir


    public void disparar() {
        // Crear un disparo en la posición actual de la nave
        Disparo disparo = new Disparo(getiPosicionX() + getiAncho() / 2, getiPosicionY() + getiAlto(), new Texture("disparo.png"), 5);
        // Aquí deberíamos añadir el disparo a una lista de disparos en la clase principal del juego

        Disparos.add(disparo);

    }


    public boolean fallece() {
        if (colision(Disparo)) {
            // Si la nave es golpeada, dejará de estar viva
            setbVivo(false);
        }
        return true; // Indica que la nave ha sido destruida
    }




    public boolean colision(Disparo disparo) {
        return super.colision(disparo);
    }


}

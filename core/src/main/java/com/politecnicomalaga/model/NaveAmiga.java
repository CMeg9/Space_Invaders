package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;

public class NaveAmiga extends ObjetoVolador {

    private ArrayList<DisparoAmigo> disparos;
    private float tiempoUltimoDisparo;
    private float cadenciaDisparo = 0.3f; // Puede disparar cada 0.3 segundos

    public NaveAmiga(int iPosicionX, int iPosicionY, int iAlto, int iAncho, int iVelocidad, boolean bVivo, Texture tImg, Texture imgDisparo) {
        super(iPosicionX, iPosicionY, iAlto, iAncho, iVelocidad, bVivo, tImg);
        this.disparos = new ArrayList<>();
        this.tiempoUltimoDisparo = 0;
    }

    @Override
    public void moverse(int iDireccionX, int iDireccionY) {
        super.moverse(iDireccionX, iDireccionY); // lo hereda de ObjetoVolador
    }

    public void moverseFlechas(float delta, Texture imgDisparo) {
        // Movimiento basado en teclado
        int iDireccionX = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            iDireccionX = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            iDireccionX = 1;
        }

        // Mover al jugador
        moverse(iDireccionX, 0);

        // Control de disparo
        tiempoUltimoDisparo += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && tiempoUltimoDisparo >= cadenciaDisparo) {
            disparar(imgDisparo);
            tiempoUltimoDisparo = 0;
        }
    }

    public void disparar(Texture imgDisparo) {
        // Crear un disparo desde el centro de la nave
        DisparoAmigo disparo = new DisparoAmigo(
            getiPosicionX() + getiAncho() / 2 - imgDisparo.getWidth() / 2,
            getiPosicionY() + getiAlto(), imgDisparo, 5
        );
        disparos.add(disparo);
    }

    public void actualizar(float delta, Texture imgDisparo) {
        moverseFlechas(delta, imgDisparo);

        // Actualizar disparos
        Iterator<DisparoAmigo> iter = disparos.iterator();
        while (iter.hasNext()) {
            DisparoAmigo disparo = iter.next();
            disparo.moverse(0, 1);
            if (disparo.getiPosicionY() > Gdx.graphics.getHeight()) {
                iter.remove(); // Eliminar disparos fuera de pantalla
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        // Dibuja los disparos activos
        for (DisparoAmigo disparo : disparos) {
            disparo.draw(batch);
        }
    }

    public boolean detectarColision(NaveEnemiga enemigo) {
        Iterator<DisparoAmigo> iter = disparos.iterator();
        while (iter.hasNext()) {
            DisparoAmigo disparo = iter.next();
            if (disparo.colision(enemigo)) {
                enemigo.setbVivo(false);
                iter.remove(); // Eliminar disparo después de colisionar
                return true;
            }
        }
        return false;
    }
}


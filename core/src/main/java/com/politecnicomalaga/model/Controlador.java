package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import java.util.ArrayList;

public class Controlador {
    private float xPantalla;
    private float yPantalla;
    private float xInput;
    private float yInput;

    private Texture imgNaveAmiga;
    private Texture imgNaveEnemiga;
    private Texture imgDisparo;
    private Texture imgDisparoEnemigo;

    private Nave nave;

    private NaveAmiga jugador;
    private ArrayList<NaveEnemiga> enemigos;
    private ArrayList<DisparoAmigo> disparos;

    public Controlador(float xPantalla, float yPantalla) {
        this.xPantalla = xPantalla;
        this.yPantalla = yPantalla;
        this.xInput = 0;
        this.yInput = 0;

        // Cargar imágenes
        this.imgNaveAmiga = new Texture("NaveAmiga.png");
        this.imgNaveEnemiga = new Texture("NaveEnemiga.png");
        this.imgDisparo = new Texture("disparo.png");
        this.imgDisparoEnemigo = new Texture("disparo_enemigo.png");

        // Inicializar nave y enemigos
        this.nave = new Nave((int) (xPantalla / 2), 50, 40, 40, 5, imgNaveAmiga);
        this.enemigos = new ArrayList<>();
        this.disparos = new ArrayList<>();
        iniciarEnemigos();
    }

    public void iniciarEnemigos() {
        for (int i = 0; i < 2; i++) { // Dos filas
            for (int j = 0; j < 12; j++) { // Doce enemigos por fila
                enemigos.add(new NaveEnemiga(50 + j * 50, (int) yPantalla - i * 50, 40, 40, 2, imgNaveEnemiga));
            }
        }
    }

    public void verInputs() {
        if (Gdx.input.isTouched()) {
            xInput = Gdx.input.getX();
            yInput = Gdx.input.getY();

            // Disparo si se toca la parte superior de la pantalla
            if (yInput < yPantalla / 2) {
                disparar();
            }
            // Movimiento a la izquierda
            if (xInput < xPantalla / 2) {
                nave.moverse(-1, 0);
            }
            // Movimiento a la derecha
            else {
                nave.moverse(1, 0);
            }
        }
    }

    public void disparar() {
        disparos.add(new DisparoAmigo(nave.getiPosicionX() + nave.getiAncho() / 2, nave.getiPosicionY() + nave.getiAlto(), imgDisparo, 5));
    }

    public void actualizar() {
        // Mover disparos
        for (DisparoAmigo disparo : disparos) {
            disparo.moverse(0, 1);
        }

        // Verificar colisiones entre disparos y enemigos
        ArrayList<NaveEnemiga> enemigosEliminados = new ArrayList<>();
        ArrayList<DisparoAmigo> disparosEliminados = new ArrayList<>();

        for (NaveEnemiga enemigo : enemigos) {
            for (DisparoAmigo disparo : disparos) {
                if (enemigo.colision(disparo)) {
                    enemigosEliminados.add(enemigo);
                    disparosEliminados.add(disparo);
                    break;
                }
            }
        }

        enemigos.removeAll(enemigosEliminados);
        disparos.removeAll(disparosEliminados);

        // Verificar si la nave ha sido alcanzada
        for (NaveEnemiga enemigo : enemigos) {
            if (nave.colision(enemigo)) {
                nave.fallece();
            }
        }
    }

    public void draw(SpriteBatch batch) {
        nave.draw(batch);
        for (NaveEnemiga enemigo : enemigos) {
            enemigo.draw(batch);
        }
        for (DisparoAmigo disparo : disparos) {
            disparo.draw(batch);
        }
    }

    public boolean juegoTerminado() {
        return !nave.isbVivo();
    }
}

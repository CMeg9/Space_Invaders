package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;

public class NaveEnemiga extends ObjetoVolador {
    private ArrayList<DisparoEnemigo> disparos;
    private float tiempoUltimoDisparo;
    private final float cadenciaDisparo = 1.5f; // Dispara cada 1.5 segundos
    private final Texture imgDisparoEnemigo;

    public NaveEnemiga(int posX, int posY, int ancho, int alto, int velocidad, Texture imagen) {
        super(posX, posY, ancho, alto, velocidad, imagen);
        this.disparos = new ArrayList<>();
        this.tiempoUltimoDisparo = 0;
        this.imgDisparoEnemigo = new Texture("disparo_enemigo.png"); // Textura compartida para todos los disparos
    }

    // Método para disparar con cadencia
    public void disparar() {
        DisparoEnemigo disparo = new DisparoEnemigo(getPosX() + getAncho() / 2, getPosY(), imgDisparoEnemigo, 5);
        disparos.add(disparo);
    }

    public void actualizar(float delta) {
        tiempoUltimoDisparo += delta;
        if (tiempoUltimoDisparo >= cadenciaDisparo) {
            disparar();
            tiempoUltimoDisparo = 0; // Resetear el tiempo de disparo
        }

        // Limpiar los disparos fuera de la pantalla usando removeIf
        disparos.removeIf(disparo -> disparo.getPosY() < 0);

        // Mover los disparos hacia abajo
        for (DisparoEnemigo disparo : disparos) {
            disparo.moverse(Direccion.ABAJO);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getImagen(), getPosX(), getPosY(), getAncho(), getAlto());
        for (DisparoEnemigo disparo : disparos) {
            disparo.draw(batch);
        }
    }

    // Método para detectar colisiones entre un disparo de la nave amiga y la nave enemiga
    public boolean detectarColision(DisparoAmigo disparo) {
        if (disparo.colisionaCon(this)) {
            setEstaVivo(false); // La nave enemiga muere al recibir el disparo
            return true;
        }
        return false;
    }

    // Getter para los disparos (en caso de que sea necesario acceder desde otra clase)
    public ArrayList<DisparoEnemigo> getDisparos() {
        return disparos;
    }

    // Puedes agregar más lógica de control, como la muerte de la nave enemiga
}

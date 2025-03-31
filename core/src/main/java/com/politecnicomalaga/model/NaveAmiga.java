package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;

public class NaveAmiga extends ObjetoVolador {
    private ArrayList<Disparo> disparosAmigos;
    private float tiempoUltimoDisparo;
    private final float cadenciaDisparo = 0.3f;
    private int vidas;

    public NaveAmiga(int posX, int posY, int ancho, int alto, int velocidad, Texture imagen, int vidas) {
        super(posX, posY, ancho, alto, velocidad, imagen);
        this.disparosAmigos = new ArrayList<>();
        this.tiempoUltimoDisparo = 0;
        this.vidas = vidas;
    }

    public int getVidas() { return vidas; }
    public void setVidas(int vidas) { this.vidas = vidas; }

    public void actualizar(float delta, Texture imgDisparo) {
        moverseFlechas();
        gestionarDisparos(delta, imgDisparo);
    }

    private void moverseFlechas() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && getPosX() > 0) {
            moverse(direccion.IZQUIERDA);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && getPosX() < Gdx.graphics.getWidth() - getAncho()) {
            moverse(direccion.DERECHA);
        }
    }

    private void gestionarDisparos(float delta, Texture imgDisparo) {
        tiempoUltimoDisparo += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && tiempoUltimoDisparo >= cadenciaDisparo) {
            disparar(imgDisparo);
            tiempoUltimoDisparo = 0;
        }

        // Limpiar los disparos que han salido de la pantalla
        disparosAmigos.removeIf(disparo -> disparo.getPosY() > Gdx.graphics.getHeight());

        // Mover los disparos
        for (Disparo disparo : disparosAmigos) {
            disparo.moverse(direccion.ARRIBA);
        }
    }

    private void disparar(Texture imgDisparo) {
        Disparo disparo = new Disparo(getPosX() + getAncho() / 2 - imgDisparo.getWidth() / 2, getPosY() + getAlto(), imgDisparo, 5);
        disparosAmigos.add(disparo);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getImagen(), getPosX(), getPosY(), getAncho(), getAlto());
        for (Disparo disparo : disparosAmigos) {
            disparo.draw(batch);
        }
    }

    // Método para detectar colisiones con una nave enemiga
    public boolean detectarColision(NaveEnemiga enemigo) {
        Iterator<Disparo> iter = disparosAmigos.iterator();
        while (iter.hasNext()) {
            Disparo disparo = iter.next();
            if (Disparo.colisionaCon(enemigo)) {
                enemigo.setEstaVivo(false);
                iter.remove();
                return true; // Colisión detectada
            }
        }
        return false; // No hubo colisión
    }

    // Método para reducir vidas cuando la nave amiga colisiona con una nave enemiga
    public void perderVida() {
        vidas--;
        if (vidas <= 0) {
            setEstaVivo(false);  // La nave amiga muere cuando se quedan sin vidas
        }
    }
}

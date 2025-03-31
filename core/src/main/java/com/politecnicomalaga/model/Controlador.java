package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controlador {
    private NaveAmiga naveAmiga;
    private Batallon batallon;
    private List<Disparo> disparos;
    private boolean juegoTerminado;
    private boolean victoria;
    private Texture imagenVictoria;
    private Texture imagenDerrota;

    public Controlador() {
        naveAmiga = new NaveAmiga();
        batallon = new Batallon();
        disparos = new ArrayList<>();
        juegoTerminado = false;
        victoria = false;
        imagenVictoria = new Texture("ImagenVictoria.png");
        imagenDerrota = new Texture("ImagenDerrota.png");
    }

    public void actualizar() {
        if (juegoTerminado) {
            return;
        }

        // Control de la nave amiga
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            naveAmiga.moverIzquierda();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            naveAmiga.moverDerecha();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            disparos.add(new DisparoAmigo(naveAmiga.getX() + naveAmiga.getWidth() / 2, naveAmiga.getY()));
        }

        naveAmiga.actualizar();
        batallon.actualizar();

        //Para una lista, en este caso iterador de disparos, si se sale de la pantalla que se remove
        Iterator<Disparo> iter = disparos.iterator();
        while (iter.hasNext()) {
            Disparo disparo = iter.next();
            disparo.actualizar();
            if (disparo.getY() > Gdx.graphics.getHeight() || disparo.getY() < 0) {
                iter.remove();
            }
        }

        // Lógica de colisiones
        for (Disparo disparo : disparos) {
            if (disparo instanceof DisparoAmigo) {
                batallon.verificarColision(disparo, iter);
            } else if (disparo.bounds.overlaps(naveAmiga.bounds)) {
                juegoTerminado = true;
                victoria = false;
            }
        }

        // Verificar si todos los enemigos han sido derrotados
        if (batallon.todosDerrotados()) {
            juegoTerminado = true;
            victoria = true;
        }
    }

    //poner imagen de victoria o derrota dependiendo si se gana o se pierde
    //si no se ha terminado de jugar que se dubujen las naves y disparos
    public void dibujar(SpriteBatch batch) {
        if (juegoTerminado) {
            if (victoria) {
                batch.draw(imagenVictoria, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            } else {
                batch.draw(imagenDerrota, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        } else {
            naveAmiga.dibujar(batch);
            batallon.dibujar(batch);
            for (Disparo disparo : disparos) {
                disparo.dibujar(batch);
            }
        }
    }
}

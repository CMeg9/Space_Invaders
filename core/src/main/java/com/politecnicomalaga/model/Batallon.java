package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Batallon {
    private List<Escuadron> escuadrones;

    public Batallon() {
        escuadrones = new ArrayList<>();
        float espacioVertical = Gdx.graphics.getHeight() / 6;
        for (int i = 0; i < 5; i++) {
            escuadrones.add(new Escuadron(10, espacioVertical * (i + 1)));
        }
    }

    public void actualizar() {
        for (Escuadron escuadron : escuadrones) {
            escuadron.actualizar();
        }
    }

    public void dibujar(SpriteBatch batch) {
        for (Escuadron escuadron : escuadrones) {
            escuadron.dibujar(batch);
        }
    }

    public void verificarColision(Disparo disparo, Iterator<Disparo> iter) {
        for (Escuadron escuadron : escuadrones) {
            escuadron.verificarColision(disparo, iter);
        }
    }

    public boolean todosDerrotados() {
        for (Escuadron escuadron : escuadrones) {
            if (!escuadron.estaDerrotado()) {
                return false;
            }
        }
        return true;
    }
}

package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Escuadron {
    private List<NaveEnemiga> naves;

    //pone naves dependiendo del espacio en pantalla
    public Escuadron(int cantidad, float y) {
        naves = new ArrayList<>();
        float espacio = Gdx.graphics.getWidth() / (cantidad + 1);
        for (int i = 0; i < cantidad; i++) {
            naves.add(new NaveEnemiga(espacio * (i + 1), y));
        }
    }

    public void actualizar() {
        for (NaveEnemiga nave : naves) {
            nave.actualizar();
        }
    }

    public void dibujar(SpriteBatch batch) {
        for (NaveEnemiga nave : naves) {
            nave.dibujar(batch);
        }
    }

    //Con un iterador de disparos y otro de naves enemigas, si se colapsan se eliminan ambos
    public void verificarColision(Disparo disparo, Iterator<Disparo> iter) {
        Iterator<NaveEnemiga> naveIter = naves.iterator();
        while (naveIter.hasNext()) {
            NaveEnemiga nave = naveIter.next();
            if (nave.limites.overlaps(disparo.limites)) {
                iter.remove();
                naveIter.remove();
                break;
            }
        }
    }

    public boolean estaDerrotado() {
        return naves.isEmpty();
    }
}

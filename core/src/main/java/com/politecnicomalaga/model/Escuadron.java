package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Escuadron {
    private static ObjetoVolador.direccion direccion = ObjetoVolador.direccion.DERECHA;
    private List<NaveEnemiga> navesEnemigas;
    private Texture texturaNave;
    private Texture texturaDisparo;
    private int naveAncho, naveAlto, screenWidth;
    private Random random;

    public Escuadron(Texture texturaNave, int totalNaves, int posX, int posY, int velocidad,
                     int naveAncho, int naveAlto, int screenWidth, Texture texturaDisparo) {
        this.naveAncho = naveAncho;
        this.naveAlto = naveAlto;
        this.screenWidth = screenWidth;
        this.texturaNave = texturaNave;
        this.texturaDisparo = texturaDisparo;
        this.navesEnemigas = new ArrayList<>();
        this.random = new Random();

        for (int i = 0; i < totalNaves; i++) {
            navesEnemigas.add(new NaveEnemiga(posX, posY, naveAncho, naveAlto, velocidad, texturaNave, texturaDisparo));
            posX += naveAncho + naveAncho / 5;
        }
    }

    public int actualizarYdibujar(SpriteBatch batch, Batallon batallon) {
        int totalNavesVivas = 0;
        direccion = batallon.getDireccion();

        int maxX = -1, minX = screenWidth;
        for (NaveEnemiga nave : navesEnemigas) {
            if (nave.isEstaVivo()) {
                totalNavesVivas++;
                nave.moverse(direccion);
                nave.draw(batch);
                maxX = Math.max(maxX, nave.getiPosX());
                minX = Math.min(minX, nave.getiPosX());
                nave.updateDisparos();
                nave.drawDisparos(batch);
            }
        }

        if ((direccion == ObjetoVolador.direccion.DERECHA && maxX > screenWidth - naveAncho) ||
            (direccion == ObjetoVolador.direccion.IZQUIERDA && minX < naveAncho)) {
            bajarEscuadron();
            batallon.pedirCambioDireccion();
        }

        return totalNavesVivas;
    }

    private void bajarEscuadron() {
        for (NaveEnemiga nave : navesEnemigas) {
            nave.setiPosY(nave.getiPosY() - naveAlto / 3);
        }
    }

    public void procesarDisparosAmigos(List<Disparo> disparosAmigos) {
        for (NaveEnemiga nave : navesEnemigas) {
            if (nave.isEstaVivo()) {
                comprobarImpacto(nave, disparosAmigos);
            }
        }
    }

    private void comprobarImpacto(NaveEnemiga nave, List<Disparo> disparosAmigos) {
        disparosAmigos.removeIf(disparo -> disparo.colisionaCon(nave) && (nave.setEstaVivo(false)));
    }

    public void disparar() {
        if (!navesEnemigas.isEmpty()) {
            NaveEnemiga nave = navesEnemigas.get(random.nextInt(navesEnemigas.size()));
            if (nave.isEstaVivo()) {
                nave.disparar(texturaDisparo);
            }
        }
    }

    public List<NaveEnemiga> getNaves() {
        return navesEnemigas;
    }
}

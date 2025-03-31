package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Batallon {
    private List<Escuadron> escuadrones;
    private int vel = 5;
    private int posInicialY;
    private int posInicialX;
    private int gameLevel;
    private ObjetoVolador.direccion direccion;
    private boolean peticionCambioDireccion = false;
    private Random random;
    private int ultimoTiempoDisparo = -1;

    public Batallon(String baseName_fileName_texture, int totalEscuadrones, int totalNavesPorEscuadron,
                    int naveEnemigaAncho, int naveEnemigaAlto, int screenWidth, int screenHeight,
                    Texture texdisparo, int gameLevel) {

        random = new Random();
        direccion = ObjetoVolador.direccion.IZQUIERDA;
        escuadrones = new ArrayList<>(totalEscuadrones);
        posInicialY = screenHeight / 2;
        posInicialX = (screenWidth - (totalNavesPorEscuadron * naveEnemigaAncho) -
            ((totalNavesPorEscuadron + 1) * naveEnemigaAncho / 2)) / 2;

        for (int i = 0; i < totalEscuadrones; i++) {
            escuadrones.add(new Escuadron("nave_enemiga.png", totalNavesPorEscuadron, posInicialX, posInicialY,
                vel, naveEnemigaAncho, naveEnemigaAlto, screenWidth, texdisparo));
            posInicialY += naveEnemigaAlto + naveEnemigaAlto / 5;
        }
        this.gameLevel = gameLevel;
    }

    public int draw(SpriteBatch batch) {
        int totalNaves = 0;
        for (Escuadron escuadron : escuadrones) {
            totalNaves += escuadron.actualizarYdibujar(batch, this);
        }

        if (peticionCambioDireccion) {
            cambiarDireccion();
        }

        disparar();
        return totalNaves;
    }

    public void pedirCambioDireccion() {
        peticionCambioDireccion = true;
    }

    private void cambiarDireccion() {
        peticionCambioDireccion = false;
        direccion = (direccion == ObjetoVolador.direccion.DERECHA) ?
            ObjetoVolador.direccion.IZQUIERDA :
            ObjetoVolador.direccion.DERECHA;
    }

    public ObjetoVolador.direccion getDireccion() {
        return direccion;
    }

    public void procesarDisparosAmigos(List<Disparo> disparosAmigos) {
        for (Escuadron escuadron : escuadrones) {
            escuadron.procesarDisparosAmigos(disparosAmigos);
        }
    }

    private void disparar() {
        int tiempoActual = (int) (System.currentTimeMillis() / 1000) % 10;
        if (tiempoActual != ultimoTiempoDisparo) {
            ultimoTiempoDisparo = tiempoActual;

        }
    }
}

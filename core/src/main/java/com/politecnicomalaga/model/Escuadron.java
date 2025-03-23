package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Escuadron extends NaveEnemiga {

    private ArrayList<NaveEnemiga> navesEnemigas; // Lista de naves enemigas en el escuadrón
    private boolean eliminado;                    // Indica si el escuadrón ha sido eliminado
    private int anchoPantalla;                   // Limite de la pantalla
    private float velocidadX, velocidadY;        // Velocidad del escuadrón en los ejes X e Y

    // Constructor
    public Escuadron(int iPosicionY, float velocidadX, float velocidadY, Texture tImgNaveEnemiga, Texture imgDisparoEnemigo, int anchoPantalla, int numNaves) {
        // Llamada al constructor de NaveEnemiga
        super(0, iPosicionY, 30, 30, 0, tImgNaveEnemiga);

        this.navesEnemigas = new ArrayList<>();
        this.eliminado = false;
        this.anchoPantalla = anchoPantalla;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;

        // Crear las naves enemigas y distribuirlas en el escuadrón
        int espacioEntreNaves = anchoPantalla / (numNaves + 1); // Espacio entre naves
        for (int i = 1; i <= numNaves; i++) {
            int posX = espacioEntreNaves * i;
            NaveEnemiga nave = new NaveEnemiga(posX, iPosicionY, 30, 30, 0, tImgNaveEnemiga);
            navesEnemigas.add(nave);
        }
    }

    // Metodo para dibujar todas las naves enemigas del escuadrón
    @Override
    public void draw(SpriteBatch batch) {
        for (NaveEnemiga nave : navesEnemigas) {
            nave.draw(batch);
        }
    }

    // Metodo para mover el escuadrón
    @Override
    public void moverse(int direcX, int direcY) {
        for (NaveEnemiga nave : navesEnemigas) {
            nave.moverse(direcX, direcY);
        }

        // Verificar si el escuadrón ha alcanzado los límites de la pantalla
        if (limitePantalla()) {
            cambioDireccion();
        }
    }

    // Metodo para verificar si el escuadrón ha alcanzado los límites de la pantalla
    private boolean limitePantalla() {
        for (NaveEnemiga nave : navesEnemigas) {
            if (nave.getiPosicionX() <= 0 || nave.getiPosicionX() + nave.getiAncho() >= anchoPantalla) {
                return true;
            }
        }
        return false;
    }

    // Metodo para cambiar la dirección del escuadrón
    private void cambioDireccion() {
        velocidadX = -velocidadX; // Invierte la dirección en el eje X
        for (NaveEnemiga nave : navesEnemigas) {
            nave.setiPosicionY(nave.getiPosicionY() - 20); // Desciende el escuadrón
        }
    }

    // Metodo para disparar proyectiles desde las naves enemigas
    @Override
    public void disparar() {
        for (NaveEnemiga nave : navesEnemigas) {
            if (Math.random() < 0.01) { // Probabilidad de disparar (1%)
                nave.disparar();
            }
        }
    }

    // Metodo para verificar si el escuadrón ha sido eliminado
    public boolean isEliminado() {
        return navesEnemigas.isEmpty();
    }

    // Metodo para eliminar una nave enemiga del escuadrón
    public void eliminarNave(NaveEnemiga nave) {
        navesEnemigas.remove(nave);
    }

    // Getter para la lista de naves enemigas
    public ArrayList<NaveEnemiga> getNavesEnemigas() {
        return navesEnemigas;
    }


}

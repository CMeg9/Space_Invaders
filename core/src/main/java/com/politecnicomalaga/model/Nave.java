package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Nave extends ObjetoVolador {

    private List<Disparo> listaDisparos;
    private boolean esAmiga;
    private boolean estaVivo;
    private direccion direccion;

    // Constructor mejorado
    public Nave(int iPosicionX, int iPosicionY, int iAlto, int iAncho, int iVelocidad, boolean bVivo, Texture tImg, boolean esAmiga, ObjetoVolador.direccion direccion) {
        super(iPosicionX, iPosicionY, iAlto, iAncho, iVelocidad, tImg);  // Llamamos al constructor de la clase padre
        this.listaDisparos = new ArrayList<>();
        this.esAmiga = esAmiga;
        this.direccion = direccion;
        this.estaVivo = bVivo;
    }

    // Getter de dirección corregido
    public ObjetoVolador.direccion getDireccion() {
        return this.direccion;
    }

    // Setter de dirección
    public void setDireccion(ObjetoVolador.direccion direccion) {
        this.direccion = direccion;
    }

    // Método para disparar
    public boolean disparar(Texture tImg) {
        // Crear un disparo en la posición actual de la nave un poco por encima
        Disparo disparo = new Disparo(getPosX() + getAncho() / 2, getPosY() + getAlto(), tImg, 5);
        // Añadir disparo a la lista de disparos
        return listaDisparos.add(disparo);
    }

    // Método para dibujar los disparos
    public void dibujarDisparos(SpriteBatch spriteBatch) {
        for (Disparo disparo : listaDisparos) {
            disparo.draw(spriteBatch);
        }
    }

    // Método para actualizar la lista de disparos
    public void actualizarDisparos() {
        // Eliminar disparos fuera de la pantalla o ya no activos
        listaDisparos.removeIf(disparo -> !disparo.isEstaVivo());
    }

    // Getter para saber si la nave está viva
    public boolean estaVivo() {
        return estaVivo;
    }

    // Método para cambiar el estado de vida de la nave
    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    // Método para manejar la colisión con otros objetos voladores
    public boolean colisionaCon(ObjetoVolador otro) {
        return super.colisionaCon(otro);  // Delegar la comprobación de colisiones a la clase padre
    }
}

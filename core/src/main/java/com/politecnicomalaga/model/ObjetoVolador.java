package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class ObjetoVolador {
    private int posX, posY; // Posición en X y Y
    private int ancho, alto; // Tamaño del objeto
    private int velocidadX, velocidadY; // Velocidad en el eje X y Y
    private boolean estaVivo; // Si el objeto está vivo o destruido
    private Texture imagen; // Imagen asociada al objeto

    // Constructor
    public ObjetoVolador(int posX, int posY, int ancho, int alto, int velocidadX, int velocidadY, Texture imagen) {
        this.posX = posX;
        this.posY = posY;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.imagen = imagen;
        this.estaVivo = true;
    }

    // Getters y setters
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public boolean isEstaVivo() { return estaVivo; }
    public Texture getImagen() { return imagen; }

    public void setPosX(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }
    public void setVelocidadX(int velocidadX) { this.velocidadX = velocidadX; }
    public void setVelocidadY(int velocidadY) { this.velocidadY = velocidadY; }
    public void setEstaVivo(boolean estaVivo) { this.estaVivo = estaVivo; }

    // Movimiento del objeto en los ejes X y Y
    public void moverse(direccion direccion) {
        switch (direccion) {
            case ARRIBA -> posY += velocidadY;
            case ABAJO -> posY -= velocidadY;
            case IZQUIERDA -> posX -= velocidadX;
            case DERECHA -> posX += velocidadX;
        }
    }

    // Colisión con otro objeto
    public boolean colisionaCon(ObjetoVolador otro) {
        Rectangle rect1 = new Rectangle(posX, posY, ancho, alto);
        Rectangle rect2 = new Rectangle(otro.getPosX(), otro.getPosY(), otro.getAncho(), otro.getAlto());
        return rect1.overlaps(rect2); // Verifica si los rectángulos se superponen
    }

    // Enumeración para direcciones
    public enum direccion {
        ARRIBA, ABAJO, IZQUIERDA, DERECHA;
    }
}

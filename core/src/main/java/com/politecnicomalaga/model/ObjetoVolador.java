package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObjetoVolador {
    private int iPosicionX;
    private int iPosicionY;
    private int iAlto;
    private int iAncho;
    private int iVelocidad;
    private boolean bVivo;
    private Texture tImg;

    public ObjetoVolador(int iPosicionX, int iPosicionY, int iAlto, int iAncho, int iVelocidad, boolean bVivo, Texture tImg) {
        this.iPosicionX = iPosicionX;
        this.iPosicionY = iPosicionY;
        this.iAlto = iAlto;
        this.iAncho = iAncho;
        this.iVelocidad = iVelocidad;
        this.bVivo = bVivo;
        this.tImg = tImg;
    }

    public int getiPosicionX() {
        return iPosicionX;
    }

    public void setiPosicionX(int iPosicionX) {
        this.iPosicionX = iPosicionX;
    }

    public int getiPosicionY() {
        return iPosicionY;
    }

    public void setiPosicionY(int iPosicionY) {
        this.iPosicionY = iPosicionY;
    }

    public int getiAlto() {
        return iAlto;
    }

    public void setiAlto(int iAlto) {
        this.iAlto = iAlto;
    }

    public int getiAncho() {
        return iAncho;
    }

    public void setiAncho(int iAncho) {
        this.iAncho = iAncho;
    }

    public int getiVelocidad() {
        return iVelocidad;
    }

    public void setiVelocidad(int iVelocidad) {
        this.iVelocidad = iVelocidad;
    }

    public boolean isbVivo() {
        return bVivo;
    }

    public void setbVivo(boolean bVivo) {
        this.bVivo = bVivo;
    }

    public Texture gettImg() {
        return tImg;
    }

    public void settImg(Texture tImg) {
        this.tImg = tImg;
    }

    public void moverse(int iDireccionX, int iDireccionY){
        // Formula de movimiento para nuevas posiciones
        iPosicionX += iVelocidad*iDireccionX;
        iPosicionY += iVelocidad*iDireccionY;

        //Limites, ANCHO_JUEGO y ALTO_JUEGO son/deberian ser variables tipo:
        // "public static final int", para que solo se cambie de un lugar que podria ser
        //una clase "juego", sino se cambia por unos numero y ya esta

        //Anchura de pantalla/mapa
        if (iPosicionX<0) iPosicionX=0;
        if (iPosicionX>ANCHO_JUEGO-iAncho) iPosicionX=ANCHO_JUEGO-iAncho;

        //Alto de pantalla/mapa
        if (iPosicionY<0) iPosicionY=0;
        if (iPosicionY>ALTO_JUEGO-iAlto) iPosicionY=ALTO_JUEGO-iAlto;

        //No se si esto funcionara pero yo le veo sentido
    }

    //Basicamente es de la bibilioteca pone textura al objeto volador
    //siempre y cuando tenga una textura y este vivo
    public void draw(SpriteBatch spriteBatch){
        if (tImg != null && bVivo){
            spriteBatch.draw(tImg, iPosicionX,iPosicionY,iAncho,iAlto);
        }
    }

    //Se esta comparando con "otro" objeto volador
    //Hace colision si ocurre lo siguente:
    //La posición izquierda de este objeto es menor que la posición derecha del otro objeto
    //La posición derecha de este objeto es mayor que la posición izquierda del otro objeto
    //La posición inferior de este objeto es menor que la posición superior del otro objeto
    //La posición superior de este objeto es mayor que la posición inferior del otro objeto

    //Por lo que he estado investigando esta técnica se llama "Bounding Box"
    //Por si hay que buscar más información sobre ella
    public boolean colision(ObjetoVolador otro){
        return this.iPosicionX<otro.iPosicionX + otro.iAncho &&
            this.iPosicionX+this.iAncho > otro.iPosicionX &&
            this.iPosicionY<otro.iPosicionY + otro.iAlto &&
            this.iPosicionY+this.iAlto > otro.iPosicionY;
    }
}

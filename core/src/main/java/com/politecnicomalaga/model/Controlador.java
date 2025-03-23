package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controlador {

    private float xPantalla;

    private float yPantalla;

    private float xInput;

    private float yInput;

    private Texture imgNaveAmiga;

    private Texture imgNaveEnemiga;

    private Texture imgDisparo;

    private Texture imgDisparoEnemigo;

    public Controlador(float xPantalla, float yPantalla, float xInput, float yInput) {
        this.xPantalla = xPantalla;
        this.yPantalla = yPantalla;
        this.xInput = xInput;
        this.yInput = yInput;
        this.imgNaveAmiga = new Texture("NaveAmiga.png");
        this.imgNaveEnemiga = new Texture("NaveEnemiga.png");
        this.imgDisparo = new Texture("disparo.png");
        this.imgDisparoEnemigo = new Texture("disparo_enemigo.png");
    }

    public float getxPantalla() {
        return xPantalla;
    }

    public void setxPantalla(float xPantalla) {
        this.xPantalla = xPantalla;
    }

    public float getyPantalla() {
        return yPantalla;
    }

    public void setyPantalla(float yPantalla) {
        this.yPantalla = yPantalla;
    }

    public float getxInput() {
        return xInput;
    }

    public void setxInput(float xInput) {
        this.xInput = xInput;
    }

    public float getyInput() {
        return yInput;
    }

    public void setyInput(float yInput) {
        this.yInput = yInput;
    }


    public void draw(SpriteBatch batch){

    }

    public void render(){

    }
    public void verImputs(){}
}


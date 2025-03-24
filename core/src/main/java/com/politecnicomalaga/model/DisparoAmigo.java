package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;

public class DisparoAmigo extends Disparo{
    public DisparoAmigo(int i, int i1, Texture texture, int i2) {
        super(i, i1, texture, i2);
    }


    //El disparo amigo tiene que golpear nave enemiga tiene que saber si lo que golpea
    //es una nave enemiga


    @Override
    public boolean colision(ObjetoVolador otro) {
        return super.colision(otro);
    }
}

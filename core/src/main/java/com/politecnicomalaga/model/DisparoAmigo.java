package com.politecnicomalaga.model;

import com.badlogic.gdx.graphics.Texture;

public class DisparoAmigo extends Disparo{
    public DisparoAmigo(int iPosicionX, int iPosicionY, int iAlto, int iAncho, int iVelocidad, Texture tImg){

        super(iPosicionX,iPosicionY,iAlto,iAncho,iVelocidad,tImg);

    }

    //El disparo amigo tiene que golpear nave enemiga tiene que saber si lo que golpea
    //es una nave enemiga


    @Override
    public boolean colision(ObjetoVolador otro) {
        return super.colision(otro);
    }
}

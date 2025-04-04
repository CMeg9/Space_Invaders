package com.politecnicomalaga.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.politecnicomalaga.model.Controlador;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Controlador controlador;

    @Override
    public void create() {
        batch = new SpriteBatch();
        controlador = new Controlador();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        controlador.actualizar();
        controlador.dibujar(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

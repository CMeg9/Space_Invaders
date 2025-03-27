package com.politecnicomalaga.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.model.Controlador;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Texture disparo;
    private Texture disparoEnemig;
    private Texture nave_enemiga;

    public Controlador controlador;






    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        image = new Texture("NaveAmiga.png");
        nave_enemiga = new Texture("NaveEnemiga.png");
        disparo = new Texture("disparo.png");
        disparoEnemig = new Texture("disparo_enemigo.png");

        controlador = new Controlador(500,500);
        controlador.iniciarEnemigos();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        controlador.verInputs();


        controlador.disparar();
        controlador.actualizar();
        controlador.juegoTerminado();
        batch.begin();

        controlador.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

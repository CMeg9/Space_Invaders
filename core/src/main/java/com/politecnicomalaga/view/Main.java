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

        controlador = new Controlador(100,100);
        controlador.iniciarEnemigos();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        controlador.verInputs();
        batch.begin();
        controlador.disparar();
        controlador.actualizar();
        controlador.juegoTerminado();
        controlador.draw(batch);
        batch.draw(image, 1, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

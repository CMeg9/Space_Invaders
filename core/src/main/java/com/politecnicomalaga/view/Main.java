package com.politecnicomalaga.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.model.Controlador;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture texNaveAmiga;
    private Texture texNaveEnemiga;
    private Texture texDisparoAmigo;
    private Texture texDisparoEnemigo;

    // Controlador que maneja la lógica del juego
    private Controlador controlador;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Cargando las imágenes
        texNaveAmiga = new Texture("NaveAmiga.png");
        texNaveEnemiga = new Texture("NaveEnemiga.png");
        texDisparoAmigo = new Texture("disparo.png");
        texDisparoEnemigo = new Texture("disparo_enemigo.png");

        // Inicializando el controlador
        controlador = new Controlador(500, 500, texNaveAmiga, texNaveEnemiga, texDisparoAmigo, texDisparoEnemigo);
        controlador.iniciarEnemigos();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);  // Fondo oscuro

        controlador.verInputs();  // Procesar entradas del usuario
        controlador.actualizar();  // Actualizar la lógica del juego
        controlador.juegoTerminado();  // Verificar si el juego ha terminado

        batch.begin();
        controlador.draw(batch);  // Dibujar todo en la pantalla
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texNaveAmiga.dispose();
        texNaveEnemiga.dispose();
        texDisparoAmigo.dispose();
        texDisparoEnemigo.dispose();
    }
}

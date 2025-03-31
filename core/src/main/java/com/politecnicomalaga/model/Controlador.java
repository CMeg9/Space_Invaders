package com.politecnicomalaga.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class Controlador {
    private NaveAmiga jugador;
    private Batallon batallon;
    private ArrayList<Disparo> disparos;
    private ArrayList<NaveEnemiga> enemigos;
    private Music music_playing;
    private Music vgmdisparo;
    private Texture gameover;
    private Texture gamewin;
    private int totalNavesVivas;

    public Controlador() {
        // Inicializar texturas, sonidos y objetos
        this.jugador = new NaveAmiga(Gdx.graphics.getWidth() / 2, 0, 1, 30, 30, new Texture("ship.png"), true, ObjetoVolador.direccion.IZQ, 3);
        this.batallon = new Batallon("enemy", 3, 8, 30, 30, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new Texture("bullet.png"), 1);
        this.enemigos = new ArrayList<>();
        this.disparos = new ArrayList<>();
        this.music_playing = Gdx.audio.newMusic(Gdx.files.internal("cadetOST.mp3"));
        this.vgmdisparo = Gdx.audio.newMusic(Gdx.files.internal("shoot.mp3"));
        this.gameover = new Texture("gameover2.jpeg");
        this.gamewin = new Texture("win.png");
        this.music_playing.setLooping(true);
        this.music_playing.play();
        iniciarEnemigos();
    }

    public void manejarEntradas() {
        if (Gdx.input.isTouched()) {
            int iPosXClicked = Gdx.input.getX();
            int iPosYClicked = Gdx.input.getY();

            if (iPosYClicked > Gdx.graphics.getHeight() / 2) {
                if (iPosXClicked < jugador.getPosX()) {
                    jugador.setDireccion(ObjetoVolador.direccion.IZQUIERDA);
                } else {
                    jugador.setDireccion(ObjetoVolador.direccion.DERECHA);
                }
                jugador.moverse(jugador.getDireccion());
            } else {
                if (Gdx.input.justTouched()) {
                    jugador.disparar(new Texture("disparo.png"));
                    vgmdisparo.play();
                }
            }
        }
    }

    public void actualizar() {
        // Actualizar disparos del jugador
        jugador.updateDisparos();
        gestionarColisiones();
    }

    public void draw(SpriteBatch batch) {
        if (jugador.isEstaVivo()) {
            jugador.drawDisparos(batch);
            batch.draw(jugador.getImagen(), jugador.getPosX(), jugador.getPosY(), jugador.getAncho(), jugador.getAlto());
        }

        // Dibujar batallón de enemigos
        totalNavesVivas = batallon.draw(batch);

        // Verificar estado del juego
        if (!jugador.isEstaVivo()) {
            batch.draw(gameover, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if (totalNavesVivas == 0) {
            batch.draw(gamewin, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            music_playing.stop();
        }
    }

    private void gestionarColisiones() {
        // Comprobar las colisiones de los disparos con los enemigos
        for (Disparo disparo : jugador.getDisparos()) {
            for (Escuadron escuadron : batallon.getEscuadrones()) {
                for (NaveEnemiga nave : escuadron.getNaves()) {
                    if (nave.colisionar(nave, disparo)) {
                        escuadron.getNaves().remove(nave);
                        jugador.getDisparos().remove(disparo);
                        totalNavesVivas--;
                        break;
                    }
                }
            }
        }

        // Comprobar las colisiones con los disparos enemigos y la nave amiga
        for (Escuadron escuadron : batallon.getEscuadrones()) {
            for (NaveEnemiga nave : escuadron.getNaves()) {
                for (Disparo disparo : nave.getDisparos()) {
                    if (jugador.colisionar(jugador, disparo)) {
                        jugador.setEstaVivo(false);
                    }
                }
            }
        }

        // Comprobar si la nave amiga colisiona con los enemigos
        for (Escuadron escuadron : batallon.getEscuadrones()) {
            for (NaveEnemiga nave : escuadron.getNaves()) {
                if (jugador.colisionar(jugador, nave)) {
                    jugador.setEstaVivo(false);
                }
            }
        }
    }

    private void iniciarEnemigos() {
        // Aquí es donde se crean los enemigos en el batallón
        for (int i = 0; i < 3; i++) { // Tres filas de enemigos
            for (int j = 0; j < 8; j++) { // Ocho enemigos por fila
                enemigos.add(new NaveEnemiga(50 + j * 50, Gdx.graphics.getHeight() - (i + 1) * 50, 30, 30, new Texture("enemy.png")));
            }
        }
    }

    public void dispose() {
        // Limpiar los recursos
        music_playing.dispose();
        vgmdisparo.dispose();
        gameover.dispose();
        gamewin.dispose();
    }
}

package com.example.adrianmontes.miprimerjuego;

import android.graphics.Canvas;

/**
 * Created by adrian.montes on 10/01/2018.
 */

public class GameLoopThread extends Thread {
    private final long FPS = 10000;
    private GameView view;
    private boolean running = false;
    private GameLoopThread gameLoopThread;

    public GameLoopThread(GameView view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        this.running = run;
    }


    @Override
    public void run() {

        long tkisPS = 1000 / FPS;
        long startTime;
        long sleeptime;

        //de esta manera pintamos el cambas todo el rato con el while
        while (running) {
            Canvas c = null;
            //cojo el tiempo del inicio de el hilo
            startTime = System.currentTimeMillis();

            try {
                //aqui pintamos el cambas cada vez que se actualiza con el while y
                //synchornizamos para que nadie pueda acceder mientras de pinta, en el finally lo volvemos a intentar
                //por si ya se accedio a ese recurso
                c = view.getHolder().lockCanvas();

                synchronized (view.getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }

            }
            sleeptime = tkisPS - (System.currentTimeMillis() - startTime);
                try {

                if (sleeptime > 0)
                        sleep(sleeptime);
                else
                    sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

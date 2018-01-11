package com.example.adrianmontes.miprimerjuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by adrian.montes on 10/01/2018.
 */

public class GameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread  gameLoopThread;
    int x=0;
    private int XSpeed=1;
    private Sprite sprite;

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        //ahora en vez de gestionar cada vez que hacemos un cambio llamar al onDraw que es lo que hace el View, con el
        //surfaceView cada vez que hacemos un cambio lo hacemos en surfaceChange
        holder= getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            //en este metodo se ejecuta la primera vez que se cree
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
                Canvas c = holder.lockCanvas(null);
                onDraw(c);
                holder.unlockCanvasAndPost(c);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry=true;
                gameLoopThread.setRunning(false);
                while(retry){
                    try {
                        gameLoopThread.join();
                        retry=false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.bad1);
        //ahora cargo la imagen en el Sprite
        sprite=new Sprite(this,bmp);

    }

//ahora generamos un metodo que tiene la clase View para poder crear el cambas.
    @Override
    protected void onDraw(Canvas canvas) {
        //ponemos el color de fondo
        canvas.drawColor(Color.BLACK);
        //ahora le poasamos la funcion de mover la imagen a la clase Sprite
        sprite.onDraw(canvas);

    }
}

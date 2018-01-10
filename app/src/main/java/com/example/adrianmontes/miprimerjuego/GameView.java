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

    public GameView(Context context) {
        super(context);
        //ahora en vez de gestionar cada vez que hacemos un cambio llamar al onDraw que es lo que hace el View, con el
        //surfaceView cada vez que hacemos un cambio lo hacemos en surfaceChange
        holder= getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas c = holder.lockCanvas(null);
                onDraw(c);
                holder.unlockCanvasAndPost(c);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        bmp= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

    }

//ahora generamos un metodo que tiene la clase View para poder crear el cambas.
    @Override
    protected void onDraw(Canvas canvas) {
        //ponemos el color de fondo
        canvas.drawColor(Color.BLACK);
        //y le indicamos la imagen que queremos colocar
        canvas.drawBitmap(bmp,10,10,null);

        super.onDraw(canvas);
    }
}

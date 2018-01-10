package com.example.adrianmontes.miprimerjuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by adrian.montes on 10/01/2018.
 */

public class GameView extends SurfaceView {
    private Bitmap bmp;


    public GameView(Context context) {
        super(context);
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

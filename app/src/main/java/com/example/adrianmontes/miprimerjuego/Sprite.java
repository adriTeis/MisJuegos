package com.example.adrianmontes.miprimerjuego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by adrian.montes on 10/01/2018.
 */

public class Sprite {
    private static final int BMP_ROWS=4;
    private static final int BMP_COLUMNS=3;
    private int x=0;
    private int y=0;
    private int xSpeed=5;
    private int ySpeed=0;
    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame=0;
    private int width;
    private int height;


    public Sprite(GameView gameView,Bitmap bmp){
        this.gameView= gameView;
        this.bmp = bmp;
        //ahora le indicamos que los muñecos se muevan de manera aleatoria
        Random rnd = new Random();
        //le inidico lo que ocupa cada una de las imagenes en la imagen
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.height=bmp.getHeight()/BMP_ROWS;
        //le indico que la posicion de la x y de la y es aleatoria
        xSpeed = rnd.nextInt(100)-5;
        ySpeed = rnd.nextInt(100)-5;

    }
    private void update() {
        //ahora creamos el movimiento aleatorio en x
        if (x > gameView.getWidth()-width-xSpeed || x+xSpeed<0) {
            xSpeed =-xSpeed;
        }
        x=x+xSpeed;
        //ahora creamos el movimiento aleatorio en y
        if (y > gameView.getWidth()-height-ySpeed || y+ySpeed<0) {
            ySpeed =-ySpeed;
        }
        y=y+ySpeed;

        if (x + xSpeed < 0) {
            xSpeed = 5;
        }
        x = x + xSpeed;
        currentFrame=++currentFrame%BMP_COLUMNS;
    }

                public void onDraw(Canvas Canvas) {
                    update();

                    int srcX=currentFrame*width;
                    int srcY=1*height;
                    //calculo que rectangulo quiero mostrar, Rect es un objeto de android que me ayuda a recortar un rectangulo
                    // en la imagen.
                    Rect src = new Rect(srcX,srcX,srcX+width,srcY+height);
                    Rect dst=new Rect(x,y,x+width,y+height);
                    //src es la imagen que queremos que se muestre de la imagen, y dst es donde queremos que se pinte en el canvas
                    Canvas.drawBitmap(bmp,src,dst, null);
                }


    }


package com.example.pohguan.wallpaper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import java.util.Random;

/**
 * Created by PohGuan on 9/1/2015.
 */
public class SingleBitmapRandomizerDrawer implements BitmapAnimationDrawer {

    /*
     * The bitmap.
     */
    private Bitmap bm;

    /*
     * Left.
     */
    private int left;

    /*
     * Top.
     */
    private int top;

    /*
     * Rotation.
     */
    private float angle;

    /*
     * xScale.
     */
    private float xScale;

    /*
     * yScale.
     */
    private float yScale;

    /*
     * Time manager.
     */
    private TimeManager timeManager;

    /*
     * Constructor.
     */
    public SingleBitmapRandomizerDrawer(Bitmap bm, int left, int top, TimeManager timeManager) {
        this.bm = bm;
        this.left = left - bm.getWidth()/2;
        this.top = top - bm.getHeight()/2;
        this.angle = 0f;
        this.xScale = 1;
        this.yScale = 1;
        this.timeManager = timeManager;
    }


    /*
     * Draw frame.
     */
    public void drawFrame(Canvas canvas) {
        randomize(canvas);
        Matrix matrix = new Matrix();
        matrix.postScale(xScale, yScale);
        matrix.postRotate(angle);
        matrix.postTranslate(left, top);
        canvas.drawBitmap(bm, matrix, null);
    }


    /*
     * Randomize stuff.
     */
    private void randomize(Canvas canvas) {
        Random r = new Random();
        angle = r.nextFloat()*180;
        double offScreenOffsetX =  bm.getWidth()*xScale;
        double offScreenOffsetY =  bm.getHeight()*yScale;

        left = (int) ((r.nextFloat()*(canvas.getWidth()- 2*offScreenOffsetX))+offScreenOffsetX);
        top = (int) ((r.nextFloat()*(canvas.getHeight()- 2*offScreenOffsetY))+offScreenOffsetY);

        setScale();
    }


    /*
     * Set scale of bitmap.
     */
    private void setScale() {
        if (timeManager.isNoon()) {
            xScale = 1;
            yScale = 1;
        } else if (timeManager.isEvening()) {
            xScale = 0.3f;
            yScale = 0.3f;
        } else {
            xScale = 0.6f;
            yScale = 0.6f;
        }

    }
}

package edu.lewisu.cs.example.simplegame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

class Ball {
    private final int RADIUS = 100;
    private final int BALL_COLOR = 0xffff00ff;
    private Paint mPaint;
    private Point mCenter;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    private int mDirection;


    public Ball(int surfaceWidth, int surfaceHeight) {
        mSurfaceWidth = surfaceWidth;
        mSurfaceHeight = surfaceHeight;

        // Set initial position and direction
        mCenter = new Point(RADIUS+10, (surfaceHeight+RADIUS)/2);
        mPaint = new Paint();
        mPaint.setColor(BALL_COLOR);
        mDirection =1;
    }

    public boolean move(double elapsedTime) {
        mCenter.x += (int)elapsedTime * mDirection;
        boolean scored=false;

        if(mCenter.x > mSurfaceWidth - RADIUS){ //right edge
            mCenter.x = mSurfaceWidth - RADIUS;
            mDirection = -1;
        }else if(mCenter.x <=  RADIUS) { //left edge
            mCenter.x =  RADIUS;
            mDirection = 1;
        }
        return scored;
    }

    public void relocate(float x, float y){
        mCenter.x= (int)x;
        mCenter.y= (int)y;
    }

    public boolean insideBall(float x, float y){
        int slop = RADIUS + 40;
        if(x>= mCenter.x-slop && x<= mCenter.x+slop )
            return true;
        else
            return false;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(mCenter.x, mCenter.y, RADIUS, mPaint);
    }
}

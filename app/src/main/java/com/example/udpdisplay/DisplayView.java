package com.example.udpdisplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DisplayView extends View {
    private static final int MAX_SCALE=10;
    private static final int MAX_LENGTH_X=150;//X 轴最长150cm
    private static final int MAX_LENGTH_Y=200;
    private Rect rect_Desk;
    private RectF rect_Tracer;
    private Paint mPaint;
    private float mStepSize=10f;//每个刻度代表的长度，单位cm
    private float mPerCm=6.5f;//每厘米需要多少个像素
    private float mStartX=40f;
    private float mStartY=80f;

    public DisplayView(Context context) {
        super(context);
        //初始化画笔

    }

    public DisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//在画图的时候，图片如果旋转或缩放之后，总是会出现那些华丽的锯齿。给Paint加上抗锯齿标志
        mPaint .setAntiAlias(true);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(20);
        mPaint.setStrokeWidth(1);
        canvas.drawText("单位/CM",mStartX-40,mStartY-30,mPaint);
        canvas.drawText("X轴",MAX_LENGTH_X*mPerCm+mStartX+30,mStartY-20,mPaint);
        canvas.drawText("Y轴",mStartX-35,MAX_LENGTH_Y*mPerCm+mStartY+60,mPaint);
        canvas.drawLine(mStartX,mStartY,MAX_LENGTH_X*mStepSize+mStartX,mStartY,mPaint);
        canvas.drawLine(mStartX,mStartY,mStartX,MAX_LENGTH_Y*mStepSize+mStartY,mPaint);
        //画表格X
        for (int i = 1; i <= MAX_LENGTH_X/mStepSize; i++) {
            //画横线     Max_Y = 580   Max_X = 1000  startX = 20
            float startX=mPerCm*mStepSize*i+mStartX;
            canvas.drawText(i*(int)mStepSize+"",startX-10,mStartY-5,mPaint);
            canvas.drawLine(startX,mStartY,startX,MAX_LENGTH_Y*mPerCm+mStartY,mPaint);
        }
        //画表格Y
        for (int i = 1; i <= MAX_LENGTH_Y/mStepSize; i++) {
            //画横线     Max_Y = 580   Max_X = 1000  startX = 20
            float startY=mPerCm*mStepSize*i+mStartY;
            canvas.drawText(i*(int)mStepSize+"",mStartX-40,startY,mPaint);
            canvas.drawLine(mStartX,startY,MAX_LENGTH_X*mPerCm+mStartX,startY,mPaint);
        }

//        //画刻度
//        for (int i = 0; i <= LINE_NUM; i++) {
//            //画横线     Max_Y = 580   Max_X = 1000  startX = 20
//            //               四个参数   显示内容  x坐标  y坐标   画笔
//            //  canvas.drawText(txtLast,1000,600,paintAxes2);
//            canvas.drawText(xnums[i],(Max_X-startX)/LINE_NUM*i,600,paintAxes2);
//            canvas.drawText(ynums[10-i],0,Max_Y/LINE_NUM*i,paintAxes2);
//
//
//        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


}

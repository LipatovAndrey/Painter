package ru.sberbank.user7.painter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by user7 on 22.04.2017.
 */

public class PaintinfView extends View {
    private float lastx, lasty;
    private SparseArray<PointF> lastPoints = new SparseArray<>(10);

    private Bitmap cachBitmap;
    private Paint linepaint;
    private Canvas bitmapCanvas;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){

            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_POINTER_DOWN:
                int pointerID = event.getPointerId(event.getActionIndex());
                lastPoints.put(pointerID, new PointF(event.getX(event.getActionIndex()),event.getY(event.getActionIndex())));
                lastx = event.getX();
                lasty = event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                for(int i=0 ; i < event.getPointerCount(); i++) {
                    PointF pf = lastPoints.get(event.getPointerId(i));
                    float x = event.getX(i);
                    float y = event.getY(i);
                    if(i%2==0){
                    bitmapCanvas.drawLine(pf.x, pf.y, x, y, linepaint);}else{
                        Paint p = new Paint(Color.CYAN);
                        p.setStrokeWidth(getResources().getDimension(R.dimen.default_width));
                        bitmapCanvas.drawLine(pf.x, pf.y, x, y, p);}
                    pf.set(x,y);

                }
                invalidate();
                return true;
            case MotionEvent.ACTION_POINTER_UP:return true;
            case MotionEvent.ACTION_UP: return true;
            case MotionEvent.ACTION_CANCEL:return true;

        }
        return super.onTouchEvent(event);
    }
    private void init(){
        linepaint = new Paint();
        linepaint.setAntiAlias(true);
        linepaint.setStrokeCap(Paint.Cap.BUTT);
        linepaint.setStrokeJoin(Paint.Join.ROUND);
        linepaint.setStrokeWidth(getResources().getDimension(R.dimen.default_width));
        linepaint.setColor(getResources().getColor(R.color.colorAccent));
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(cachBitmap != null){
            cachBitmap.recycle();
        }
        if (w != 0 && h != 0){
            cachBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmapCanvas = new Canvas(cachBitmap);
        }

    }
    public void clear(){
        bitmapCanvas.drawColor(Color.WHITE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(isInEditMode()){
            canvas.drawRect(0.1f*getWidth(),0.1f*getHeight(), 0.9f*getWidth(), 0.9f, linepaint);
        }
        canvas.drawBitmap(cachBitmap, 0,0, null);

    }

    public PaintinfView(Context context) {

        super(context);
        init();
    }

    public PaintinfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintinfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PaintinfView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
}

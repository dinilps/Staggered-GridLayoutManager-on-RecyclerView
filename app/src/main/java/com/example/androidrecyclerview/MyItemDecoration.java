package com.example.androidrecyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyItemDecoration extends RecyclerView.ItemDecoration{

    private Paint paintBlue, paintRed, paintBorder;
    private int offset;

    Bitmap bitmap;
    int bitmap_w, bitmap_h;
    Rect rectSrc;

    public MyItemDecoration(Context c){
        offset = 10;
        paintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.STROKE);
        paintBlue.setStrokeWidth(3);

        paintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRed.setColor(Color.RED);
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(1);

        paintBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBorder.setColor(Color.GREEN);
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setStrokeWidth(10);

        bitmap = BitmapFactory.decodeResource(
                c.getResources(),
                android.R.drawable.ic_menu_info_details);
        bitmap_w = bitmap.getWidth();
        bitmap_h = bitmap.getHeight();
        rectSrc = new Rect(0, 0, bitmap_w, bitmap_h);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(offset, offset, offset, offset);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        c.drawRect(
                0,
                0,
                c.getWidth(),
                c.getHeight(),
                paintBorder);

        for(int i=0; i<parent.getChildCount(); i++){
            final View child = parent.getChildAt(i);
            c.drawRect(
                    layoutManager.getDecoratedLeft(child),
                    layoutManager.getDecoratedTop(child),
                    layoutManager.getDecoratedRight(child),
                    layoutManager.getDecoratedBottom(child),
                    paintBlue);
            c.drawRect(
                    layoutManager.getDecoratedLeft(child) + offset,
                    layoutManager.getDecoratedTop(child) + offset,
                    layoutManager.getDecoratedRight(child) - offset,
                    layoutManager.getDecoratedBottom(child) - offset,
                    paintRed);

        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        c.drawBitmap(bitmap,
                rectSrc,
                new Rect(
                        c.getWidth()-(2*bitmap_w),
                        c.getHeight()-(2*bitmap_h),
                        c.getWidth(),
                        c.getHeight()),
                null);
    }
}

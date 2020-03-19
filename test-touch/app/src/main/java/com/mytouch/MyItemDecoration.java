package com.mytouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    Context context;
    int mTotalItems;
    int mSpanCount;
    Paint paint;
    Bitmap bitmap;
    Bitmap bitmap1;

    public MyItemDecoration(Context context){
        this.context = context;
        paint = new Paint();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if(0 == mTotalItems){
            mTotalItems = parent.getAdapter().getItemCount();
        }
        if(0 == mSpanCount){
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if(layoutManager instanceof GridLayoutManager){
                GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
                mSpanCount = gridLayoutManager.getSpanCount();
            }else{
                mSpanCount = 1;
            }
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for(int i=0; i<parent.getChildCount(); i++){
            drawVertical(c,parent.getChildAt(i));
        }
    }

    private void drawVertical(Canvas canvas, View childView){
        int position = ((RecyclerView.LayoutParams)childView.getLayoutParams()).getViewLayoutPosition();
        if(isLastRow(position,mTotalItems,mSpanCount)){
            return;
        }
        Bitmap bitmap;
        if(position % 2 == 0){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.item);
        }else{
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.item1);
        }
        canvas.drawBitmap(bitmap,new Matrix(),paint);
    }

    private boolean isLastRow(int currentItemPos,int totalItems,int spanCount){
        boolean result = false;
        int rowCount = 0;
        if(0 == totalItems%spanCount){
            rowCount = totalItems/spanCount;
        }else{
            rowCount = totalItems/spanCount+1;
        }
        if((currentItemPos + 1) > (rowCount-1)*spanCount){
            result = true;
        }
        return result;
    }


}

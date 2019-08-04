package me.mortuza.fragmentanimations;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MultipleTouch implements RecyclerView.OnItemTouchListener {
    private int PREVIOUS_X = 0;
    private int PREVIOUS_Y = 0;

    private int PREVIOUS_HEIGHT = 0;
    private int PREVIOUS_WIDTH = 0;
    int OFFSET = 100;
    private int LAST_POSITION = -1;

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int width = recyclerView.getMeasuredWidth() / 7;
        int height = recyclerView.getMeasuredHeight() / 7;
        int mainHeight = recyclerView.getMeasuredHeight();
        Log.d("MultipleTouch", "onTouchEvent x=: " + motionEvent.getX() + "y=" + motionEvent.getY());
        Log.d("MultipleTouch", "onTouchEvent xx=: " + width + "yy=" + height);
        if (action == MotionEvent.ACTION_MOVE && mainHeight > motionEvent.getY()) {//|| action == MotionEvent.ACTION_POINTER_UP || action == MotionEvent.ACTION_CANCEL) {

            View v = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            RecyclerView.ViewHolder holder = null;
            try {
                holder = recyclerView.getChildViewHolder(v);
                PREVIOUS_WIDTH = holder.itemView.getWidth();
                PREVIOUS_HEIGHT = holder.itemView.getHeight();
                if (LAST_POSITION != holder.getAdapterPosition()) {
                    Log.d("MultipleTouch", "onTouchEvent:DDR " + holder.getAdapterPosition());
                    ViewGroup itemView = (ViewGroup) holder.itemView;
                    if (!((TextView) (itemView.getChildAt(0))).getText().toString().isEmpty()) {
                        holder.itemView.setBackgroundColor(Color.GREEN);
                    }
                }
                LAST_POSITION = holder.getAdapterPosition();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PREVIOUS_X = (int) motionEvent.getX();
            PREVIOUS_Y = (int) motionEvent.getY();
        } else if (action == MotionEvent.ACTION_POINTER_UP || action == MotionEvent.ACTION_CANCEL) {
            PREVIOUS_HEIGHT = 0;
            PREVIOUS_WIDTH = 0;
            LAST_POSITION = -1;
        }

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}

package mikkoluhtasaari.gwentdeckbuilder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Click listener class for recyclerview.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 07 May 2017
 * @since 1.0
 */

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    /**
     * Detects gestures
     */
    private GestureDetector gestureDetector;

    /**
     * Detects clicks
     */
    private ClickListener clickListener;

    /**
     * Basic constructor
     *
     * @param context Context for the touch listener
     * @param recyclerView Recycler view which is to be monitored
     * @param clickListener Click listener to be bound
     */
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

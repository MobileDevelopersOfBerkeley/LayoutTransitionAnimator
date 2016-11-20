package com.platform.mdb.layouttransitionanimatorlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cody on 11/20/16.
 */

public class LayoutAnimator {
    private List<View> viewList  = new ArrayList<View>();
    private String slideDirection;
    private int delayOffset = 200;
    private int animationDuration = 1000; //default value
    private int translationX;
    private int translationY;


    public LayoutAnimator(ViewGroup viewLayout, String slideDirection){
        this.slideDirection = slideDirection;
        final int childcount = viewLayout.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = viewLayout.getChildAt(i); //Gets each view of a LinearLayout
            viewList.add(v);
        }
        setInitialStates();
    }
    //Sets the transparency for each view as clear and offsets for the slide
    private void setInitialStates(){
        for(View v : viewList){
            v.setAlpha(0f);
            switch (slideDirection) {
                case "LEFT":
                    v.setTranslationX(-100f);
                    translationX = 100;
                    translationY = 0;
                    break;
                case "TOP":
                    v.setTranslationY(-100f);
                    translationX = 0;
                    translationY = -100;
                    break;
                case "RIGHT":
                    v.setTranslationX(100f);
                    translationX = -100;
                    translationY = 0;
                    break;
                case "BOTTOM": // Gravity.BOTTOM
                    v.setTranslationY(100f);
                    translationX = 0;
                    translationY = -100;
            }
        }
    }
    public void runAnimations(){
        int delayTime = 0;
        for(View v : viewList){
            System.out.println(v.getWidth());
            v.animate().alpha(1f)
                    .translationX(translationX)
                    .translationY(translationY)
                    .setDuration(animationDuration)
                    .setStartDelay(delayTime)
                    .setInterpolator(AnimUtils.getLinearOutSlowInInterpolator(
                            v.getContext()));
            delayTime +=delayOffset;
        }
    }
    public void setAnimationDuration(int duration){
        this.animationDuration = duration;
    }
    public void setDelayOffset(int duration){
        this.delayOffset = duration;
    }

}

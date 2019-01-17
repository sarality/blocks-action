package com.sarality.action;

import android.view.MotionEvent;
import android.view.View;

import java.util.Set;

/**
 * Interface for classes that provide the data for the swipe action.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SwipeActionContext extends ActionContext {

  private final Set<SwipeType> swipeTypes;
  private final MotionEvent downEvent;
  private final MotionEvent upEvent;

  SwipeActionContext(View view, Set<SwipeType> swipeTypes, MotionEvent downEvent, MotionEvent upEvent) {
    super(view);
    this.swipeTypes = swipeTypes;
    this.downEvent = downEvent;
    this.upEvent = upEvent;
  }

  public Set<SwipeType> getSwipeTypes() {
    return swipeTypes;
  }

  public MotionEvent getDownEvent() {
    return downEvent;
  }

  public MotionEvent getUpEvent() {
    return upEvent;
  }
}

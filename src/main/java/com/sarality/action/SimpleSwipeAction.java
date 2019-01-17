package com.sarality.action;

import java.util.Set;

/**
 * Base implementation of Swipe Actions that does nothing.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SimpleSwipeAction implements ViewAction {

  @Override
  public boolean perform(ActionContext actionContext) {
    boolean status = false;
    if (actionContext instanceof SwipeActionContext) {
      SwipeActionContext context = (SwipeActionContext) actionContext;
      Set<SwipeType> swipeTypes = context.getSwipeTypes();
      if (swipeTypes != null) {
        if (swipeTypes.contains(SwipeType.LEFT)) {
          status = onLeftSwipe(context);
        }
        if (swipeTypes.contains(SwipeType.RIGHT)) {
          status = status || onRightSwipe(context);
        }
        if (swipeTypes.contains(SwipeType.UP)) {
          status = status || onUpSwipe(context);
        }
        if (swipeTypes.contains(SwipeType.DOWN)) {
          status = status || onDownSwipe(context);
        }
      }
    }
    return status;
  }

  public boolean onLeftSwipe(SwipeActionContext context) {
    return false;
  }

  public boolean onRightSwipe(SwipeActionContext context) {
    return false;
  }

  public boolean onUpSwipe(SwipeActionContext context) {
    return false;
  }

  public boolean onDownSwipe(SwipeActionContext context) {
    return false;
  }
}

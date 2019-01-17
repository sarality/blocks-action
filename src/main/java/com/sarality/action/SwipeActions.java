package com.sarality.action;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.sarality.util.log.Resources;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Registers Swipe Actions on a View or children of a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SwipeActions implements ActionInitializer {

  private static final float MIN_SWIPE_DISTANCE = 150;

  private Activity activity;
  private Map<Integer, Config> registry = new HashMap<>();

  public SwipeActions(Activity activity) {
    this.activity = activity;
  }

  /**
   * Register the Action to be triggered on a Swipe.
   *
   * @param viewId Id of the View that will trigger the action when user swipes left.
   * @param action Action to be performed
   * @return Swipe Actions
   */
  public SwipeActions register(int viewId, ViewAction action, SwipeType... swipeTypes) {
    if (swipeTypes == null) {
      throw new IllegalArgumentException("Must specific Swipe Type for the Action");
    }
    Set<SwipeType> swipeTypeSet = new HashSet<>(Arrays.asList(swipeTypes));
    if (registry.containsKey(viewId)) {
      Config config = registry.get(viewId);
      for (SwipeType swipeType : swipeTypeSet) {
        if (config.swipeTypes.contains(swipeType)) {
          throw new IllegalStateException("Cannot register multiple " + swipeType + " Swipe actions for" +
              " the same View with Id  " + Resources.name(activity, viewId));
        }
      }
      config.swipeTypes.addAll(swipeTypeSet);
    } else {
      registry.put(viewId, new Config(action, swipeTypeSet));
    }
    return this;
  }

  @Override
  public void init(View rootView) {
    init(activity, rootView);
  }

  @Override
  public void init() {
    init(activity, null);
  }

  private void init(Activity activity, View rootView) {
    for (Integer viewId : registry.keySet()) {
      View view;
      if (rootView != null) {
        view = rootView.findViewById(viewId);
      } else {
        view = activity.findViewById(viewId);
      }
      if (view == null) {
        throw new IllegalStateException("Cannot find View with id " + Resources.name(activity, viewId));
      }
      Config config = registry.get(viewId);
      if (config != null && config.action != null) {
        final GestureDetector detector = new GestureDetector(activity.getApplicationContext(),
            new Performer(view, config.action, config.swipeTypes));

        view.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
              view.performClick();
            }
            return detector.onTouchEvent(motionEvent);
          }
        });
      }
    }
  }

  private class Config {
    private final ViewAction action;
    private final Set<SwipeType> swipeTypes;

    public Config(ViewAction action, Set<SwipeType> swipeTypes) {
      this.action = action;
      this.swipeTypes = swipeTypes;
    }
  }

  /**
   * Listens to the Click events and triggers the action to be performed.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  private class Performer extends GestureDetector.SimpleOnGestureListener {
    private final View view;
    private final ViewAction action;
    private final Set<SwipeType> swipeTypes;

    private Performer(View view, ViewAction action, Set<SwipeType> swipeTypes) {
      this.view = view;
      this.action = action;
      this.swipeTypes = swipeTypes;
    }

    @Override
    public boolean onDown(MotionEvent event) {
      return true;
    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent upEvent, float velocityX, float velocityY) {
      float x1 = downEvent.getX();
      float y1 = downEvent.getY();
      float x2 = upEvent.getX();
      float y2 = upEvent.getY();
      float diffX = x2 - x1;
      float diffY = y2 - y1;
      Set<SwipeType> swipeTypeSet = new HashSet<>();
      if (diffX > MIN_SWIPE_DISTANCE) {
        if (diffX < 0 && swipeTypes.contains(SwipeType.LEFT)) {
          swipeTypeSet.add(SwipeType.LEFT);
        } else if (diffX > 0 && swipeTypes.contains(SwipeType.RIGHT)) {
          swipeTypeSet.add(SwipeType.RIGHT);
        }
      }
      if (diffY > MIN_SWIPE_DISTANCE) {
        if (diffY < 0 && swipeTypes.contains(SwipeType.UP)) {
          swipeTypeSet.add(SwipeType.UP);
        } else if (diffY > 0 && swipeTypes.contains(SwipeType.DOWN)) {
          swipeTypeSet.add(SwipeType.DOWN);
        }
      }

      if (!swipeTypeSet.isEmpty()) {
        action.perform(new SwipeActionContext(view, swipeTypeSet, downEvent, upEvent));
      }
      return true;
    }
  }
}

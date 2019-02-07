package com.sarality.action;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.sarality.util.log.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registers Actions that are performed when the Text for a View Changes.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TextChangedActions implements ActionInitializer {

  private Activity activity;
  private Map<Integer, ViewAction> registry = new HashMap<>();
  private Map<Integer, List<Performer>> performerListRegistry = new HashMap<>();

  public TextChangedActions(Activity activity) {
    this.activity = activity;
  }

  public TextChangedActions register(int viewId, ViewAction action) {
    if (registry.containsKey(viewId)) {
      throw new IllegalStateException("Cannot register multiple actions for the same View with Id  " +
          Resources.name(activity, viewId));
    }
    registry.put(viewId, action);
    return this;
  }

  @Override
  public void init() {
    for (Integer viewId : registry.keySet()) {
      TextView view = activity.findViewById(viewId);
      if (view == null) {
        throw new IllegalStateException("Cannot find View with id " + Resources.name(activity, viewId));
      }
      ViewAction action = registry.get(viewId);
      if (action != null) {
        Performer performer = new Performer(view, action);
        if (!performerListRegistry.containsKey(viewId)) {
          performerListRegistry.put(viewId, new ArrayList<Performer>());
        }
        performerListRegistry.get(viewId).add(performer);
        view.addTextChangedListener(performer);
      } else {
        List<Performer> performerList = performerListRegistry.get(viewId);
        if (performerList != null) {
          for (Performer performer : performerList) {
            view.removeTextChangedListener(performer);
          }
        }
      }
    }
  }

  @Override
  public void init(View parentView) {
    for (Integer viewId : registry.keySet()) {
      TextView view = parentView.findViewById(viewId);
      ViewAction action = registry.get(viewId);
      if (action != null) {
        Performer performer = new Performer(view, action);
        if (!performerListRegistry.containsKey(viewId)) {
          performerListRegistry.put(viewId, new ArrayList<Performer>());
        }
        performerListRegistry.get(viewId).add(performer);
        view.addTextChangedListener(performer);
      } else {
        List<Performer> performerList = performerListRegistry.get(viewId);
        if (performerList != null) {
          for (Performer performer : performerList) {
            view.removeTextChangedListener(performer);
          }
        }
      }
    }
  }

  /**
   * Listens to the Item selection events and triggers the action to be performed.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  private class Performer implements TextWatcher {

    private final View view;
    private final ViewAction action;

    private Performer(View view, ViewAction action) {
      this.view = view;
      this.action = action;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
      action.perform(new ActionContext(view));
    }
  }
}

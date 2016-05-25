package com.dothat.action;

import android.app.Activity;
import android.view.View;

/**
 * Interface for all events that trigger an Action.
 * <p/>
 * The class is used to simplify the action configuration and setup appropriate listeners
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface TriggerEventDefinition {

  Activity getActivity();

  View getView();

  void setupAction(ViewAction action);
}

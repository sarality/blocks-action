package com.sarality.action;

import android.view.View;

/**
 * Interface for all classes that initialize Actions on Views
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ActionInitializer {

  /**
   * Initialize Actions and Listeners in the context of the Activity.
   */
  void init();

  /**
   * Initialize Action and Listeners in the context of the given View.
   *
   * @param view Parent view to lookup children on to setup actions.
   */
  void init(View view);
}

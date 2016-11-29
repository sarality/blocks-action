package com.sarality.action.intent;

import android.view.View;

/**
 * Interface for classes that provides the View that the Action is to be performed on
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ActionViewProvider {

  View getView(View triggerView);
}

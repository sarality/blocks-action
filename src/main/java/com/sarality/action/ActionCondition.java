package com.sarality.action;

import android.view.View;

/**
 * Interface that evaluates condition that must be true for an Action to performed.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ActionCondition {

  boolean isTrue(View view);
}

package com.sarality.action;

import android.content.Intent;

/**
 * Defines the different types of navigation behaviours that are available to an application at the
 * time of starting an activity
 *
 * @author satya@ (Satya Puniani)
 */
public enum NavigationStyle {
  STANDARD(0),
  NO_HISTORY(Intent.FLAG_ACTIVITY_NO_HISTORY),
  KEEP_ON_TOP(Intent.FLAG_ACTIVITY_CLEAR_TOP);

  private final int intentFlags;

  NavigationStyle(int intentFlags){
    this.intentFlags=intentFlags;
  }

  public int getIntentFlags() {
    return intentFlags;
  }
}

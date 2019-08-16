package com.sarality.action.intent;

import android.text.TextUtils;
import android.view.View;

/**
 * Extracts a Double value from a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DoubleViewValueExtractor implements ViewValueExtractor<Double> {

  private final ViewValueExtractor<String> viewValueExtractor;

  public DoubleViewValueExtractor(ViewValueExtractor<String> viewValueExtractor) {
    this.viewValueExtractor = viewValueExtractor;
  }

  @Override
  public Double extract(View view) {
    String value = viewValueExtractor.extract(view);
    if (!TextUtils.isEmpty(value)) {
      return Double.valueOf(value);
    }
    return null;
  }
}

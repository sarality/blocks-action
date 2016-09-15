package com.sarality.action.intent;

import android.view.View;

/**
 * Extracts a value from a View so that it can be passed to an Intent
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewValueExtractor<T> {

  T extract(View view);
}

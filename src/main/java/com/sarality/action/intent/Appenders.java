package com.sarality.action.intent;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Utility class to create an instance of IntentAppender
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class Appenders {

  public static IntentAppender longValue(String extraName, ViewValueExtractor<Long> extractor) {
    return new LongValueAppender(extraName, extractor);
  }

  public static <T extends Serializable> IntentAppender serializableListValue(String extraName,
      ViewValueExtractor<ArrayList<T>> extractor) {
    return new SerializableListValueAppender<T>(extraName, extractor);
  }
}

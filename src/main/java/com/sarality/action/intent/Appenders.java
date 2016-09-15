package com.sarality.action.intent;

/**
 * Utility class to create an instance of IntentAppender
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class Appenders {

  public static IntentAppender longValue(String extraName, ViewValueExtractor<Long> extractor) {
    return new LongValueAppender(extraName, extractor);
  }
}

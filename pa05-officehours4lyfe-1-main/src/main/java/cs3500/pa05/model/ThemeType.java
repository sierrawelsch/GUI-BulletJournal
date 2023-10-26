package cs3500.pa05.model;

/**
 * represents the options for a theme
 */

public enum ThemeType {
  DEFAULT, MODERN, RETRO, PASTEL, CUSTOM;

  /**
   * given a string returns the corresponding themeType enum
   *
   * @param theme a string value of the theme
   *
   * @return a ThemeType enum correlating to the string value
   */
  public static ThemeType stringToEnum(String theme) {
    String themeUpperCase = theme.toUpperCase();
    if (themeUpperCase.equals("DEFAULT")) {
      return ThemeType.DEFAULT;
    } else if (themeUpperCase.equals("MODERN")) {
      return ThemeType.MODERN;
    } else if (themeUpperCase.equals("RETRO")) {
      return ThemeType.RETRO;
    } else if (themeUpperCase.equals("PASTEL")) {
      return ThemeType.PASTEL;
    } else {
      return ThemeType.CUSTOM;
    }
  }
}

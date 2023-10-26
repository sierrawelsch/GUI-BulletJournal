package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ThemeType enum class
 */
class ThemeTypeTest {

  /**
   * tests that given a theme as a String the corresponding enum value will be ouptted
   */
  @Test
  void stringToEnum() {
    assertEquals(ThemeType.stringToEnum("DEFAULT"), ThemeType.DEFAULT);
    assertEquals(ThemeType.stringToEnum("CUSTOM"), ThemeType.CUSTOM);
    assertEquals(ThemeType.stringToEnum("RETRO"), ThemeType.RETRO);
    assertEquals(ThemeType.stringToEnum("MODERN"), ThemeType.MODERN);
    assertEquals(ThemeType.stringToEnum("PASTEL"), ThemeType.PASTEL);
  }
}
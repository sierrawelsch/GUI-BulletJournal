package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for the DayName enum class
 */
class DayNameTest {

  /**
   * tests that a given string is properly converted to its associated enum
   * definition
   */
  @Test
  void stringToEnum() {
    assertEquals(DayName.stringToEnum("MONDAY"), DayName.MONDAY);
    assertEquals(DayName.stringToEnum("TUESDAY"), DayName.TUESDAY);
    assertEquals(DayName.stringToEnum("WEDNESDAY"), DayName.WEDNESDAY);
    assertEquals(DayName.stringToEnum("THURSDAY"), DayName.THURSDAY);
    assertEquals(DayName.stringToEnum("FRIDAY"), DayName.FRIDAY);
    assertEquals(DayName.stringToEnum("SATURDAY"), DayName.SATURDAY);
    assertEquals(DayName.stringToEnum("SUNDAY"), DayName.SUNDAY);
  }

  /**
   * tests that the calIndex, which represents the column of each day on the
   * grid pane, of each DayName is properly stored
   */
  @Test
  void getCalIndex() {
    assertEquals(DayName.MONDAY.getCalIndex(), 1);
    assertEquals(DayName.TUESDAY.getCalIndex(), 2);
    assertEquals(DayName.WEDNESDAY.getCalIndex(), 3);
    assertEquals(DayName.THURSDAY.getCalIndex(), 4);
    assertEquals(DayName.FRIDAY.getCalIndex(), 5);
    assertEquals(DayName.SATURDAY.getCalIndex(), 6);
    assertEquals(DayName.SUNDAY.getCalIndex(), 0);
  }
}
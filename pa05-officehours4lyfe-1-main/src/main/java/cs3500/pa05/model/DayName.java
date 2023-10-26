package cs3500.pa05.model;

/**
 * represents the names of a day
 */

public enum DayName {
  MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(0);

  private int calenderIndex;

  /**
   * @param calenderIndex represents the index of the column on the journal
   */
  DayName(int calenderIndex) {
    this.calenderIndex = calenderIndex;
  }

  /**
   * converts string value of a day to an enumeration
   *
   * @param day the value to be converted
   *
   * @return the enumeration value
   */

  public static DayName stringToEnum(String day) {
    String dayUpperCase = day.toUpperCase();
    if (dayUpperCase.equals("MONDAY")) {
      return DayName.MONDAY;
    } else if (dayUpperCase.equals("TUESDAY")) {
      return DayName.TUESDAY;
    } else if (dayUpperCase.equals("WEDNESDAY")) {
      return DayName.WEDNESDAY;
    } else if (dayUpperCase.equals("THURSDAY")) {
      return DayName.THURSDAY;
    } else if (dayUpperCase.equals("FRIDAY")) {
      return DayName.FRIDAY;
    } else if (dayUpperCase.equals("SATURDAY")) {
      return DayName.SATURDAY;
    } else {
      return DayName.SUNDAY;
    }
  }

  /**
   * retrieves the calendar index of a DayName
   *
   * @return the calendar index of a DayName
   */

  public int getCalIndex() {
    return calenderIndex;
  }
}

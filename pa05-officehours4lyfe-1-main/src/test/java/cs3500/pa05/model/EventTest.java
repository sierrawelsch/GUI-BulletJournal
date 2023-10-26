package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for an Event object
 */
class EventTest {
  Event event;
  EventJson json;

  /**
   * initializes an Event object and an EventJson object for testing
   */
  @BeforeEach
  void setup() {
    event = new Event("Do Work", "For OOD", DayName.MONDAY, "6:00", 50, new Coord(1, 1));
    json = new EventJson("Do Work", "For OOD", "MONDAY", "6:00", 50, new CoordJson(1, 1));
  }

  /**
   * tests that the getDayOfWeek method will return the associated day the event is on as an enum
   */
  @Test
  void getDayOfWeek() {
    assertEquals(event.getDayOfWeek(), DayName.MONDAY);
  }

  /**
   * tests that the getDuration method will return the associated int value stored as duration
   */
  @Test
  void getDuration() {
    assertEquals(event.getDuration(), 50);
  }


  /**
   * tests that the getStartTime method will return the associated String value stored as startTime
   */
  @Test
  void getStartTime() {
    assertEquals(event.getStartTime(), "6:00");
  }

  /**
   * tests that an Event can properly be converted to a EventJson object with the same contents
   */
  @Test
  void eventToJson() {
    assertEquals(event.eventToJson(), json);
  }

  /**
   * tests that when you pass in values that represent the fields of an Event object, the fields
   * of Event update accordingly
   */
  @Test
  void setEvent() {
    event.setEvent("Don't do Work", "For MDM", DayName.FRIDAY, "3:00", 4, new Coord(1, 0));
    assertEquals(event.getName(), "Don't do Work");
    assertEquals(event.getDescription(), "For MDM");
    assertEquals(event.getDayOfWeek(), DayName.FRIDAY);
    assertEquals(event.getStartTime(), "3:00");
    assertEquals(event.getDuration(), 4);
    assertEquals(event.getLocation().getRow(), 1);
    assertEquals(event.getLocation().getCol(), 0);
  }

}
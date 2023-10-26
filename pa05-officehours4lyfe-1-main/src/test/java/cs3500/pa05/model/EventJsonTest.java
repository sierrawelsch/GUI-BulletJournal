package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for an EventJson object
 */
class EventJsonTest {
  EventJson json;
  Event event;


  /**
   * initializes an EventJson object and a Event object for testing
   */
  @BeforeEach
  void setup() {
    json = new EventJson("School", "Do work", "TUESDAY", "4:00", 50, new CoordJson(3, 1));
    event = new Event("School", "Do work", DayName.TUESDAY, "4:00", 50, new Coord(3, 1));
  }

  /**
   * tests that an eventJson is properly converted to an Event object with the same contents
   */
  @Test
  void jsonToEvent() {
    Event newEvent = json.jsonToEvent();
    assertEquals(newEvent.getName(), event.getName());
    assertEquals(newEvent.getDayOfWeek(), event.getDayOfWeek());
    assertEquals(newEvent.getDescription(), event.getDescription());
    assertEquals(newEvent.getStartTime(), event.getStartTime());
    assertEquals(newEvent.getDuration(), event.getDuration());
    assertEquals(newEvent.getLocation().getRow(), event.getLocation().getRow());
    assertEquals(newEvent.getLocation().getCol(), event.getLocation().getCol());
  }
}
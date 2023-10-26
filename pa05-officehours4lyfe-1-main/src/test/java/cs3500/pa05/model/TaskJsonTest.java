package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the TaskJson class
 */
class TaskJsonTest {
  TaskJson json;

  /**
   * initializes a TaskJson object to use for testing
   */
  @BeforeEach
  void setup() {
    json = new TaskJson("Talk", "Talk to friends", "MONDAY", false, new CoordJson(3, 2));
  }

  /**
   * tests that a TaskJson can properly be converted to a Task object with the same contents
   */
  @Test
  void jsonToTask() {
    Task convertedTask = json.jsonToTask();
    assertEquals(convertedTask.getName(), "Talk");
    assertEquals(convertedTask.getDescription(), "Talk to friends");
    assertEquals(convertedTask.getDayOfWeek(), DayName.MONDAY);
    assertEquals(convertedTask.getComplete(), false);
    assertEquals(convertedTask.getLocation().getRow(), 3);
    assertEquals(convertedTask.getLocation().getCol(), 2);
  }
}
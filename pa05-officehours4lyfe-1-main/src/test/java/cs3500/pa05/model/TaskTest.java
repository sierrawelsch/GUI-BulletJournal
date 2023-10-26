package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests of the Task class
 */
class TaskTest {

  Task task;
  TaskJson json;

  /**
   * initializes a Task object and a TaskJson object to use for testing
   */
  @BeforeEach
  void setup() {
    task = new Task("Gym", "Go to the gym", DayName.FRIDAY, true, new Coord(3, 5));
    json = new TaskJson("Gym", "Go to the gym", "FRIDAY", true, new CoordJson(3, 5));
  }


  /**
   * tests that the getComplete method returns the field complete associated with a Task object
   */
  @Test
  void getComplete() {
    assertEquals(task.getComplete(), true);
  }

  /**
   * tests that given a new boolean (complete) value, the complete field
   * of a Task object will be set to the given value
   */
  @Test
  void setComplete() {
    task.setComplete(false);

    assertEquals(task.getComplete(), false);
  }

  /**
   * tests that the getComplete method returns the field dayOfWeek associated with a Task object
   */
  @Test
  void getDayOfWeek() {
    assertEquals(task.getDayOfWeek(), DayName.FRIDAY);
  }

  /**
   * tests that a Task object can properly be converted to a TaskJson object with the same contents
   */
  @Test
  void taskToJson() {
    assertEquals(task.taskToJson(), json);
  }

  /**
   * tests that given new values that correlate to the fields of a Task
   * the fields of a Task object will be set to the given values
   */
  @Test
  void setTask() {
    task.setTask("HW", "do HW", DayName.MONDAY, false, new Coord(5, 4));
    assertEquals(task.getName(), "HW");
    assertEquals(task.getDescription(), "do HW");
    assertEquals(task.getDayOfWeek(), DayName.MONDAY);
    assertEquals(task.getComplete(), false);
    assertEquals(task.getLocation().getRow(), 5);
    assertEquals(task.getLocation().getCol(), 4);
  }

}
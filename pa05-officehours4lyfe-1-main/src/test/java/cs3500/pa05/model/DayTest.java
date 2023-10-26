package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Day class
 */
class DayTest {
  Day monday;
  Task mondayTask;
  Task mondayTask2;
  Event mondayEvent;
  Event mondayEvent2;


  /**
   * initializes a Day object along with Task objects and a List of Task options
   * that will be added to a day for testing
   */
  @BeforeEach
  void setup() {
    monday = new Day(DayName.MONDAY);
    mondayTask = new Task("School", "Go to school", DayName.MONDAY, true, new Coord(0, 1));
    monday.addTask(mondayTask);
    mondayTask2 = new Task("Lunch", "Eat food", DayName.MONDAY, false, new Coord(2, 1));
    mondayEvent =
        new Event("Assembly Day", "At School", DayName.MONDAY, "6:00", 30, new Coord(1, 1));
    monday.addEvent(mondayEvent);
    mondayEvent2 =
        new Event("Dance", "At My Dance School", DayName.MONDAY, "7:00", 30, new Coord(3, 1));
  }

  /**
   * tests that adding a task to a day will be stored in the day's list of tasks
   */
  @Test
  void addTask() {
    monday.addTask(mondayTask2);
    assertEquals(monday.getTasks().get(1), mondayTask2);
  }

  /**
   * tests that adding an event to a day will be stored in the day's list of events
   */
  @Test
  void addEvent() {
    monday.addEvent(mondayEvent2);
    assertEquals(monday.getEvents().get(1), mondayEvent2);
  }

  /**
   * tests that removing a task to a day will be removed in the day's list of tasks
   */
  @Test
  void removeTask() {
    monday.addTask(mondayTask2);
    assertEquals(monday.getTasks().size(), 2);
    monday.removeTask(mondayTask);
    assertEquals(monday.getTasks().size(), 1);
    assertEquals(monday.getTasks().get(0), mondayTask2);
  }

  /**
   * tests that removing an event to a day will be removed in the day's list of events
   */
  @Test
  void removeEvent() {
    monday.addEvent(mondayEvent2);
    assertEquals(monday.getEvents().size(), 2);
    monday.removeEvent(mondayEvent);
    assertEquals(monday.getEvents().size(), 1);
    assertEquals(monday.getEvents().get(0), mondayEvent2);
  }

  /**
   * tests that getTasks method properly returns the correct contents of the day's list of tasks
   */
  @Test
  void getTasks() {
    monday.addTask(mondayTask2);
    List<Task> tasks = monday.getTasks();
    assertEquals(tasks.get(0), mondayTask);
    assertEquals(tasks.get(1), mondayTask2);
  }

  /**
   * tests that getEvents method properly returns the correct contents of the day's list of events
   */
  @Test
  void getEvents() {
    monday.addEvent(mondayEvent2);
    List<Event> events = monday.getEvents();
    assertEquals(events.get(0), mondayEvent);
    assertEquals(events.get(1), mondayEvent2);
  }

  /**
   * tests that a Day object can properly be converted to a DayJson object with the correct contents
   */
  @Test
  void dayToJson() {
    DayJson json = monday.dayToJson();
    assertEquals(json.day(), "MONDAY");
    assertEquals(json.tasks().length, 1);
    for (TaskJson j : json.tasks()) {
      assertEquals(j.name(), "School");
      assertEquals(j.description(), "Go to school");
      assertEquals(j.day(), "MONDAY");
      assertEquals(j.complete(), true);
      assertEquals(j.location().row(), 0);
      assertEquals(j.location().col(), 1);
    }
    for (EventJson e : json.events()) {
      assertEquals(e.name(), "Assembly Day");
      assertEquals(e.description(), "At School");
      assertEquals(e.day(), "MONDAY");
      assertEquals(e.startTime(), "6:00");
      assertEquals(e.duration(), 30);
      assertEquals(e.location().row(), 1);
      assertEquals(e.location().col(), 1);
    }
  }

  /**
   * tests that a list of tasks in a Day can be properly converted to a taskJson object with the
   * correct contents
   */
  @Test
  void tasksToJson() {
    TaskJson[] jsonTasks = monday.tasksToJson();
    for (TaskJson j : jsonTasks) {
      assertEquals(j.name(), "School");
      assertEquals(j.description(), "Go to school");
      assertEquals(j.day(), "MONDAY");
      assertEquals(j.complete(), true);
      assertEquals(j.location().row(), 0);
      assertEquals(j.location().col(), 1);
    }
  }

  /**
   * tests that a list of events in a Day can be properly converted to a eventJson object with
   * the correct contents
   */
  @Test
  void eventsToJson() {
    EventJson[] jsonEvents = monday.eventsToJson();
    for (EventJson e : jsonEvents) {
      assertEquals(e.name(), "Assembly Day");
      assertEquals(e.description(), "At School");
      assertEquals(e.day(), "MONDAY");
      assertEquals(e.startTime(), "6:00");
      assertEquals(e.duration(), 30);
      assertEquals(e.location().row(), 1);
      assertEquals(e.location().col(), 1);
    }
  }

  /**
   * tests that the getName method returns the name of the Day as an enum
   */
  @Test
  void getName() {
    assertEquals(monday.getName(), DayName.MONDAY);
  }
}
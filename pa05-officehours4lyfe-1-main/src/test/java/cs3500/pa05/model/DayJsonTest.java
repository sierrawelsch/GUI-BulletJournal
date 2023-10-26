package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the DayJson class
 */
class DayJsonTest {
  DayJson json;
  TaskJson jsonTask;
  TaskJson jsonTask2;
  TaskJson[] jsonTaskList;
  List<Task> taskList;
  EventJson jsonEvent;
  EventJson jsonEvent2;
  EventJson[] jsonEventList;
  List<Event> eventList;

  /**
   * initializes taskJson objects, eventJson objects, along with lists of those to be
   * fields of our DayJson object for testing
   */
  @BeforeEach
  void setup() {
    jsonTask = new TaskJson("Do it", "Please do it", "MONDAY", false, new CoordJson(3, 6));
    jsonTask2 =
        new TaskJson("Don't do it", "Please don't do it", "FRIDAY", false, new CoordJson(3, 5));
    taskList = new ArrayList<>();
    taskList.add(jsonTask.jsonToTask());
    taskList.add(jsonTask2.jsonToTask());
    jsonTaskList = new TaskJson[2];
    jsonTaskList[0] = jsonTask;
    jsonTaskList[1] = jsonTask2;
    jsonEvent =
        new EventJson("Party", "At someone's house", "SATURDAY", "8:00", 60, new CoordJson(6, 3));
    jsonEvent2 = new EventJson("Read", "At my house", "SUNDAY", "5:00", 60, new CoordJson(1, 3));
    jsonEventList = new EventJson[2];
    jsonEventList[0] = jsonEvent;
    jsonEventList[1] = jsonEvent2;
    eventList = new ArrayList<>();
    eventList.add(jsonEvent.jsonToEvent());
    eventList.add(jsonEvent2.jsonToEvent());
    json = new DayJson("MONDAY", jsonTaskList, jsonEventList);
  }

  /**
   * tests that a DayJson is properly converted to a Day and has their name stored as an enum
   */
  @Test
  void jsonToDay() {
    Day convertedDay = json.jsonToDay();
    assertEquals(convertedDay.getName(), DayName.MONDAY);
  }

  /**
   * tests that an array of taskJson in a day is properly converted to a list of tasks
   */
  @Test
  void jsonToTaskList() {
    List<Task> newTaskList = json.jsonToTaskList();
    for (int i = 0; i < newTaskList.size(); i++) {
      assertEquals(newTaskList.get(i).getName(), taskList.get(i).getName());
      assertEquals(newTaskList.get(i).getDayOfWeek(), taskList.get(i).getDayOfWeek());
      assertEquals(newTaskList.get(i).getDescription(), taskList.get(i).getDescription());
      assertEquals(newTaskList.get(i).getComplete(), taskList.get(i).getComplete());
      assertEquals(newTaskList.get(i).getLocation().getRow(),
          taskList.get(i).getLocation().getRow());
      assertEquals(newTaskList.get(i).getLocation().getCol(),
          taskList.get(i).getLocation().getCol());
    }
  }

  /**
   * tests that an array of eventJson in a day is properly converted to a list of events
   */
  @Test
  void jsonToEventList() {
    List<Event> newEventList = json.jsonToEventList();
    for (int i = 0; i < newEventList.size(); i++) {
      assertEquals(newEventList.get(i).getName(), eventList.get(i).getName());
      assertEquals(newEventList.get(i).getDayOfWeek(), eventList.get(i).getDayOfWeek());
      assertEquals(newEventList.get(i).getDescription(), eventList.get(i).getDescription());
      assertEquals(newEventList.get(i).getStartTime(), eventList.get(i).getStartTime());
      assertEquals(newEventList.get(i).getDuration(), eventList.get(i).getDuration());
      assertEquals(newEventList.get(i).getLocation().getRow(),
          eventList.get(i).getLocation().getRow());
      assertEquals(newEventList.get(i).getLocation().getCol(),
          eventList.get(i).getLocation().getCol());
    }
  }
}
package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * the Json representation of a Day object
 *
 * @param day the name of a day
 *
 * @param tasks the tasks on a day
 *
 * @param events the events on a day
 *
 */

public record DayJson(
    @JsonProperty("day") String day,
    @JsonProperty("tasks") TaskJson[] tasks,
    @JsonProperty("events") EventJson[] events) {

  /**
   * converts the DayJson to a Day object
   *
   * @return a Day object
   */

  public Day jsonToDay() {
    return new Day(DayName.stringToEnum(day));
  }

  /**
   * converts the DayJson to a list of Task objects
   *
   * @return a list of Task objects
   */

  public List<Task> jsonToTaskList() {
    List<Task> taskList = new ArrayList<>();
    for (TaskJson t : tasks) {
      taskList.add(t.jsonToTask());
    }
    return taskList;
  }

  /**
   * converts the DayJson to a list of Event objects
   *
   * @return a list of Event objects
   */

  public List<Event> jsonToEventList() {
    List<Event> eventList = new ArrayList<>();
    for (EventJson e : events) {
      eventList.add(e.jsonToEvent());
    }
    return eventList;
  }
}


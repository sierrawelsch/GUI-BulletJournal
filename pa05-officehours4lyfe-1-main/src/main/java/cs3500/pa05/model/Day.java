package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a day and the tasks and events it has
 */
public class Day { //TODO make private and fix whatever issues arise with it
  private DayName name;
  private List<Task> tasks;
  private List<Event> events;

  /**
   * initializes a Day object with a DayName enum passed in
   *
   * @param name name of the day
   */
  public Day(DayName name) {
    this.name = name;
    this.tasks = new ArrayList<>();
    this.events = new ArrayList<>();
  }

  /**
   * adds a task to the day
   *
   * @param t the task to be added
   */

  public void addTask(Task t) {
    tasks.add(t);
  }

  /**
   * adds an event to the day
   *
   * @param e the event to be added
   */

  public void addEvent(Event e) {
    events.add(e);
  }

  /**
   * removes a task from the day
   *
   * @param t the task to be removed
   */

  public void removeTask(Task t) {
    tasks.remove(t);
  }

  /**
   * removes an event from the day
   *
   * @param e the event to be removed
   */

  public void removeEvent(Event e) {
    events.remove(e);
  }

  /**
   * retrieves the tasks from a day
   *
   * @return the tasks from a day
   */

  public List<Task> getTasks() {
    return tasks;
  }

  /**
   * retrieves the events from a day
   *
   * @return the events from a day
   */

  public List<Event> getEvents() {
    return events;
  }

  /**
   * converts a Day object to a DayJson object
   *
   * @return a DayJson object to represent a day
   */

  public DayJson dayToJson() {
    return new DayJson(name.toString(), tasksToJson(), eventsToJson());
  }

  /**
   * converts the Task objects into TaskJson objects
   *
   * @return the TaskJson objects to represent a Task object
   */

  public TaskJson[] tasksToJson() {
    TaskJson[] tasksInJson = new TaskJson[tasks.size()];
    for (int i = 0; i < tasks.size(); i++) {
      tasksInJson[i] = tasks.get(i).taskToJson();
    }
    return tasksInJson;
  }

  /**
   * converts the Event objects into EventJson objects
   *
   * @return the EventJson objects to represent an Event object
   */

  public EventJson[] eventsToJson() {
    EventJson[] eventsInJson = new EventJson[events.size()];
    for (int i = 0; i < events.size(); i++) {
      eventsInJson[i] = events.get(i).eventToJson();
    }
    return eventsInJson;
  }

  /**
   * retrieves the name of the day
   *
   * @return the name of the day
   */

  public DayName getName() {
    return name;
  }

}

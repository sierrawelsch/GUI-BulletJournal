package cs3500.pa05.model;

/**
 * represents a BujoItem of a Task
 */

public class Task extends BujoItem {
  private boolean complete;

  public Task(String name, String description, DayName dayNameOfWeek, boolean complete,
              Coord onBujo) {
    super(name, description, dayNameOfWeek, onBujo);
    this.complete = complete;
  }

  /**
   * retrieves whether a Task is complete or not
   *
   * @return whether a Task is complete or not
   */

  public boolean getComplete() {
    return complete;
  }

  /**
   * sets the completion status of a Task
   *
   * @param newComplete the new status of a Task
   */

  public void setComplete(boolean newComplete) {
    this.complete = newComplete;
  }

  /**
   * retrieves the day of the week an event is on
   *
   * @return the day of the week an event is on
   */

  @Override
  public DayName getDayOfWeek() {
    return dayNameOfWeek;
  }

  /**
   * converts a Task object to a TaskJson
   *
   * @return an TaskJson to represent a Task object
   */

  public TaskJson taskToJson() {
    return new TaskJson(name, description, dayNameOfWeek.toString(),
        complete, location.coordToJson());
  }

  /**
   * sets the name, description, day of the week, completion status, and location of a Task
   *
   * @param name the task's name
   *
   * @param description a description of the task
   *
   * @param dayOfWeek the day of the week a task is on
   *
   * @param complete whether a task is complete or not
   *
   * @param location the location on the calendar of a task
   */

  public void setTask(String name, String description, DayName dayOfWeek, boolean complete,
                      Coord location) {
    this.name = name;
    this.description = description;
    this.dayNameOfWeek = dayOfWeek;
    this.complete = complete;
    this.location = location;
  }

}

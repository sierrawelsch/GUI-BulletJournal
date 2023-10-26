package cs3500.pa05.model;

/**
 * represents a BujoItem of an Event
 */

public class Event extends BujoItem {

  private String startTime;
  private int duration;

  /**
   * initializes an event with the given fields
   *
   * @param name name of the event
   *
   * @param description description of the event
   *
   * @param dayNameOfWeek name of the week the event falls
   *
   * @param startTime startTime of the event
   *
   * @param duration how long the event is
   *
   * @param location the location on the journal the event is displayed
   */
  public Event(String name, String description, DayName dayNameOfWeek, String startTime,
               int duration, Coord location) {
    super(name, description, dayNameOfWeek, location);
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * retrieves the day of the week an event is on
   *
   * @return the day of the week an event is on
   */

  @Override
  public DayName getDayOfWeek() {
    return this.dayNameOfWeek;
  }

  /**
   * retrieves the duration of an event
   *
   * @return the duration of an event
   */

  public int getDuration() {
    return duration;
  }

  /**
   * retrieves the startTime of an event
   *
   * @return the startTime of an event
   */

  public String getStartTime() {
    return startTime;
  }

  /**
   * converts an Event object to an EventJson
   *
   * @return an EventJson to represent an Event object
   */

  public EventJson eventToJson() {
    return new EventJson(name, description, dayNameOfWeek.toString(), startTime, duration,
        location.coordToJson());
  }

  /**
   * sets the name, description, day of the week, start time, duration, and location of an event
   *
   * @param name the event's name
   *
   * @param description a description of the event
   *
   * @param dayOfWeek the day of the week an event is on
   *
   * @param startTime the start time of the event
   *
   * @param duration the duration of the event
   *
   * @param location the location on the calendar of an event
   */

  public void setEvent(String name, String description, DayName dayOfWeek, String startTime,
                       int duration,
                       Coord location) {
    this.name = name;
    this.description = description;
    this.dayNameOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.duration = duration;
    this.location = location;
  }

}

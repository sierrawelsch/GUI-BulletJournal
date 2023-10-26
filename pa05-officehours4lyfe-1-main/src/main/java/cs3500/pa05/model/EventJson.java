package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * the Json representation of an Event
 *
 * @param name the name of an Event
 *
 * @param description the description of an Event
 *
 * @param day the day an Event is on
 *
 * @param startTime the time an Event starts at
 *
 * @param duration how long an Event is
 *
 * @param location where the Event is on the calendar
 */

public record EventJson(
    @JsonProperty("name-of-event") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day-of-event") String day,
    @JsonProperty("start-time") String startTime,
    @JsonProperty("duration") int duration,
    @JsonProperty("location") CoordJson location) {

  /**
   * converts an EventJson into an Event object
   *
   * @return an Event object
   */
  public Event jsonToEvent() {
    return new Event(name, description, DayName.stringToEnum(day), startTime, duration,
        location.jsonToCoord());
  }
}

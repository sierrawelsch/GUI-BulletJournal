package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * a Json representation of a Task
 *
 * @param name the name of a Task
 *
 * @param description a description of a Task
 *
 * @param day the day a Task is on
 *
 * @param complete whether a Task is complete or not
 *
 * @param location the location of a Task on the calendar
 */

public record TaskJson(
    @JsonProperty("name-of-task") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day-of-event") String day,
    @JsonProperty("complete") boolean complete,
    @JsonProperty("location") CoordJson location) {

  /**
   * converts a TaskJson into a Task object
   *
   * @return a Task object
   */

  public Task jsonToTask() {
    return new Task(name, description, DayName.stringToEnum(day), complete, location.jsonToCoord());
  }
}

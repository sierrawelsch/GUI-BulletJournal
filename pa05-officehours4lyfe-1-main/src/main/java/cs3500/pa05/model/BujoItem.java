package cs3500.pa05.model;

/**
 * an abstrat class to represent a Bullet Journal item
 */

public abstract class  BujoItem {
  protected String name;
  protected String description;
  protected DayName dayNameOfWeek;
  protected Coord location;

  /**
   * represents an BujoItem which is an abstract class extended by Task and Event
   *
   * @param name the name of the item
   *
   * @param description the description of the item
   *
   * @param dayNameOfWeek the day the item lands on
   *
   * @param location the location of the item on the journal
   */
  public BujoItem(String name, String description, DayName dayNameOfWeek, Coord location) {
    this.name = name;
    this.description = description;
    this.dayNameOfWeek = dayNameOfWeek;
    this.location = location;
  }

  /**
   * retrieves the name of the BujoItem
   *
   * @return the name of the BujoItem
   */

  public String getName() {
    return name;
  }

  /**
   * retrieves the description of the BujoItem
   *
   * @return the description of the BujoItem
   */

  public String getDescription() {
    return description;
  }

  /**
   * retrieves the day of the week the BujoItem is on
   *
   * @return the day of the week the BujoItem is on
   */

  public abstract DayName getDayOfWeek();

  /**
   * retrieves the coordinates the BujoItem is on
   *
   * @return the coordinates the BujoItem is on
   */

  public Coord getLocation() {
    return location;
  }

}
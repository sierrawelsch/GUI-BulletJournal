package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * represents a week on a calendar
 */

public class Week {
  private String weekName;
  private List<Day> days;
  private List<Task> allTasks;
  private List<Event> allEvents;
  private int maxTasks;
  private int maxEvents;
  private String quotesAndNotes;
  private List<Task> taskQueue;
  private ThemeType themeType;
  private List<Color> themeInfo;

  /**
   * for converting from json to week aka opening an existing file
   */
  public Week(String weekName, List<Day> days, int maxTasks, int maxEvents, String quotesAndNotes,
              List<Task> taskQueue, ThemeType themeType, List<Color> themeInfo) {
    this.weekName = weekName;
    this.allTasks = new ArrayList<>();
    this.days = days;
    this.allEvents = new ArrayList<>();
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
    this.quotesAndNotes = quotesAndNotes;
    this.taskQueue = taskQueue;
    this.themeType = themeType;
    this.themeInfo = themeInfo;
  }

  /**
   *  for initializing a Week when your editing on a new file
   */
  public Week(List<Day> days) {
    this.weekName = "";
    this.allTasks = new ArrayList<>();
    this.days = days;
    this.allEvents = new ArrayList<>();
    this.maxTasks = 13;
    this.maxEvents = 13;
    quotesAndNotes = "";
    this.taskQueue = this.allTasks;
    this.themeType = ThemeType.DEFAULT;
    this.themeInfo = new ArrayList<>();
  }

  /**
   * retrieves all the tasks of a week
   *
   * @return all the tasks of a week
   */

  public List<Task> getAllTasks() {
    allTasks.clear();
    for (Day d : days) {
      allTasks.addAll(d.getTasks());
    }
    return allTasks;
  }

  /**
   * retrieves all the events of a week
   *
   * @return all the events of a week
   */

  public List<Event> getAllEvents() {
    allEvents.clear();
    for (Day d : days) {
      allEvents.addAll(d.getEvents());
    }
    return allEvents;
  }

  /**
   * sets the maximum amount of tasks for the week
   *
   * @param maxTasks the maximum amount of tasks
   */

  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * sets the maximum amount of events for the week
   *
   * @param maxEvents the maximum amount of events
   */

  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * converts a Week object to a WeekJson
   *
   * @return an WeekJson to represent a Week object
   */

  public WeekJson weekToJson() {
    DayJson[] daysJson = new DayJson[days.size()];
    for (int i = 0; i < days.size(); i++) {
      daysJson[i] = days.get(i).dayToJson();
    }
    TaskJson[] taskQueueJson = new TaskJson[taskQueue.size()];
    for (int i = 0; i < taskQueue.size(); i++) {
      taskQueueJson[i] = taskQueue.get(i).taskToJson();
    }
    String[] themeColors = new String[themeInfo.size()];
    for (int i = 0; i < themeInfo.size(); i++) {
      themeColors[i] = themeInfo.get(i).toString();
    }
    return new WeekJson(weekName, maxTasks, maxEvents, daysJson, quotesAndNotes, taskQueueJson,
        themeType.toString(),
        themeColors);
  }

  /**
   * retrieves the days on the week
   *
   * @return the days on the week
   */

  public List<Day> getDays() {
    return days;
  }

  /**
   * retrieves the name of the week
   *
   * @return the name of the week
   */

  public String getWeekName() {
    return weekName;
  }

  /**
   * retrieves the maximum tasks of the week
   *
   * @return the maximum tasks of the week
   */

  public int getMaxTasks() {
    return maxTasks;
  }

  /**
   * retrieves the maximum events of the week
   *
   * @return the maximum events of the week
   */

  public int getMaxEvents() {
    return maxEvents;
  }

  /**
   * sets the content of the Quotes and Notes section
   *
   * @param newQuotesAndNotes the content to be set
   */

  public void setQuotesAndNotes(String newQuotesAndNotes) {
    quotesAndNotes = newQuotesAndNotes;
  }

  /**
   * retrieves the content of the Quotes and Notes section
   *
   * @return the content of the Quotes and Notes section
   */

  public String getQuotesAndNotes() {
    return this.quotesAndNotes;
  }

  /**
   * adds a task to the Task Queue
   *
   * @param t the task to be added
   */

  public void populateTaskQueue(Task t) {
    taskQueue.add(t);
  }

  /**
   * removes a task from the Task Queue
   *
   * @param t the task to be removed
   */

  public void unpopulateTaskQueue(Task t) {
    taskQueue.remove(t);
  }

  /**
   * sets the theme for the week
   *
   * @param newThemeType the theme to be seet
   */

  public void setTheme(ThemeType newThemeType) {
    this.themeType = newThemeType;
  }

  /**
   * retrieves the theme of the week
   *
   * @return the theme of the week
   */

  public ThemeType getTheme() {
    return this.themeType;
  }

  /**
   * retrieves the theme's colors
   *
   * @return the theme's colors
   */

  public List<Color> getThemeInfo() {
    return themeInfo;
  }

  /**
   * adds a color to a theme
   *
   * @param color the color to be added to a theme
   */

  public void addToThemeInfo(Color color) {
    themeInfo.add(color);
  }

  /**
   * resets the theme to be empty
   */

  public void resetThemeInfo() {
    themeInfo = new ArrayList<>();
  }

  /**
   * sets the week's name
   *
   * @param newWeekName the new name of the week
   */

  public void setWeekName(String newWeekName) {
    this.weekName = newWeekName;
  }

  /**
   * retrieves the task queue
   *
   * @return the task queue
   */
  public List<Task> getTaskQueue() {
    return taskQueue;
  }

  /**
   * sets the week with another week's properties
   *
   * @param oldWeek the week to be copied from
   */

  public void setWeekFromWeek(Week oldWeek) {
    this.weekName = oldWeek.weekName;
    this.days.clear();
    for (Day day : oldWeek.getDays()) {
      this.days.add(day);
    }

    this.allTasks.clear();
    for (Task bj : oldWeek.getAllTasks()) {
      this.allTasks.add(bj);
    }

    this.allEvents.clear();
    for (Event bj : oldWeek.getAllEvents()) {
      this.allEvents.add(bj);
    }

    this.maxEvents = oldWeek.maxEvents;
    this.maxTasks = oldWeek.maxTasks;

    this.quotesAndNotes = oldWeek.quotesAndNotes;

    this.taskQueue.clear();
    for (Task t : oldWeek.getAllTasks()) {
      this.taskQueue.add(t);
    }


    this.themeType = oldWeek.getTheme();

    this.themeInfo.clear();
    for (Color c : oldWeek.getThemeInfo()) {
      this.themeInfo.add(c);
    }
  }

}

package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the tests for the Week class
 */
class WeekTest {
  Week content;
  List<Task> tasks;
  Task sundayTask;
  Task sundayTask2;
  Task thursdayTask;
  Event wednesdayEvent;
  Event fridayEvent;
  Event saturdayEvent;
  Event saturdayEvent2;
  List<Day> days;
  Day sunday = new Day(DayName.SUNDAY);
  Day monday = new Day(DayName.MONDAY);
  Day tuesday = new Day(DayName.TUESDAY);
  Day wednesday = new Day(DayName.WEDNESDAY);
  Day thursday = new Day(DayName.THURSDAY);
  Day friday = new Day(DayName.FRIDAY);
  Day saturday = new Day(DayName.SATURDAY);


  /**
   * initializes Task and Event objects to be added to Lists of Tasks and
   * Events in the List of Day objects which is added as a parameter to a Week
   * object to use for testing
   */
  @BeforeEach
  void setup() {
    days = new ArrayList<>();
    days.add(sunday);
    days.add(monday);
    days.add(tuesday);
    days.add(wednesday);
    days.add(thursday);
    days.add(friday);
    days.add(saturday);
    sundayTask = new Task("Lunch", "Eat food", DayName.SUNDAY, false, new Coord(0, 0));
    sunday.addTask(sundayTask);
    sundayTask2 = new Task("Sleep", "Sleep in my bed", DayName.SUNDAY, false, new Coord(1, 0));
    sunday.addTask(sundayTask2);
    thursdayTask =
        new Task("Soccer", "Go to soccer practice", DayName.THURSDAY, false, new Coord(0, 4));
    thursday.addTask(thursdayTask);

    wednesdayEvent =
        new Event("Assembly Day", "At School", DayName.WEDNESDAY, "6:00", 30, new Coord(0, 3));
    wednesday.addEvent(wednesdayEvent);
    fridayEvent =
        new Event("Dance", "At My Dance School", DayName.MONDAY, "7:00", 30, new Coord(3, 5));
    friday.addEvent(fridayEvent);
    saturdayEvent =
        new Event("Spiderman", "Go see spiderman", DayName.SATURDAY, "9:00", 50, new Coord(1, 6));
    saturday.addEvent(saturdayEvent);
    saturdayEvent2 =
        new Event("Barbie", "Go see barbie", DayName.SATURDAY, "10:00", 100, new Coord(0, 6));
    saturday.addEvent(saturdayEvent2);
    content = new Week(days);
  }

  /**
   * tests that the getAllTasks() method returns all the tasks in
   * the list of days that is contained in the weekObject
   */
  @Test
  void getAllTasks() {
    List<Task> allTasks = content.getAllTasks();
    assertEquals(allTasks.get(0), sundayTask);
    assertEquals(allTasks.get(1), sundayTask2);
    assertEquals(allTasks.get(2), thursdayTask);
  }

  /**
   * tests that the getAllEvents() method returns all the events in the
   * list of days that is contained in the weekObject
   */
  @Test
  void getAllEvents() {
    List<Event> allEvents = content.getAllEvents();
    assertEquals(allEvents.get(0), wednesdayEvent);
    assertEquals(allEvents.get(1), fridayEvent);
    assertEquals(allEvents.get(2), saturdayEvent);
    assertEquals(allEvents.get(3), saturdayEvent2);
  }

  /**
   * tests that the maxTasks of a Week object is set to the passed in new maxTasks value
   */
  @Test
  void setMaxTasks() {
    assertEquals(content.getMaxTasks(), 13);
    content.setMaxTasks(3);
    assertEquals(content.getMaxTasks(), 3);
  }

  /**
   * tests that the maxEvents of a Week object is set to the passed in new maxEvents value
   */
  @Test
  void setMaxEvents() {
    assertEquals(content.getMaxEvents(), 13);
    content.setMaxEvents(5);
    assertEquals(content.getMaxEvents(), 5);
  }

  /**
   * tests that a Week object can properly be converted to a WeekJson object with all the
   * same contents
   */
  @Test
  void weekToJson() {
    content.setQuotesAndNotes("I believe in you.");
    WeekJson json = content.weekToJson();
    assertEquals(json.name(), "");
    assertEquals(json.maxTasks(), 13);
    assertEquals(json.maxEvents(), 13);
    assertEquals(json.qAndNotes(), "I believe in you.");
    TaskJson[] jsonTask = json.tasks();
    for (int i = 0; i < jsonTask.length; i++) {
      assertEquals(jsonTask[0], sundayTask.taskToJson());
    }
    assertEquals(json.theme(), "DEFAULT");
    assertEquals(json.themeContents().length, 0);
  }

  /**
   * tests that a Week object's list of days is returned when the getDays method is called
   */
  @Test
  void getDays() {
    assertEquals(content.getDays(), days);
  }

  /**
   * tests that a Week object's name of the week is returned when the getWeekName method is called
   */
  @Test
  void getWeekName() {
    assertEquals(content.getWeekName(), "");
  }

  /**
   * tests that a Week object's max tasks is returned when the getMaxTasks method is called
   */
  @Test
  void getMaxTasks() {
    assertEquals(content.getMaxTasks(), 13);
  }

  /**
   * tests that a Week object's max events is returned when the getMaxEvents method is called
   */
  @Test
  void getMaxEvents() {
    assertEquals(content.getMaxEvents(), 13);
  }

  /**
   * tests that given a String the quotes and notes of a Week object is set to that passed in value
   */
  @Test
  void setQuotesAndNotes() {
    assertEquals(content.getQuotesAndNotes(), "");
    content.setQuotesAndNotes("I want to never give up");
    assertEquals(content.getQuotesAndNotes(), "I want to never give up");
  }

  /**
   * tests that a Week object's quotes and notes is returned when the getQuotesAndNotes
   * method is called
   */
  @Test
  void getQuotesAndNotes() {
    content.setQuotesAndNotes("I want to grow up");
    assertEquals(content.getQuotesAndNotes(), "I want to grow up");
  }

  /**
   * tests that given a Task object, that task object is
   * added to the taskQueue field in a Week object
   */
  @Test
  void populateTaskQueue() {
    assertEquals(content.getTaskQueue().size(), 0);
    content.populateTaskQueue(sundayTask);
    assertEquals(content.getTaskQueue().size(), 1);
    assertEquals(content.getTaskQueue().get(0), sundayTask);
  }

  /**
   * tests that given a Task object, that task object is removed
   * from the taskQueue field in a Week object
   */
  @Test
  void unpopulateTaskQueue() {
    content.populateTaskQueue(sundayTask);
    assertEquals(content.getTaskQueue().get(0), sundayTask);
    content.unpopulateTaskQueue(sundayTask);
    assertEquals(content.getTaskQueue().size(), 0);
  }

  /**
   * tests that given a ThemeType the theme of a Week object is set to that passed in value
   */
  @Test
  void setTheme() {
    assertEquals(content.getTheme(), ThemeType.DEFAULT);
    content.setTheme(ThemeType.MODERN);
    assertEquals(content.getTheme(), ThemeType.MODERN);
  }

  /**
   * tests that a Week object's theme is returned as a ThemeType enum when the getTheme
   * method is called
   */
  @Test
  void getTheme() {
    content.setTheme(ThemeType.RETRO);
    assertEquals(content.getTheme(), ThemeType.RETRO);
  }

  /**
   * tests that a Week object's themeInfo is returned as a list of colors when the getThemeInfo
   * method is called
   */
  @Test
  void getThemeInfo() {
    assertEquals(content.getThemeInfo(), new ArrayList<>());
  }


  /**
   * tests that given a Color, that Color is added to the themeInfo field (a list) in a Week object
   */
  @Test
  void addToThemeInfo() {
    content.addToThemeInfo(Color.PALEGREEN);
    assertEquals(content.getThemeInfo().size(), 1);
    assertEquals(content.getThemeInfo().get(0), Color.PALEGREEN);
  }

  /**
   * tests that when this method is called the themeInfo field is set to a new empty array list
   * and the contents have been wiped
   */
  @Test
  void resetThemeInfo() {
    content.addToThemeInfo(Color.PALEGREEN);
    assertEquals(content.getThemeInfo().get(0), Color.PALEGREEN);
    content.resetThemeInfo();
    assertEquals(content.getThemeInfo(), new ArrayList<>());
  }

  /**
   * tests that given a week name the name of a Week object is set to that passed in value
   */
  @Test
  void setWeekName() {
    content.setWeekName("sierra's week");
    assertEquals(content.getWeekName(), "sierra's week");
  }

  /**
   * tests that a Week object's taskQueue is returned as a list of tasks when the getTaskQueue
   * method is called
   */
  @Test
  void getTaskQueue() {
    content.populateTaskQueue(sundayTask);
    content.populateTaskQueue(sundayTask2);
    content.populateTaskQueue(thursdayTask);
    List<Task> queue = content.getTaskQueue();
    assertEquals(queue.get(0), sundayTask);
    assertEquals(queue.get(1), sundayTask2);
    assertEquals(queue.get(2), thursdayTask);
  }

  /**
   * tests that given new values that correlate to the fields of a
   * Week the fields of a Week object will be set to the given values
   */
  @Test
  void setWeekFromWeek() {
    List<Color> themeInfo = new ArrayList<>();
    themeInfo.add(Color.BLUE);
    themeInfo.add(Color.PURPLE);
    themeInfo.add(Color.GREEN);
    themeInfo.add(Color.RED);
    List<Task> queue = new ArrayList<>();
    Week oldWeek = new Week("old", days, 2, 3, "make sure to test for your PA05 assignment", queue,
        ThemeType.CUSTOM, themeInfo);
    content.setWeekFromWeek(oldWeek);
    assertEquals(content.getWeekName(), "old");
    assertEquals(content.getDays(), days);
    assertEquals(content.getMaxTasks(), 2);
    assertEquals(content.getMaxEvents(), 3);
    assertEquals(content.getQuotesAndNotes(), "make sure to test for your PA05 assignment");
    assertEquals(content.getTaskQueue().size(), 0);
    assertEquals(content.getTheme(), ThemeType.CUSTOM);
    assertEquals(content.getThemeInfo().get(0), Color.BLUE);
    assertEquals(content.getThemeInfo().get(1), Color.PURPLE);
    assertEquals(content.getThemeInfo().get(2), Color.GREEN);
    assertEquals(content.getThemeInfo().get(3), Color.RED);
  }
}
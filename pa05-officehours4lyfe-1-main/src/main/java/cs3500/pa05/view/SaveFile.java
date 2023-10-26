package cs3500.pa05.view;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayJson;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskJson;
import cs3500.pa05.model.ThemeType;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.WeekJson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * represents a class to handle saving a file
 */

public class SaveFile {
  private Path file;

  /**
   * initializes a SaveFile object that takes in a Path
   *
   * @param file the file that needs to be saved to
   */
  public SaveFile(Path file) {
    this.file = file;
  }

  /**
   * method that serializes a passed in record
   *
   * @param record Record to serialize
   *
   * @return JsonNode the serialized record
   *
   * @throws IllegalArgumentException when record can't be serialized
   */

  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("This record can not be serialized!");
    }
  }

  /**
   * converts a Week object into a WeekJson
   *
   * @param week the week to be converted
   */

  public void convertToJson(Week week) {
    WeekJson weekJson = week.weekToJson();
    JsonNode serializedWeek = serializeRecord(weekJson);
    writeFile(serializedWeek.toString());
  }

  /**
   * writes the given content to a file
   *
   * @param content the content to be written onto a file
   */

  private void writeFile(String content) {
    FileWriter writer = null;
    try {
      writer = new FileWriter(file.toFile());
    } catch (IOException e) {
      System.out.println("Cannot write to this file!");
    }
    try {
      writer.write(content);
      writer.close();
    } catch (IOException e) {
      System.out.println("Cannot write this content to this file!");
    }
  }

  /**
   * reads the content from a file and converts it into a Week object
   *
   * @return a Week object from the file
   *
   * @throws IOException if incorrect input
   */

  public Week convertToWeek() throws IOException {
    WeekJson weekContent = translateFile(file);
    Week weekFromFile;
    List<Day> convertedDays = new ArrayList<>();
    for (DayJson day : weekContent.week()) {
      Day convertedDay = day.jsonToDay();
      List<Task> tasksInaDay = day.jsonToTaskList();
      for (Task t : tasksInaDay) {
        convertedDay.addTask(t);
      }
      List<Event> eventsInaDay = day.jsonToEventList();
      for (Event e : eventsInaDay) {
        convertedDay.addEvent(e);
      }
      convertedDays.add(convertedDay);
    }
    List<Task> convertedTasks = new ArrayList<>();
    for (TaskJson jsonTask : weekContent.tasks()) {
      Task task = jsonTask.jsonToTask();
      convertedTasks.add(task);
    }
    List<Color> themeColors = new ArrayList<>();
    for (String color : weekContent.themeContents()) {
      Color c = Color.web(color);
      themeColors.add(c);
    }
    weekFromFile =
        new Week(weekContent.name(), convertedDays, weekContent.maxTasks(), weekContent.maxEvents(),
            weekContent.qAndNotes(), convertedTasks, ThemeType.stringToEnum(weekContent.theme()),
            themeColors);
    return weekFromFile;
  }

  /**
   * reads the Json file and deserializes it into a WeekJson object
   *
   * @param file the file to be translated
   *
   * @return a WeekJson object
   *
   * @throws IOException if incorrect input
   */

  private WeekJson translateFile(Path file) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File jsonWeekFile = new File(file.toFile().toString());
    WeekJson weekContent = null;
    weekContent = mapper.readValue(jsonWeekFile, WeekJson.class);
    return weekContent;
  }

}

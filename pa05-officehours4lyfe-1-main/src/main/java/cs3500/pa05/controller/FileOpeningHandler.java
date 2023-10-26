package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.ThemeType;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.SaveFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * represents a class to handle opening a file
 */

public class FileOpeningHandler extends AbstractHandler implements EventHandler<ActionEvent> {
  private Stage primaryStage;
  private StringProperty[][] textBoxes;
  private VBox taskQueue;
  private TextArea quotesAndNotesTextArea;
  private TextField weekNameTextField;
  private Theme themeItems;
  private Week content;
  private List<Day> days;
  private List<HBox> taskhBoxes;
  private TextField maxEventsTextField;
  private TextField maxTasksTextField;
  private WeeklyOverview weeklyOverview;

  /**
   * represents the event handler for letting a user open a file
   *
   * @param primaryStage the stage the scene is on
   *
   * @param textBoxes the binded values of the grid pane
   *
   * @param taskQueue the Vbox that stores the taskQueue
   *
   * @param quotesAndNotesTextArea the TextArea where the quotes and notes are stored
   *
   * @param weekNameTextField the TextField where the name of the week is stored
   *
   * @param themeItems the colors that represent the different colors the
   *                   user can choose in custom theme
   *
   * @param days a list of days in a week
   *
   * @param content represents a week and all the data it contains
   *
   * @param taskhBoxes represents a list of hboxes which stores each task in the taskQueue
   *
   * @param maxEventsTextField the textField that stores the maxEvents
   *
   * @param maxTasksTextField the textField that stores the maxTasks
   *
   * @param weeklyOverview represents the week overview feature that is constantly updated and
   *                       displayed to the user
   */
  public FileOpeningHandler(Stage primaryStage, StringProperty[][] textBoxes, VBox taskQueue,
                            TextArea quotesAndNotesTextArea, TextField weekNameTextField,
                            Theme themeItems,
                            List<Day> days, Week content, List<HBox> taskhBoxes,
                            TextField maxEventsTextField,
                            TextField maxTasksTextField, WeeklyOverview weeklyOverview) {
    super();
    this.primaryStage = primaryStage;
    this.textBoxes = textBoxes;
    this.taskQueue = taskQueue;
    this.quotesAndNotesTextArea = quotesAndNotesTextArea;
    this.weekNameTextField = weekNameTextField;
    this.themeItems = themeItems;
    this.content = content;
    this.days = days;
    this.taskhBoxes = taskhBoxes;
    this.maxEventsTextField = maxEventsTextField;
    this.maxTasksTextField = maxTasksTextField;
    this.weeklyOverview = weeklyOverview;
  }

  /**
   * handles the opening of a file by a user
   *
   * @param event the action event
   */

  @Override
  public void handle(ActionEvent event) {
    primaryStage.setTitle("Open File");
    final FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(primaryStage);
    if (file != null && file.getName().endsWith(".bujo")) {
      SaveFile sf = new SaveFile(file.toPath());
      try {
        content.setWeekFromWeek(sf.convertToWeek());
        displayWeek(content);
        weeklyOverview.updateWeeklyOverview(content);
      } catch (IOException e) {
        displayErrorAlert("Parsing Error", "This file can't be parsed into Json. Try again!");
      }
    } else {
      displayErrorAlert("Error",
          "This file is not of type bujo or null and therefore can't be opened. Try again!");
    }
  }

  /**
   * displays the week
   *
   * @param content the data in a week
   */

  private void displayWeek(Week content) {
    //putting the week name back in if it exists
    this.weekNameTextField.setText(content.getWeekName());
    this.weekNameTextField.setEditable(true);
    this.maxTasksTextField.setEditable(true);
    if (content.getMaxTasks() < 13) {
      this.maxTasksTextField.setText("" + content.getMaxTasks());
    }
    this.maxEventsTextField.setEditable(true);
    if (content.getMaxEvents() < 13) {
      this.maxEventsTextField.setText("" + content.getMaxEvents());
    }
    this.quotesAndNotesTextArea.setText(content.getQuotesAndNotes());
    this.quotesAndNotesTextArea.setEditable(true);
    resetCalendar();
    displayCalendarView();
    displayTaskQueue();

  }

  /**
   * displays the calendar
   */

  private void displayCalendarView() {
    for (Day day : days) {
      for (Task t : day.getTasks()) {
        int row = t.getLocation().getRow();
        int col = t.getLocation().getCol();
        textBoxes[row][col].set(t.getName() + "\n" + t.getDescription() + "\n" + t.getComplete());
      }
      for (Event e : day.getEvents()) {
        int row = e.getLocation().getRow();
        int col = e.getLocation().getCol();
        textBoxes[row][col].set(
            e.getName() + "\n" + e.getDescription() + "\n" + e.getStartTime() + "\n"
                + e.getDuration());
      }
    }
  }

  /**
   * displays the task queue
   */
  private void displayTaskQueue() {

    for (Task t : content.getTaskQueue()) {
      HBox taskhBox = new HBox(3);
      taskhBox.setMaxHeight(50);
      taskhBox.setMaxWidth(100);
      CheckBox check = new CheckBox();
      if (t.getComplete()) {
        check.setSelected(true);
      }
      TextArea taskInfo = new TextArea(t.getName() + "\n" + t.getComplete());
      taskInfo.setEditable(false);

      taskhBox.getChildren().addAll(check, taskInfo);
      taskhBoxes.add(taskhBox);
      taskQueue.getChildren().add(taskhBox);
      check.setOnAction(e -> {
        if (check.isSelected()) {
          handleComplete(t, taskhBox, true,
              textBoxes[t.getLocation().getRow()][t.getLocation().getCol()]);

        } else {
          handleComplete(t, taskhBox, false,
              textBoxes[t.getLocation().getRow()][t.getLocation().getCol()]);
        }
      });
    }
    displayWeekTheme();
  }


  /**
   * displays the theme that was set
   */

  private void displayWeekTheme() {
    //theme stuff
    if (content.getTheme().equals(ThemeType.MODERN)) {
      themeItems.setModernTheme();
    } else if (content.getTheme().equals(ThemeType.CUSTOM)) {
      themeItems.setCustomTheme(content.getThemeInfo());
    } else if (content.getTheme().equals(ThemeType.PASTEL)) {
      themeItems.setPastelTheme();
    } else if (content.getTheme().equals(ThemeType.RETRO)) {
      themeItems.setRetroTheme();
    } else if (content.getTheme().equals(ThemeType.DEFAULT)) {
      themeItems.defaultTheme();
    }
  }


  /**
   * handles when a task is marked complete
   *
   * @param t the task
   *
   * @param taskhBox the Hbox a task is in
   *
   * @param status whether the task is marked complete or not
   *
   * @param textBox the TextBox of the task
   */

  private void handleComplete(Task t, HBox taskhBox, boolean status, StringProperty textBox) {
    taskhBox.getChildren().remove(taskhBox.getChildren().get(1));
    TextArea trueStatus = new TextArea(t.getName() + "\n" + status);
    taskhBox.getChildren().add(trueStatus);
    int completeIndex;
    if (status) {
      completeIndex = textBox.getValue().indexOf("false");
      textBox.set(textBox.getValue().substring(0, completeIndex) + "true");
      t.setComplete(true);

    } else {
      completeIndex = textBox.getValue().indexOf("true");
      textBox.set(textBox.getValue().substring(0, completeIndex) + "false");
      t.setComplete(false);
    }
    weeklyOverview.updateWeeklyOverview(content);
  }

  /**
   * resets the calendar to be empty
   */

  private void resetCalendar() {
    for (int row = 1; row < 13; row++) {
      for (int col = 0; col < 7; col++) {
        textBoxes[row][col].set("");
      }
    }
    taskhBoxes.clear();
    taskQueue.getChildren().clear();
  }
}




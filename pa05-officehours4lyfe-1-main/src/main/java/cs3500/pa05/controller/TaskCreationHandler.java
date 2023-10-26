package cs3500.pa05.controller;

import cs3500.pa05.model.Coord;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;


/**
 * a class to handle creating a task
 */

public class TaskCreationHandler extends AbstractHandler implements EventHandler<ActionEvent> {
  private Stage primaryStage;
  private StringProperty[][] textBoxes;
  private Popup popup;
  private VBox taskQueue;
  private List<Day> days;
  private Week week;
  private List<HBox> taskhBoxes;
  private WeeklyOverview weeklyOverview;

  TaskCreationHandler(StringProperty[][] textBoxes, Stage primaryStage, VBox taskQueue,
                      List<Day> days, Week week, List<HBox> taskhBoxes,
                      WeeklyOverview weeklyOverview) {
    super();
    popup = new Popup();
    this.textBoxes = textBoxes;
    this.primaryStage = primaryStage;
    this.taskQueue = taskQueue;
    this.days = days;
    this.week = week;
    this.taskhBoxes = taskhBoxes;
    this.weeklyOverview = weeklyOverview;
  }

  /**
   * handles creating a task by a user
   *
   * @param event the action event
   */

  @Override
  public void handle(ActionEvent event) {
    Dialog<Event> dialog = new Dialog<>();
    dialog.setHeaderText("Add Task");
    dialog.getDialogPane().setMinSize(500, 270);

    VBox vbox = new VBox(10);

    HBox nameBox = new HBox(10);
    Label nameLabel = new Label("Name: ");
    TextField nameResponse = new TextField();
    nameBox.getChildren().addAll(nameLabel, nameResponse);

    HBox descriptionBox = new HBox(10);
    Label descriptionLabel = new Label("Description:");
    TextField descriptionResponse = new TextField();
    descriptionBox.getChildren().addAll(descriptionLabel, descriptionResponse);

    HBox dayBox = new HBox(10);
    Label dayOfWeekLabel = new Label("Day Of The Week:");
    ComboBox comboBox = new ComboBox();
    comboBox.getItems().addAll(DayName.SUNDAY.toString(), DayName.MONDAY.toString(),
        DayName.TUESDAY.toString(), DayName.WEDNESDAY.toString(), DayName.THURSDAY.toString(),
        DayName.FRIDAY.toString(), DayName.SATURDAY.toString());

    dayBox.getChildren().addAll(dayOfWeekLabel, comboBox);

    HBox completeBox = new HBox(10);
    Label completeLabel = new Label("Complete:");
    TextField completeResponse = new TextField("False");
    completeBox.getChildren().addAll(completeLabel, completeResponse);

    vbox.getChildren().addAll(nameBox, dayBox, descriptionBox, completeBox);
    dialog.getDialogPane().setContent(vbox);

    popup.getContent().add(dialog.getDialogPane());
    HBox hbox = new HBox(15);
    hbox.setLayoutX(140);
    hbox.setLayoutY(230);
    Button ok = new Button("OK");
    Button cancel = new Button("CANCEL");
    hbox.getChildren().addAll(ok, cancel);
    ok.setOnAction(e -> {
      popup.hide();
      String name = nameResponse.getText();
      String description = descriptionResponse.getText();
      DayName day = DayName.stringToEnum(comboBox.getValue().toString());
      boolean complete = Boolean.parseBoolean(completeResponse.getText());
      for (Day d : days) {
        if (d.getName().equals(day)) {
          if (week.getMaxTasks() <= d.getTasks().size()) {
            displayErrorAlert("Add Task Error",
                "By adding this task you exceed your set max tasks.");
          }
          displayTask(name, description, day, complete);
          break;
        }
      }
    });
    cancel.setOnAction(e -> popup.hide());
    popup.getContent().add(hbox);
    popup.show(primaryStage);
  }

  /**
   * displays a task on the calendar
   *
   * @param name the name of the task
   *
   * @param description a description of the task
   *
   * @param dayOfWeek the day that the task is o
   *
   * @param complete whether a task is complete or not
   */

  private void displayTask(String name, String description, DayName dayOfWeek, boolean complete) {
    int col = dayOfWeek.getCalIndex();
    for (int row = 1; row < textBoxes.length; row++) {
      if (row > 12) {
        displayErrorAlert("Add Task Error",
            "You have exceeded the maximum amount of tasks you can add to this day.");
        break;
      }
      if (textBoxes[row][col].getValue().equals("")) {
        textBoxes[row][col].set(name + "\n" + description + "\n" + complete);
        Task newTask = updateWeekData(name, description, dayOfWeek, complete, new Coord(row, col));
        //TODO CALL HERE
        updateTaskQueue(name, complete, textBoxes[row][col], newTask);
        //OR HERE
        weeklyOverview.updateWeeklyOverview(week);
        break;
      }
    }
  }

  /**
   * updates the task queue with the new task
   *
   * @param name the name of the task
   *
   * @param complete whether a task is complete or not
   *
   * @param textBox the TextBox of a task
   *
   * @param newTask the new task to add
   */

  private void updateTaskQueue(String name, boolean complete, StringProperty textBox,
                               Task newTask) {
    HBox taskhBox = new HBox(3);
    taskhBox.setMaxHeight(50);
    taskhBox.setMaxWidth(100);
    CheckBox check = new CheckBox();
    TextArea taskInfo = new TextArea(name + "\n" + complete);
    taskInfo.setEditable(false);
    taskhBox.getChildren().addAll(check, taskInfo);
    check.setOnAction(e -> {
      if (check.isSelected()) {
        handleComplete(name, taskhBox, true, textBox, newTask);
      } else {
        handleComplete(name, taskhBox, false, textBox, newTask);
      }
      weeklyOverview.updateWeeklyOverview(week);
    });
    taskhBoxes.add(taskhBox);
    taskQueue.getChildren().add(taskhBox);
  }

  /**
   * handles when a task is completed
   *
   * @param name the name of the task
   *
   * @param status whether the task has been marked completed
   *
   * @param textBox the TextBox of a task
   *
   * @param newTask the new task to add
   */
  private void handleComplete(String name, HBox taskHbox, boolean status, StringProperty textBox,
                              Task newTask) {
    taskHbox.getChildren().remove(taskHbox.getChildren().get(1));
    TextArea trueStatus = new TextArea(name + "\n" + status);
    taskHbox.getChildren().add(trueStatus);
    int completeIndex;
    if (status) {
      completeIndex = textBox.getValue().indexOf("false");
      textBox.set(textBox.getValue().substring(0, completeIndex) + "true");
      newTask.setComplete(true);
    } else {
      completeIndex = textBox.getValue().indexOf("true");
      textBox.set(textBox.getValue().substring(0, completeIndex) + "false");
      newTask.setComplete(false);
    }
  }

  /**
   * updates the week data with the new task
   *
   * @param name the name of the task
   *
   * @param description a description of the task
   *
   * @param dayOfWeek the day that the task is o
   *
   * @param complete whether a task is complete or not
   *
   * @param location the coordinate to place the task on
   *
   * @return the new task
   */

  private Task updateWeekData(String name, String description, DayName dayOfWeek, boolean complete,
                              Coord location) {
    Task newTask = null;
    for (Day day : days) {
      if (day.getName().equals(dayOfWeek)) {
        newTask = new Task(name, description, dayOfWeek, complete, location);
        day.addTask(newTask);
        week.populateTaskQueue(newTask);
        break;
      }
    }
    return newTask;
  }
}

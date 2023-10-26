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
 * represents a class to handle when a task needs to be edited
 */

public class TaskEditingHandler extends AbstractHandler implements EventHandler<ActionEvent> {
  private Stage primaryStage;
  private StringProperty[][] textBoxes;
  private Popup popup;
  private VBox taskQueue;
  private List<Day> days;
  private Week week;
  private List<HBox> taskhBoxes;
  WeeklyOverview weeklyOverview;

  /**
   * initializes the TaskEditingHandler which takes in a 2d array
   * of stringProperty, a stage, a VBox,
   * a list of days, a week object, a list of HBox's and a weeklyOverview object
   *
   * @param textBoxes value binded to the gridPane
   *
   * @param primaryStage the scene the stage is loaded on
   *
   * @param taskQueue the VBox representing the taskQueue
   *
   * @param days list of days in a week object
   *
   * @param week a week object
   *
   * @param taskhBoxes a list of hBoxes (taskQueue)
   *
   * @param weeklyOverview displays the week overview (stats)
   */
  public TaskEditingHandler(StringProperty[][] textBoxes, Stage primaryStage, VBox taskQueue,
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
   * handles the event of editing a task by a user
   *
   * @param event the action event
   */

  @Override
  public void handle(ActionEvent event) {
    Dialog<Event> dialog = new Dialog<>();
    dialog.setHeaderText("Edit Task");
    dialog.getDialogPane().setMinSize(500, 270);


    VBox vbox = new VBox(10);

    HBox dayBox = new HBox(10);
    Label dayOfWeekLabel = new Label("Day Of The Week:");
    ComboBox comboBox = new ComboBox();
    comboBox.getItems().addAll(DayName.SUNDAY.toString(), DayName.MONDAY.toString(),
        DayName.TUESDAY.toString(), DayName.WEDNESDAY.toString(), DayName.THURSDAY.toString(),
        DayName.FRIDAY.toString(), DayName.SATURDAY.toString());

    HBox rowBox = new HBox(10);
    Label rowLabel = new Label("Row from 1 - 12:");
    TextField rowResponse = new TextField();
    rowBox.getChildren().addAll(rowLabel, rowResponse);

    dayBox.getChildren().addAll(dayOfWeekLabel, comboBox);

    vbox.getChildren().addAll(dayBox, rowBox);
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
      DayName day = DayName.stringToEnum(comboBox.getValue().toString());
      int colIndex = day.getCalIndex();
      int rowInfo = Integer.parseInt(rowResponse.getText());
      if (textBoxes[rowInfo][colIndex].getValue().equals("")) {
        displayErrorAlert("Edit Error",
            "You can not edit this task because there is no task to edit at this location!");
      } else {
        for (Day d : week.getDays()) {
          for (Task selectedTask : d.getTasks()) {
            if (selectedTask.getLocation().getRow() == rowInfo
                && selectedTask.getLocation().getCol() == colIndex) {
              allowEditTask(rowInfo, selectedTask);
              break;
            }
          }
        }
      }
    });
    cancel.setOnAction(e -> popup.hide());
    popup.getContent().add(hbox);
    popup.show(primaryStage);
  }

  /**
   * allows for a task to be edited
   *
   * @param rowInfo the index of the row an task is in
   *
   * @param selectedTask the task to be edited
   */

  private void allowEditTask(int rowInfo, Task selectedTask) {
    Dialog<Event> dialog = new Dialog<>();
    dialog.setHeaderText("Edit Task");
    dialog.getDialogPane().setMinSize(500, 270);

    VBox vbox = new VBox(10);

    HBox nameBox = new HBox(10);
    Label nameLabel = new Label("Name: ");
    TextField nameResponse = new TextField(selectedTask.getName());
    nameBox.getChildren().addAll(nameLabel, nameResponse);

    HBox descriptionBox = new HBox(10);
    Label descriptionLabel = new Label("Description:");
    TextField descriptionResponse = new TextField(selectedTask.getDescription());
    descriptionBox.getChildren().addAll(descriptionLabel, descriptionResponse);

    HBox dayBox = new HBox(10);
    Label dayOfWeekLabel = new Label("Day Of The Week:");
    ComboBox comboBox = new ComboBox();
    comboBox.getItems().addAll(DayName.SUNDAY.toString(), DayName.MONDAY.toString(),
        DayName.TUESDAY.toString(), DayName.WEDNESDAY.toString(), DayName.THURSDAY.toString(),
        DayName.FRIDAY.toString(), DayName.SATURDAY.toString());
    comboBox.setValue(selectedTask.getDayOfWeek().toString());

    dayBox.getChildren().addAll(dayOfWeekLabel, comboBox);

    HBox completeBox = new HBox(10);
    Label completeLabel = new Label("Complete:");
    TextField completeResponse = new TextField(String.valueOf(selectedTask.getComplete()));
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

      boolean editName = !selectedTask.getName().equals(name);
      boolean editComplete = selectedTask.getComplete() != complete;

      int oldRow = selectedTask.getLocation().getRow();
      int oldCol = selectedTask.getLocation().getCol();
      if (selectedTask.getDayOfWeek().equals(day)) {
        displayEditedTask(name, description, day, complete, rowInfo, selectedTask);
      } else {
        textBoxes[rowInfo][selectedTask.getDayOfWeek().getCalIndex()].set("");
        for (Day d : days) {
          if (d.getName().equals(selectedTask.getDayOfWeek())) {
            d.removeTask(selectedTask);
          }
        }
        displayOnDifferentDay(name, description, day, complete, selectedTask, days);
      }
      if (editName || editComplete) {
        displayEditedTaskQueue(name, complete, selectedTask,
            textBoxes[selectedTask.getLocation().getRow()][selectedTask.getLocation().getCol()],
            oldRow, oldCol);
        //TODO CALL HERE
        weeklyOverview.updateWeeklyOverview(week);

      }
    });
    cancel.setOnAction(e -> popup.hide());
    popup.getContent().add(hbox);
    popup.show(primaryStage);
  }

  /**
   * displays the task with its new details
   *
   * @param name the task's name
   *
   * @param description the tak,'s description
   *
   * @param day the day the task is on
   *
   * @param complete whether a task is complete or not
   *
   * @param rowInfo the index of the row of the task
   *
   * @param t the task to be displayed
   */

  private void displayEditedTask(String name, String description, DayName day, boolean complete,
                                 int rowInfo, Task t) {
    textBoxes[rowInfo][day.getCalIndex()].set(name + "\n" + description + "\n" + complete);
    t.setTask(name, description, day, complete, t.getLocation());
  }

  /**
   * displays the task with its new details on a different day
   *
   * @param name the task's name
   *
   * @param description the task's description
   *
   * @param day the day the task is on
   *
   * @param complete whether a task is complete or not
   *
   * @param t the task to be displayed
   *
   * @param days the list of days in the week
   */

  private void displayOnDifferentDay(String name, String description, DayName day, boolean complete,
                                     Task t, List<Day> days) {
    for (int row = 1; row < textBoxes.length; row++) {
      if (textBoxes[row][day.getCalIndex()].getValue().equals("")) {
        textBoxes[row][day.getCalIndex()].set(name + "\n" + description + "\n" + complete);
        t.setTask(name, description, day, complete, new Coord(row, day.getCalIndex()));
        for (Day d : days) {
          if (d.getName().equals(t.getDayOfWeek())) {
            d.addTask(t);
            break;
          }
        }
        break;
      }
    }
  }

  /**
   * displays the edited task queue with the new task
   *
   * @param name the task's name
   *
   * @param complete whether a task is complete or not
   *
   * @param t the task to be displayed in the queue
   *
   * @param textBox the content of the task in a TextBox
   *
   * @param oldRow the original row index of the task
   *
   * @param oldCol the original column index of the task
   */

  private void displayEditedTaskQueue(String name, boolean complete, Task t, StringProperty textBox,
                                      int oldRow, int oldCol) {
    for (int index = 0; index < week.getTaskQueue().size(); index++) {
      if (week.getTaskQueue().get(index).getLocation().getRow() == t.getLocation().getRow()
          && week.getTaskQueue().get(index).getLocation().getCol() == t.getLocation().getCol()) {
        taskQueue.getChildren().remove(index);
        week.getTaskQueue().remove(index);
        week.getTaskQueue().add(index, t);
        HBox taskhBox = new HBox(3);
        taskhBox.setMaxHeight(50);
        taskhBox.setMaxWidth(100);
        CheckBox check = new CheckBox();
        if (complete) {
          check.setSelected(true);
        }
        TextArea taskInfo = new TextArea(name + "\n" + complete);
        taskInfo.setEditable(false);
        taskhBox.getChildren().addAll(check, taskInfo);
        taskhBoxes.remove(index);
        taskhBoxes.add(index, taskhBox);
        check.setOnAction(e -> {
          if (check.isSelected()) {
            handleComplete(name, taskhBox, true, textBox, t);
          } else {
            handleComplete(name, taskhBox, false, textBox, t);
          }
          weeklyOverview.updateWeeklyOverview(week);
        });
        taskQueue.getChildren().add(index, taskhBox);
        break;
      }
    }

  }

  /**
   * handles when a task is completed
   *
   * @param name the name of the task
   *
   * @param taskhBox the Hbox of a task
   *
   * @param status whether the task has been marked completed
   *
   * @param textBox the TextBox of a task
   *
   * @param newTask the new task to add
   */

  private void handleComplete(String name, HBox taskhBox, boolean status, StringProperty textBox,
                              Task newTask) {
    taskhBox.getChildren().remove(taskhBox.getChildren().get(1));
    TextArea trueStatus = new TextArea(name + "\n" + status);
    taskhBox.getChildren().add(trueStatus);
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

}

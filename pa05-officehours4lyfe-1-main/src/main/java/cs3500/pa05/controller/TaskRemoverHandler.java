package cs3500.pa05.controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * a class to represent removing a task
 */

public class TaskRemoverHandler extends AbstractHandler implements EventHandler<ActionEvent> {
  private Stage primaryStage;
  private StringProperty[][] textBoxes;
  private Popup popup;
  private VBox taskQueue;
  private List<Day> days;
  private Week week;
  private List<HBox> taskhBoxes;
  WeeklyOverview weeklyOverview;

  /**
   * initializes a string property array, a stage, a vbox, a list of days,
   * a week, a list of hBoxes, and
   * a weeklyOverview to make a TaskRemoverHandler object
   *
   * @param textBoxes the value binded to the grid pane
   *
   * @param primaryStage the stage the scene is loaded on to
   *
   * @param taskQueue the VBox the taskQueue is visially stored in
   *
   * @param days the list of days of a Week object
   *
   * @param week a week object
   *
   * @param taskhBoxes a list of hBoxes
   *
   * @param weeklyOverview an object that updates the week Overview feature
   */
  public TaskRemoverHandler(StringProperty[][] textBoxes, Stage primaryStage, VBox taskQueue,
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
   * handles the removal of a task by a user
   *
   * @param event the action event
   */

  @Override
  public void handle(ActionEvent event) {
    if (!week.getAllTasks().isEmpty()) {
      Dialog<Event> dialog = new Dialog<>();
      dialog.setHeaderText("Remove Task");
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
        undisplayTask(rowInfo, colIndex);
      });
      cancel.setOnAction(e -> popup.hide());
      popup.getContent().add(hbox);
      popup.show(primaryStage);
    } else {
      displayErrorAlert("Remove Task Error", "You can not remove "
          + "this task because there is no task to "
          + "remove!");
    }
  }

  /**
   * undisplays a task on the journal at a specific location
   *
   * @param row the row index of the task
   *
   * @param col the column index of the task
   */

  private void undisplayTask(int row, int col) {
    // remove task from textBoxes row/col
    textBoxes[row][col].set("");
    // update week data (with removed task)
    for (int index = 0; index < week.getTaskQueue().size(); index++) {
      if (week.getTaskQueue().get(index).getLocation().getRow() == row
          && week.getTaskQueue().get(index).getLocation().getCol() == col) {
        removeFromWeekData(week.getTaskQueue().get(index), index);
        weeklyOverview.updateWeeklyOverview(week);
        break;
      }
    }
  }


  /**
   * removes a task from the week's data
   *
   * @param t the task to be removed
   *
   * @param taskQueueIndex the index of where the task is in queue
   */

  private void removeFromWeekData(Task t, int taskQueueIndex) {
    for (Day day : days) {
      if (day.getName().equals(t.getDayOfWeek())) {
        day.removeTask(t);
        week.unpopulateTaskQueue(t);
        removeFromTaskQueue(taskQueueIndex);
        break;
      }
    }
  }

  /**
   * removes a task from the task queue
   *
   * @param taskRow the row to remove the task from in the queue
   */

  private void removeFromTaskQueue(int taskRow) {
    taskQueue.getChildren().remove(taskhBoxes.get(taskRow));
    taskhBoxes.remove(taskRow);
  }


}
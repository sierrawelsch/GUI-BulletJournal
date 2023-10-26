package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
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
 * a class to represent removing an event
 */

public class EventRemoverHandler extends AbstractHandler implements EventHandler<ActionEvent> {
  private Stage primaryStage;
  private StringProperty[][] textBoxes;
  private Popup popup;
  private List<Day> days;
  private Week week;
  private WeeklyOverview weeklyOverview;

  /**
   * initializes a 2d array of stringProperty, a stage, a list of days,
   * a week object, and a WeeklyOverview object
   * to make a EventRemoverHandler
   *
   * @param textBoxes value binded to the grid pane
   *
   * @param primaryStage stage the scene is loaded on to
   *
   * @param days a list of days in a week
   *
   * @param week a week object
   *
   * @param weeklyOverview an object that updates the week Overview feature
   */
  public EventRemoverHandler(StringProperty[][] textBoxes, Stage primaryStage, List<Day> days,
                             Week week, WeeklyOverview weeklyOverview) {
    super();
    popup = new Popup();
    this.textBoxes = textBoxes;
    this.primaryStage = primaryStage;
    this.days = days;
    this.week = week;
    this.weeklyOverview = weeklyOverview;
  }

  /**
   * handles the removal of an event by a user
   *
   * @param event the action event
   */

  @Override
  public void handle(ActionEvent event) {
    if (!week.getAllEvents().isEmpty()) {
      Dialog<Event> dialog = new Dialog<>();
      dialog.setHeaderText("Remove Event");
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
        undisplayEvent(rowInfo, colIndex);

      });
      cancel.setOnAction(e -> popup.hide());
      popup.getContent().add(hbox);
      popup.show(primaryStage);
    } else {
      displayErrorAlert("Remove Event Error",
          "Cannot remove an event because there are no events to remove!");
    }
  }

  /**
   * undisplays an event on the journal at a specific location
   *
   * @param row the row index of the event
   *
   * @param col the column index of the event
   */

  private void undisplayEvent(int row, int col) {
    // remove task from textBoxes row/col
    textBoxes[row][col].set("");
    // update week data (with removed task)
    for (Event e : week.getDays().get(col).getEvents()) {
      if (e.getLocation().getRow() == row && e.getLocation().getCol() == col) {
        removeFromWeekData(e);
        weeklyOverview.updateWeeklyOverview(week);
        break;

      }
    }
  }

  /**
   * removes an event from the week's data
   *
   * @param e the event to be removed
   */

  private void removeFromWeekData(Event e) {
    for (Day day : days) {
      if (day.getName().equals(e.getDayOfWeek())) {
        day.removeEvent(e);
      }
    }
  }
}
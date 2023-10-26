package cs3500.pa05.controller;

import cs3500.pa05.model.Coord;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.util.ArrayList;
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
 * represents a class to handle when an event needs to be edited
 */

public class EventEditingHandler extends AbstractHandler implements EventHandler<ActionEvent> {
  private Popup popup;
  private StringProperty[][] textBoxes;
  private Stage primaryStage;
  private List<Day> days;


  /**
   * initializes a stringProperty array a stage and a list of days to create an
   * EventEditingHandler object
   *
   * @param textBoxes string value binded to the gridPane
   *
   * @param primaryStage the stage the scene is loaded on
   *
   * @param days the list of days in a week
   */
  public EventEditingHandler(StringProperty[][] textBoxes, Stage primaryStage, List<Day> days) {
    this.popup = new Popup();
    this.textBoxes = textBoxes;
    this.primaryStage = primaryStage;
    this.days = days;
  }

  /**
   * handles the event of editing an event by a user
   *
   * @param event the action event
   */

  @Override
  public void handle(ActionEvent event) {
    Dialog<Event> dialog = new Dialog<>();
    dialog.setHeaderText("Edit Event");
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
            "You can not edit this event because there is no event to edit at this location!");
      } else {
        for (Day d : days) {
          for (Event selectedEvent : d.getEvents()) {
            if (selectedEvent.getLocation().getRow() == rowInfo
                && selectedEvent.getLocation().getCol() == colIndex) {
              allowEditEvent(rowInfo, selectedEvent);
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
   * allows for an event to be edited
   *
   * @param rowInfo the index of the row an event is in
   *
   * @param selectedEvent the event to be edited
   */

  private void allowEditEvent(int rowInfo, Event selectedEvent) {
    Dialog<Event> dialog = new Dialog<>();
    dialog.setHeaderText("Edit Event");
    dialog.getDialogPane().setMinSize(500, 270);

    VBox vbox = new VBox(10);

    HBox nameBox = new HBox(10);
    Label nameLabel = new Label("Name: ");
    TextField nameResponse = new TextField(selectedEvent.getName());
    nameBox.getChildren().addAll(nameLabel, nameResponse);

    HBox descriptionBox = new HBox(10);
    Label descriptionLabel = new Label("Description:");
    TextField descriptionResponse = new TextField(selectedEvent.getDescription());
    descriptionBox.getChildren().addAll(descriptionLabel, descriptionResponse);

    HBox dayBox = new HBox(10);
    Label dayOfWeekLabel = new Label("Day Of The Week:");
    ComboBox comboBox = new ComboBox();
    comboBox.getItems().addAll(DayName.SUNDAY.toString(), DayName.MONDAY.toString(),
        DayName.TUESDAY.toString(), DayName.WEDNESDAY.toString(), DayName.THURSDAY.toString(),
        DayName.FRIDAY.toString(), DayName.SATURDAY.toString());
    comboBox.setValue(selectedEvent.getDayOfWeek().toString());

    dayBox.getChildren().addAll(dayOfWeekLabel, comboBox);

    HBox startTimeBox = new HBox(10);
    Label startTimeLabel = new Label("Start Time:");
    TextField startTimeResponse = new TextField(String.valueOf(selectedEvent.getStartTime()));
    startTimeBox.getChildren().addAll(startTimeLabel, startTimeResponse);

    HBox durationBox = new HBox(10);
    Label durationLabel = new Label("Duration:");
    TextField durationResponse = new TextField(String.valueOf(selectedEvent.getDuration()));
    durationBox.getChildren().addAll(durationLabel, durationResponse);


    vbox.getChildren().addAll(nameBox, dayBox, descriptionBox, startTimeBox, durationBox);
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
      String startTime = startTimeResponse.getText();
      int duration = Integer.parseInt(durationResponse.getText());

      if (selectedEvent.getDayOfWeek().equals(day)) {
        displayEditedEvent(name, description, day, startTime, duration, rowInfo, selectedEvent);
      } else {
        textBoxes[rowInfo][selectedEvent.getDayOfWeek().getCalIndex()].set("");
        for (Day d : days) {
          if (d.getName().equals(selectedEvent.getDayOfWeek())) {
            d.removeEvent(selectedEvent);
          }
        }
        displayOnDifferentDay(name, description, day, startTime, duration, selectedEvent, days);
      }
    });
    cancel.setOnAction(e -> popup.hide());
    popup.getContent().add(hbox);
    popup.show(primaryStage);
  }

  /**
   * displays the event with its new details
   *
   * @param name the event's name
   *
   * @param description the event's description
   *
   * @param day the day the event is on
   *
   * @param startTime when the event begins
   *
   * @param duration how long the event is
   *
   * @param rowInfo the index of the row of the event
   *
   * @param e the event to be displayed
   */

  private void displayEditedEvent(String name, String description, DayName day, String startTime,
                                  int duration,
                                  int rowInfo, Event e) {
    textBoxes[rowInfo][day.getCalIndex()].set(
        name + "\n" + description + "\n" + startTime + "\n" + duration);
    e.setEvent(name, description, day, startTime, duration, e.getLocation());
  }

  /**
   * displays an event on a different day given its new details
   *
   * @param name the event's name
   *
   * @param description the event's description
   *
   * @param day the day the event is on
   *
   * @param startTime when the event begins
   *
   * @param duration how long the event is
   *
   * @param e the event to be displayed on a different day
   *
   * @param days the list of days in the week
   */

  private void displayOnDifferentDay(String name, String description, DayName day, String startTime,
                                     int duration,
                                     Event e, List<Day> days) {
    for (int row = 1; row < textBoxes.length; row++) {
      if (textBoxes[row][day.getCalIndex()].getValue().equals("")) {
        textBoxes[row][day.getCalIndex()].set(
            name + "\n" + description + "\n" + startTime + "\n" + duration);
        e.setEvent(name, description, day, startTime, duration, new Coord(row, day.getCalIndex()));
        for (Day d : days) {
          if (d.getName().equals(e.getDayOfWeek())) {
            d.addEvent(e);
            break;
          }
        }
        break;
      }
    }
  }

}

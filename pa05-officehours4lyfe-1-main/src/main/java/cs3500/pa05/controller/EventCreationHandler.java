package cs3500.pa05.controller;


import cs3500.pa05.model.Coord;
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
 * a class to handle creating a new event on the calendar
 */
public class EventCreationHandler extends AbstractHandler implements EventHandler<ActionEvent> {

  private Popup popup;
  private StringProperty[][] textBoxes;
  private Stage primaryStage;
  private Week weekContent;
  private List<Day> days;
  private WeeklyOverview weeklyOverview;

  /**
   * initializes a 2d array of stringProperty, the stage, a list of days,
   * a week object, and a weeklyOverview object
   *
   * @param textBoxes value binded to the gridPane
   *
   * @param primaryStage where the scene will be loaded
   *
   * @param days a list of days in a week object
   *
   * @param weekContent a week object
   *
   * @param weeklyOverview an object that updates the week Overview feature
   */
  public EventCreationHandler(StringProperty[][] textBoxes, Stage primaryStage, List<Day> days,
                              Week weekContent, WeeklyOverview weeklyOverview) {
    super();
    this.popup = new Popup();
    this.textBoxes = textBoxes;
    this.primaryStage = primaryStage;
    this.weekContent = weekContent;
    this.days = days;
    this.weeklyOverview = weeklyOverview;
  }

  /**
   * handles the creation of a new event by a user
   *
   * @param event the action event
   */
  public void handle(ActionEvent event) {
    Dialog<Event> dialog = new Dialog<>();
    dialog.setHeaderText("Add Event");
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


    HBox startTimeBox = new HBox(10);
    Label startTimeLabel = new Label("Start Time:");
    TextField startTimeResponse = new TextField();
    startTimeBox.getChildren().addAll(startTimeLabel, startTimeResponse);


    HBox durationBox = new HBox(10);
    Label durationLabel = new Label("Duration:");
    TextField durationResponse = new TextField();
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
      int duration;
      try {
        duration = Integer.parseInt(durationResponse.getText());
        for (Day d : days) {
          if (d.getName().equals(day)) {
            if (weekContent.getMaxEvents() <= d.getEvents().size()) {
              displayErrorAlert("Add Event Error",
                  "By adding this task you exceed your set max events.");
            }
            displayEvent(name, description, day, startTime, duration);
            break;
          }
        }
      } catch (NumberFormatException n) {
        displayErrorAlert("Add Event Error", "The duration of this event must be an integer");
      }
    });
    cancel.setOnAction(e -> popup.hide());
    popup.getContent().add(hbox);
    popup.show(primaryStage);
  }


  /**
   * displays an event on the calendar
   *
   * @param name the name of the event
   *
   * @param description a description of the event
   *
   * @param dayOfWeek the day that the event is on
   *
   * @param startTime when the event starts
   *
   * @param duration how long the event lasts
   */

  private void displayEvent(String name, String description, DayName dayOfWeek, String startTime,
                            int duration) {
    int col = dayOfWeek.getCalIndex();
    for (int row = 1; row < textBoxes.length; row++) {
      if (row > 12) {
        displayErrorAlert("Add Event Error",
            "You have exceeded the maximum amount of events you can add to this day.");
        break;
      }
      if (textBoxes[row][col].getValue().equals("")) {
        textBoxes[row][col].set(name + "\n" + description + "\n" + startTime + "\n" + duration);
        updateWeekData(name, description, dayOfWeek, startTime, duration, new Coord(row, col));
        weeklyOverview.updateWeeklyOverview(weekContent);
        break;
      }
    }
  }

  /**
   * updates the week's data with the new event
   *
   * @param name the name of the event
   *
   * @param description a description of the event
   *
   * @param dayOfWeek the day that the event is on
   *
   * @param startTime when the event starts
   *
   * @param duration how long the event lasts
   */

  private void updateWeekData(String name, String description, DayName dayOfWeek, String startTime,
                              int duration, Coord location) {
    for (Day day : days) {
      if (day.getName().equals(dayOfWeek)) {
        day.addEvent(new Event(name, description, dayOfWeek, startTime, duration, location));
      }
    }
  }

}

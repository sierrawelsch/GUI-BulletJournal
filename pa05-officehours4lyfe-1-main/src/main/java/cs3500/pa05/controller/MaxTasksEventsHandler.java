package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * handles the maximum amount of tasks or events a week can hold
 */

public class MaxTasksEventsHandler extends AbstractHandler implements EventHandler<ActionEvent> {
  private TextField maxEventsTextField;
  private TextField maxTasksTextField;
  private Week week;

  /**
   * initializes a MaxTasksEventsHandler that takes in a maxEventsTextField, maxTasksTextField,
   * and a week object
   *
   * @param maxEventsTextField field that stores maxEvents
   *
   * @param maxTasksTextField field that stores maxTasks
   *
   * @param week week object
   */
  public MaxTasksEventsHandler(TextField maxEventsTextField, TextField maxTasksTextField,
                               Week week) {
    super();
    this.maxEventsTextField = maxEventsTextField;
    this.maxTasksTextField = maxTasksTextField;
    this.week = week;
  }

  /**
   * handles when a user wants to set a maximum number of tasks or events in a week
   *
   * @param event the action event
   */

  @Override
  public void handle(ActionEvent event) {
    int maxEvents;
    int maxTasks;

    //if neither are empty
    if (!maxEventsTextField.getText().isEmpty() && !maxTasksTextField.getText().isEmpty()) {
      maxEvents = Integer.parseInt(maxEventsTextField.getText());
      maxTasks = Integer.parseInt(maxTasksTextField.getText());

      if (maxEvents + maxTasks <= 12) {
        week.setMaxEvents(maxEvents);
        week.setMaxTasks(maxTasks);
      } else {
        displayErrorAlert("Max Error",
            "You can not set your maxEvents "
                + "and maxTasks to be greater than the number of rows provided!");
      }
    } else if (maxEventsTextField.getText().isEmpty() && !maxTasksTextField.getText().isEmpty()) {
      week.setMaxEvents(13);
      maxTasks = Integer.parseInt(maxTasksTextField.getText());

      if (maxTasks <= 12) {
        week.setMaxTasks(maxTasks);
      } else {
        displayErrorAlert("Max Task Error",
            "You can not set your maxTasks to be greater than the number of rows provided!");
      }
    } else if (!maxEventsTextField.getText().isEmpty() && maxTasksTextField.getText().isEmpty()) {
      week.setMaxTasks(13);
      maxEvents = Integer.parseInt(maxEventsTextField.getText());
      if (maxEvents <= 12) {
        week.setMaxEvents(maxEvents);
      } else {
        displayErrorAlert("Max Event Error",
            "You can not set your maxEvents to be greater than the number of rows provided!");
      }
    } else {
      week.setMaxTasks(13);
      week.setMaxEvents(13);
    }
  }

}

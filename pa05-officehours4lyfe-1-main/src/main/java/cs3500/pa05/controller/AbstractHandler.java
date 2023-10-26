package cs3500.pa05.controller;

import javafx.scene.control.Alert;

/**
 * an abstract class for handlers
 */
public abstract class AbstractHandler {
  AbstractHandler() {}

  /**
   * displays the error alert dialog
   *
   * @param title   the title of the error alert
   * @param message the error message to display to user
   */
  protected void displayErrorAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}

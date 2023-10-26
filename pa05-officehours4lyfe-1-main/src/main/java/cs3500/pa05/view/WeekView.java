package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * represents the Week view of the application (display of the fxml)
 */

public class WeekView implements JournalView {
  private FXMLLoader loader;

  /**
   * initilaizes the week view of the application by having a controller object passed in
   *
   * @param controller take control of what the view is loading
   */
  public WeekView(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("journal.fxml"));
    this.loader.setController(controller);
  }

  /**
   * loads the scene of the application
   *
   * @return the scene of the application
   *
   * @throws IllegalStateException if the layout is unable to be loaded
   */

  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}

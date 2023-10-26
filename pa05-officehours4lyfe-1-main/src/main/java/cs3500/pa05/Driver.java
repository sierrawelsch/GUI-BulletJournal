package cs3500.pa05;

import static javafx.application.Application.launch;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.ControllerImpl;
import cs3500.pa05.view.JournalView;
import cs3500.pa05.view.SaveFile;
import cs3500.pa05.view.WeekView;
import java.io.IOException;
import java.nio.file.Path;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents the entry point for our program - bullet journal
 */

public class Driver extends Application {
  /**
   * entry point of our program
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * passes the stage into the controller, and the controller
   * into the view to load the scene and give the
   * controller control of the scene
   *
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   */

  public void start(Stage stage) {
    Controller controller = new ControllerImpl(stage);
    JournalView viewer = new WeekView(controller);

    try {
      // load and place the view's scene onto the stage
      stage.setScene(viewer.load());
      controller.run();
      // render the stage
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }
}

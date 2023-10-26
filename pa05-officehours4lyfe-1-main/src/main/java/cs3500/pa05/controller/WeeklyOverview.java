package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.text.DecimalFormat;
import javafx.scene.control.TextField;

/**
 * represents the weekly overview for the application
 */

public class WeeklyOverview {
  private int totalTasks;
  private int totalEvents;
  private double tasksComplete;
  private TextField totalTasksTextField;
  private TextField totalEventsTextField;
  private TextField totalTasksCompleteTextField;


  /**
   * initializes a weeklyOverview object by passing in the textFields that represent the stats
   *
   * @param totalTasksTextField the textField for total Tasks
   *
   * @param totalEventsTextField the textField for total Events
   *
   * @param totalTasksCompleteTextField the textField for total Tasks complete
   */
  public WeeklyOverview(TextField totalTasksTextField, TextField totalEventsTextField,
                        TextField totalTasksCompleteTextField) {
    this.totalTasks = 0;
    this.totalEvents = 0;
    this.tasksComplete = 0;
    this.totalTasksTextField = totalTasksTextField;
    this.totalEventsTextField = totalEventsTextField;
    this.totalTasksCompleteTextField = totalTasksCompleteTextField;
  }

  /**
   * updates the weekly overview
   *
   * @param currentWeek the current week
   */

  public void updateWeeklyOverview(Week currentWeek) {
    this.totalTasks = currentWeek.getAllTasks().size();
    this.totalEvents = currentWeek.getAllEvents().size();

    tasksComplete = 0;
    for (Day d : currentWeek.getDays()) {
      for (Task t : d.getTasks()) {
        if (t.getComplete()) {
          tasksComplete++;
        }
      }
    }
    totalTasksTextField.setText("" + this.totalTasks);

    totalEventsTextField.setText("" + this.totalEvents);
    double tasksCompletePercent = 0;
    if (totalTasks != 0) {
      tasksCompletePercent = (tasksComplete / totalTasks) * 100;
    }
    DecimalFormat df = new DecimalFormat("####0.00");
    totalTasksCompleteTextField.setText("" + df.format(tasksCompletePercent) + "%");
  }
}

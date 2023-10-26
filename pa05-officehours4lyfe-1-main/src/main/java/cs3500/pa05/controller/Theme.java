package cs3500.pa05.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * represents the themes displayed in the application
 */

public class Theme {
  private AnchorPane anchorPane;
  private Label javaJournalLabel;
  private GridPane weekView;

  private Label taskQueueLabel;
  private Button saveToButton;
  private Button addTaskButton;
  private Button addEventButton;
  private Button editTaskButton;
  private Button editEventButton;
  private Button removeTaskButton;
  private Button removeEventButton;
  private Button openFileButton;
  private Button updateMaxButton;

  private Button modernThemeButton;

  private Button pastelThemeButton;

  private Button retroThemeButton;
  private Label quotesNotesLabel;
  private TextArea quotesNotesTextArea;
  private Stage stage;
  private Label weeklyOverviewLabel;
  private TextField totalTasksTextField;
  private TextField totalEventsTextField;
  private TextField totalTasksCompleteTextField;
  private TextField maxTasksTextField;
  private TextField maxEventsTextField;
  private List<Button> buttonList;
  private List<TextField> textFieldList;

  /**
   * initializes all the GUI components that will be changed in appearance based on the theme
   *
   * @param anchorPane the anchorPane that holds everything together
   *
   * @param javaJournalLabel the label that displays the title of the journal
   *
   * @param weekView the gridPane that is used to display tasks/events on the days of the week
   *
   * @param saveToButton the button that saves the file
   *
   * @param addTaskButton the button that adds a task
   *
   * @param addEventButton the button that adds an event
   *
   * @param editTaskButton the button that edits the fields of a task
   *
   * @param editEventButton the button that edits the fields of an event
   *
   * @param removeTaskButton the button that removes a task
   *
   * @param removeEventButton the button that removes an event
   *
   * @param openFileButton the button that opens a file
   *
   * @param updateMaxButton the button that updates the maxTasks and maxEvents
   *
   * @param modernThemeButton the button that displays the Modern Theme
   *
   * @param pastelThemeButton the button that displays the Pastel Theme
   *
   * @param retroThemeButton the button that displays the Retro Theme
   *
   * @param quotesNotesLabel the label that says quotes and notes
   *
   * @param quotesNotesTextArea the TextArea that stores the user's quotes and notes
   *
   * @param stage the stage that the scene is loaded onto
   *
   * @param taskQueueLabel the taskQueue label that says task Queue
   *
   * @param totalTasksTextField the TextField that represents the total
   *                            tasks shows in the weekly view
   *
   * @param totalEventsTextField the TextField that represents the total events in the weekly view
   *
   * @param totalTasksCompleteTextField the TextField that represents the total completed
   *                                    tasks in the weekly view
   *
   * @param maxTasksTextField the TextField that represents where
   *                          the max tasks are shown and updated
   *
   * @param maxEventsTextField the TextField that represents where the max
   *                           events are shown and updated
   */
  public Theme(AnchorPane anchorPane, Label javaJournalLabel, GridPane weekView,
               Button saveToButton, Button addTaskButton, Button addEventButton,
               Button editTaskButton, Button editEventButton, Button removeTaskButton,
               Button removeEventButton, Button openFileButton, Button updateMaxButton,
               Button modernThemeButton, Button pastelThemeButton, Button retroThemeButton,
               Label quotesNotesLabel, TextArea quotesNotesTextArea, Stage stage,
               Label taskQueueLabel, Label weeklyOverviewLabel, TextField totalTasksTextField,
               TextField totalEventsTextField, TextField totalTasksCompleteTextField,
               TextField maxTasksTextField, TextField maxEventsTextField) {
    this.anchorPane = anchorPane;
    this.javaJournalLabel = javaJournalLabel;
    this.weekView = weekView;
    this.taskQueueLabel = taskQueueLabel;
    this.saveToButton = saveToButton;
    this.addTaskButton = addTaskButton;
    this.addEventButton = addEventButton;
    this.editTaskButton = editTaskButton;
    this.editEventButton = editEventButton;
    this.removeTaskButton = removeTaskButton;
    this.removeEventButton = removeEventButton;
    this.modernThemeButton = modernThemeButton;
    this.pastelThemeButton = pastelThemeButton;
    this.retroThemeButton = retroThemeButton;
    this.openFileButton = openFileButton;
    this.updateMaxButton = updateMaxButton;
    this.quotesNotesLabel = quotesNotesLabel;
    this.quotesNotesTextArea = quotesNotesTextArea;
    this.stage = stage;
    this.weeklyOverviewLabel = weeklyOverviewLabel;
    this.totalTasksTextField = totalTasksTextField;
    this.totalEventsTextField = totalEventsTextField;
    this.totalTasksCompleteTextField = totalTasksCompleteTextField;
    this.maxTasksTextField = maxTasksTextField;
    this.maxEventsTextField = maxEventsTextField;

    this.buttonList = new ArrayList<>();
    this.buttonList.add(this.saveToButton);
    this.buttonList.add(this.addTaskButton);
    this.buttonList.add(this.addEventButton);
    this.buttonList.add(this.editTaskButton);
    this.buttonList.add(this.editEventButton);
    this.buttonList.add(this.removeTaskButton);
    this.buttonList.add(this.removeEventButton);
    this.buttonList.add(this.openFileButton);
    this.buttonList.add(this.updateMaxButton);

    this.textFieldList = new ArrayList<>();
    this.textFieldList.add(this.totalTasksTextField);
    this.textFieldList.add(this.totalEventsTextField);
    this.textFieldList.add(this.totalTasksCompleteTextField);
    this.textFieldList.add(this.maxTasksTextField);
    this.textFieldList.add(this.maxEventsTextField);
  }

  /**
   * sets the application theme to our modern theme
   */
  public void setModernTheme() {
    anchorPane.setStyle("-fx-background-color: " + "#dbd5b4" + ";");
    javaJournalLabel.setStyle("-fx-text-fill: " + "#8B4513" + ";");
    javaJournalLabel.setFont(Font.font("Broadway", 40));
    weekView.setStyle("-fx-background-color: " + "#ceab7e" + ";");
    taskQueueLabel.setStyle("-fx-text-fill: " + "#000000" + ";");
    taskQueueLabel.setStyle("-fx-background-color: " + "#a69f80" + ";");
    taskQueueLabel.setFont(Font.font("Futura", 10));
    buttonStyle("#b3a694");
    textFieldStyle("#ceab7e", "#0e100e", "#0e100e", "#0e100e");
    quotesNotesLabel.setFont(Font.font("Perpetua", 18));
    quotesNotesLabel.setStyle("-fx-text-fill: " + "#31240c" + ";");
    quotesNotesTextArea.setStyle("-fx-control-inner-background:#ceab7e; "
        + "-fx-font-family: Consolas; -fx-highlight-fill: #0e100e; "
        + "-fx-highlight-text-fill: #0e100e; -fx-text-fill: #0e100e; ");
    weeklyOverviewLabel.setFont(Font.font("Perpetua", 18));
    weeklyOverviewLabel.setStyle("-fx-text-fill: " + "#31240c" + ";");
    weekView.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
    stage.getIcons().clear();
    stage.getIcons().add(new Image("theme-icons/modernThemeImage.jpg"));
  }

  /**
   * sets the application theme to our pastel theme
   */
  public void setPastelTheme() {
    anchorPane.setStyle("-fx-background-color: " + "#bfe3ec" + ";");
    javaJournalLabel.setStyle("-fx-text-fill: " + "#ea88be" + ";");
    javaJournalLabel.setFont(Font.font("Algerian", 40));
    weekView.setStyle("-fx-background-color: " + "#c3f5cd" + ";");
    taskQueueLabel.setStyle("-fx-text-fill: " + toHexColor(Color.BLACK) + ";");
    taskQueueLabel.setStyle("-fx-background-color: " + "#f5e1aa" + ";");
    taskQueueLabel.setFont(Font.font("Gadugi", 10));
    buttonStyle("#d9bdfc");
    textFieldStyle("#e8c3d2", "#0e100e", "#e8c3d2", "#0e100e");

    quotesNotesLabel.setFont(Font.font("OCR A Extended", 13));
    quotesNotesLabel.setStyle("-fx-text-fill: " + "#3a8f5b" + ";");
    quotesNotesTextArea.setStyle("-fx-control-inner-background:#e8c3d2; "
        + "-fx-font-family: Consolas; -fx-highlight-fill: #0e100e; "
        + "-fx-highlight-text-fill: #e8c3d2; -fx-text-fill: #0e100e; ");

    weeklyOverviewLabel.setFont(Font.font("OCR A Extended", 13));
    weeklyOverviewLabel.setStyle("-fx-text-fill: " + "#3a8f5b" + ";");
    weekView.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
    stage.getIcons().clear();
    stage.getIcons().add(new Image("theme-icons/pastelThemeIcon.jpg"));
  }

  /**
   * sets the application theme to our retro theme
   */
  public void setRetroTheme() {
    anchorPane.setStyle("-fx-background-color: " + "#985a15" + ";");
    javaJournalLabel.setStyle("-fx-text-fill: " + "#EEE2DE" + ";");
    javaJournalLabel.setFont(Font.font("Courier New", 38));
    weekView.setStyle("-fx-background-color: " + "#5C8984" + ";");
    taskQueueLabel.setStyle("-fx-text-fill: " + toHexColor(Color.BLACK) + ";");
    taskQueueLabel.setStyle("-fx-background-color: " + "#F2D8D8" + ";");
    taskQueueLabel.setFont(Font.font("Gadugi", 10));
    buttonStyle("#B3C890");
    textFieldStyle("#B69A65FF", "#a0d1d9", "#937656", "#515188");
    quotesNotesLabel.setFont(Font.font("Niagara Solid", 28));
    quotesNotesLabel.setStyle("-fx-text-fill: " + "#F5DEB3" + ";");
    quotesNotesTextArea.setStyle("-fx-control-inner-background:#B69A65FF; "
        + "-fx-font-family: Consolas; -fx-highlight-fill: #a0d1d9; "
        + "-fx-highlight-text-fill: #937656; -fx-text-fill: #515188; ");
    weeklyOverviewLabel.setFont(Font.font("Niagara Solid", 28));
    weeklyOverviewLabel.setStyle("-fx-text-fill: " + "#F5DEB3" + ";");
    weekView.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
    stage.getIcons().clear();
    stage.getIcons().add(new Image("theme-icons/retroThemeIcon.jpg"));
  }

  /**
   * sets the application theme to default
   */
  public void defaultTheme() {

    //INITIALIZING COLOR OF BUTTONS
    modernThemeButton.setStyle("-fx-background-color: " + "#b3a694" + ";");
    modernThemeButton.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

    pastelThemeButton.setStyle("-fx-background-color: " + "#A459D1" + ";");
    pastelThemeButton.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
    retroThemeButton.setStyle("-fx-background-color: " + "#B3C890" + ";");
    retroThemeButton.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

    anchorPane.setStyle("-fx-background-color: " + "#ffffff" + ";");
    javaJournalLabel.setStyle("-fx-text-fill: " + "#0e100e" + ";");
    javaJournalLabel.setFont(Font.font("Bradley Hand ITC", 57));
    weekView.setStyle("-fx-background-color: " + "#ffffff" + ";");
    taskQueueLabel.setStyle("-fx-text-fill: " + toHexColor(Color.BLACK) + ";");
    taskQueueLabel.setStyle("-fx-background-color: " + "#ffffff" + ";");
    taskQueueLabel.setFont(Font.font("Arial Narrow", 12));
    buttonStyle("#ffffff");
    textFieldStyle("#B69A65FF", "#a0d1d9", "#937656", "#515188");
    quotesNotesLabel.setFont(Font.font("Bell MT", 16));
    quotesNotesLabel.setStyle("-fx-text-fill: " + "#0e100e" + ";");
    quotesNotesTextArea.setStyle("-fx-control-inner-background:#ffffff; "
        + "-fx-font-family: Consolas; -fx-highlight-fill: #a0d1d9; "
        + "-fx-highlight-text-fill: #0e100e; -fx-text-fill: #ffffff; ");
    textFieldStyle("#ffffff", "#a0d1d9", "#937656", "#515188");
    weekView.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
    stage.getIcons().clear();
    stage.getIcons().add(new Image("theme-icons/defaultThemeIcon.jpg"));
  }

  /**
   * sets the application theme to the custom theme given by the user
   *
   * @param listOfColors the list of colors the user clicked to be used to set the theme
   */
  public void setCustomTheme(List<Color> listOfColors) {
    defaultTheme();
    String backgroundColor = toHexColor(listOfColors.get(0));
    String weekColor = toHexColor(listOfColors.get(1));
    String quotesNotesColor = toHexColor(listOfColors.get(2));
    String titleFontColor = toHexColor(listOfColors.get(3));
    anchorPane.setStyle("-fx-background-color: " + backgroundColor + ";");
    weekView.setStyle("-fx-background-color: " + weekColor + ";");
    javaJournalLabel.setStyle("-fx-text-fill: " + titleFontColor + ";");
    quotesNotesTextArea.setStyle("-fx-control-inner-background: " + quotesNotesColor);
    textFieldStyle("#a0d1d9", "#515188", "#937656", "#515188");
    stage.getIcons().clear();
  }

  /**
   * converts the Java color to its Hex equivalent
   *
   * @param color the color to be converted
   *
   * @return the hex color code of the color
   */
  private String toHexColor(Color color) {
    int red = (int) Math.round(color.getRed() * 255);
    int green = (int) Math.round(color.getGreen() * 255);
    int blue = (int) Math.round(color.getBlue() * 255);

    return String.format("#%02X%02X%02X", red, green, blue);
  }

  /**
   * changes the color of a list of buttons to the passed in color
   *
   * @param color String representing hex color
   */
  private void buttonStyle(String color) {
    for (Button b : buttonList) {
      b.setStyle("-fx-background-color: "
          + color + ";");
      b.setBorder(new Border(new BorderStroke(Color.BLACK,
          BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
    }
  }

  /**
   * changes the styling of a list of TextFields based on passed in colors
   *
   * @param color1 color of inner background
   *
   * @param color2 color of highlight fill
   *
   * @param color3 color of highlight text fill
   *
   * @param color4 color of text fill
   */
  private void textFieldStyle(String color1, String color2, String color3, String color4) {
    for (TextField tf : textFieldList) {
      tf.setStyle("-fx-control-inner-background:"  + color1 + "; "
          + "-fx-font-family: Consolas; -fx-highlight-fill: " + color2 + "; "
          + "-fx-highlight-text-fill: " + color3 + "; " + "-fx-text-fill: " + color4 + "; ");
    }
  }
}

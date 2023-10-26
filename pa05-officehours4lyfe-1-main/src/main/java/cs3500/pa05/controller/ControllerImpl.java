package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.ThemeType;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.SaveFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * a class to represent the implementation of the Controller interface and handle user interaction
 */
public class ControllerImpl extends AbstractHandler implements Controller {
  Week weekContent;

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button saveToButton;

  @FXML
  private Button modernThemeButton;

  @FXML
  private Button retroThemeButton;

  @FXML
  private Button pastelThemeButton;

  @FXML
  private Button customThemeButton;

  @FXML
  private Button addTaskButton;

  @FXML
  private Button addEventButton;

  @FXML
  private Button removeTaskButton;

  @FXML
  private Button removeEventButton;

  @FXML
  private Button openFileButton;

  @FXML
  private Label taskQueueLabel;

  @FXML
  private GridPane weekView;
  private final StringProperty[][] textBoxes;

  @FXML
  private Label quotesNotesLabel;

  @FXML
  private TextArea quotesNotesTextArea;

  @FXML
  private Label javaJournalLabel;

  @FXML
  private TextField weekNameTextField;

  @FXML
  private TextField maxEventsTextField;

  @FXML
  private TextField maxTasksTextField;

  @FXML
  private VBox taskQueueVbox;

  @FXML
  private Button editTaskButton;

  @FXML
  private Button editEventButton;

  @FXML
  private Button updateMaxButton;

  @FXML
  private Label weeklyOverviewLabel;

  @FXML
  private TextField totalTasksTextField;

  @FXML
  private TextField totalEventsTextField;

  @FXML
  private TextField totalTasksCompleteTextField;

  @FXML
  private Label splashLabel;

  private WeeklyOverview weeklyOverview;


  private List<HBox> taskhBoxes;

  private Stage stage;

  private List<Day> days;

  private List<Color> listOfColors;

  /**
   * constructs a ControllerImpl given a stage
   *
   * @param stage the stage for the application
   */

  public ControllerImpl(Stage stage) {
    super();
    this.stage = stage;
    textBoxes = new StringProperty[14][7];
    days = new ArrayList<>();
    days.add(new Day(DayName.SUNDAY));
    days.add(new Day(DayName.MONDAY));
    days.add(new Day(DayName.TUESDAY));
    days.add(new Day(DayName.WEDNESDAY));
    days.add(new Day(DayName.THURSDAY));
    days.add(new Day(DayName.FRIDAY));
    days.add(new Day(DayName.SATURDAY));
    weekContent = new Week(days);
    this.taskhBoxes = new ArrayList<>();
  }

  /**
   * initializes a calendar grid with scrollable text boxes for each day of week
   */

  private void initCalendar() {
    for (int row = 1; row < 13; row++) {
      // Populate the row
      for (int col = 0; col < 7; col++) {
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Label newBox = new Label();
        newBox.setWrapText(true);
        newBox.setMaxWidth(Double.MAX_VALUE); // Allow label to grow horizontally
        textBoxes[row][col] = new SimpleStringProperty("");
        newBox.textProperty().bind(textBoxes[row][col]);

        scroll.setContent(newBox);
        weekView.add(scroll, col, row);
      }
    }
  }

  /**
   * initializes the buttons with their even handlers
   */

  private void initButtons() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save");
    saveToButton.setOnAction(e -> {
      if (!weekNameTextField.getText().equals("")) {
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
          SaveFile save = new SaveFile(selectedFile.toPath());
          weekContent.setWeekName(weekNameTextField.getText());
          weekContent.setQuotesAndNotes(quotesNotesTextArea.getText());
          save.convertToJson(weekContent);
        }
      } else {
        displayErrorAlert("Save File Error",
            "Unable to save this file because you did not name your week!");
      }
    });

    this.weeklyOverview =
        new WeeklyOverview(totalTasksTextField, totalEventsTextField, totalTasksCompleteTextField);



    ImageView splashScreen = new ImageView("splashScreen.png");
    splashLabel.setGraphic(splashScreen);

    PauseTransition pause = new PauseTransition(Duration.seconds(2));
    pause.setOnFinished(e -> splashLabel.setVisible(false));
    pause.play();




    Theme themeItems =
        new Theme(anchorPane, javaJournalLabel, weekView, saveToButton, addTaskButton,
            addEventButton, editTaskButton, editEventButton, removeTaskButton, removeEventButton,
            openFileButton, updateMaxButton, modernThemeButton, pastelThemeButton, retroThemeButton,
            quotesNotesLabel, quotesNotesTextArea, stage, taskQueueLabel, weeklyOverviewLabel,
            totalTasksTextField, totalEventsTextField, totalTasksCompleteTextField,
            maxTasksTextField, maxEventsTextField);


    stage.getIcons().add(new Image("theme-icons/defaultThemeIcon.jpg"));
    themeItems.defaultTheme();
    openFileButton.setOnAction(
        new FileOpeningHandler(stage, textBoxes, taskQueueVbox, quotesNotesTextArea,
            weekNameTextField, themeItems, days, weekContent, taskhBoxes, maxEventsTextField,
            maxTasksTextField, weeklyOverview));

    // TASK / EVENT CREATION BUTTONS
    addEventButton.setOnAction(
        new EventCreationHandler(textBoxes, stage, days, weekContent, weeklyOverview));
    addTaskButton.setOnAction(
        new TaskCreationHandler(textBoxes, stage, taskQueueVbox, days, weekContent, taskhBoxes,
            weeklyOverview));
    removeEventButton.setOnAction(
        new EventRemoverHandler(textBoxes, stage, days, weekContent, weeklyOverview));
    removeTaskButton.setOnAction(
        new TaskRemoverHandler(textBoxes, stage, taskQueueVbox, days, weekContent, taskhBoxes,
            weeklyOverview));
    editTaskButton.setOnAction(
        new TaskEditingHandler(textBoxes, stage, taskQueueVbox, days, weekContent, taskhBoxes,
            weeklyOverview));
    editEventButton.setOnAction(new EventEditingHandler(textBoxes, stage, days));
    updateMaxButton.setOnAction(
        new MaxTasksEventsHandler(maxEventsTextField, maxTasksTextField, weekContent));

    //MODERN THEME
    modernThemeButton.setOnAction(e -> {
      themeItems.setModernTheme();
      weekContent.setTheme(ThemeType.MODERN);
    });

    //PASTEL THEME HANDLER
    pastelThemeButton.setOnAction(e -> {
      themeItems.setPastelTheme();
      weekContent.setTheme(ThemeType.PASTEL);
    });

    //RETRO THEME HANDLER
    retroThemeButton.setOnAction(e -> {
      themeItems.setRetroTheme();
      weekContent.setTheme(ThemeType.RETRO);
    });

    // CUSTOM THEME HANDLER
    customThemeButton.setOnAction(e -> {
      weekContent.setTheme(ThemeType.CUSTOM);
      weekContent.resetThemeInfo();
      listOfColors = new ArrayList<>();

      displayCustomThemeDialog(listOfColors, themeItems);
    });
  }

  private void displayCustomThemeDialog(List<Color> listOfColors, Theme themeItems) {
    Popup popup = new Popup();

    Dialog<Event> dialog = new Dialog<>();
    dialog.setHeaderText("Create custom theme");
    dialog.getDialogPane().setMinSize(500, 270);

    VBox vbox = new VBox(10);

    // background color dialog
    HBox backgroundColorBox = new HBox(10);
    Label backgroundColorLabel = new Label("Background Color: ");
    ColorPicker backgroundColorPicker = new ColorPicker();
    backgroundColorBox.getChildren().addAll(backgroundColorLabel, backgroundColorPicker);

    HBox weekColorBox = new HBox(10);
    Label weekColorLabel = new Label("Week Color:");
    ColorPicker weekColorPicker = new ColorPicker();
    weekColorBox.getChildren().addAll(weekColorLabel, weekColorPicker);

    // change to quotes and notes
    HBox quotesNotesColorBox = new HBox(10);
    Label quotesNotesColorLabel = new Label("Quotes and Notes: ");
    ColorPicker quotesNotesColorPicker = new ColorPicker();
    quotesNotesColorBox.getChildren().addAll(quotesNotesColorLabel, quotesNotesColorPicker);

    // change title font color
    HBox titleFontColorBox = new HBox(10);
    Label titleFontColorLabel = new Label("Title Font Color:");
    ColorPicker titleFontColorPicker = new ColorPicker();
    titleFontColorBox.getChildren().addAll(titleFontColorLabel, titleFontColorPicker);

    vbox.getChildren()
        .addAll(backgroundColorBox, quotesNotesColorBox, weekColorBox, titleFontColorBox);
    dialog.getDialogPane().setContent(vbox);

    popup.getContent().add(dialog.getDialogPane());
    HBox hbox = new HBox(15);
    hbox.setLayoutX(140);
    hbox.setLayoutY(230);

    Button ok = new Button("OK");
    Button cancel = new Button("CANCEL");
    hbox.getChildren().addAll(ok, cancel);
    ok.setOnAction(y -> {
      popup.hide();
      weekContent.addToThemeInfo(backgroundColorPicker.getValue());
      weekContent.addToThemeInfo(weekColorPicker.getValue());
      weekContent.addToThemeInfo(quotesNotesColorPicker.getValue());
      weekContent.addToThemeInfo(titleFontColorPicker.getValue());

      listOfColors.add(backgroundColorPicker.getValue());
      listOfColors.add(weekColorPicker.getValue());
      listOfColors.add(quotesNotesColorPicker.getValue());
      listOfColors.add(titleFontColorPicker.getValue());


      themeItems.setCustomTheme(listOfColors);
    });
    cancel.setOnAction(x -> popup.hide());
    popup.getContent().add(hbox);
    popup.show(stage);
  }

  /**
   * runs the controller by initializing the calender and buttons
   */

  @Override
  public void run() {
    initCalendar();
    initButtons();
    //update file path thats seperate from the run method
  }
}

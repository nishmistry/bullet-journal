package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Event;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * represents the add events controller
 */
public class AddEventsController implements Controller {
  private Stage stage;
  private BulletJournal bulletJournal;
  private Controller bulletJournalController;
  @FXML
  private AnchorPane addEventsAnchorPane;
  @FXML
  private Label errorLabel;
  @FXML
  private TextField eventNameText;
  @FXML
  private TextField eventDescText;
  @FXML
  private ChoiceBox<String> dayChoiceBox;
  @FXML
  private Spinner<Integer> startHour;
  @FXML
  private Spinner<Integer> startMinute;
  @FXML
  private Spinner<Integer> durationHour;
  @FXML
  private Spinner<Integer> durationMinute;
  @FXML
  private Button saveButton;
  @FXML
  private Button cancelButton;

  /**
   * constructor to instantiate the add events controller
   *
   * @param bulletJournal           represents the bullet journal
   * @param bulletJournalController represents the bullet journal controller
   * @param stage                   represents the stage
   */
  public AddEventsController(BulletJournal bulletJournal, Controller bulletJournalController,
                             Stage stage) {
    this.bulletJournal = bulletJournal;
    this.bulletJournalController = bulletJournalController;
    this.stage = stage;
    this.dayChoiceBox = new ChoiceBox<>();
    this.startHour = new Spinner<>();
    this.startMinute = new Spinner<>();
    this.durationHour = new Spinner<>();
    this.durationMinute = new Spinner<>();
  }

  /**
   * represents the run method
   */
  @Override
  public void run() {
    InitializeUtils.changeAnchorPaneColors(addEventsAnchorPane, bulletJournal);
    initFields();
    setHandlers();
  }

  /**
   * initializes the fields
   */
  private void initFields() {
    dayChoiceBox.setItems(
        FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday", "Sunday"));
    dayChoiceBox.setValue("Monday");

    Spinner[] spinnerHours = new Spinner[] {this.startHour, this.durationHour};
    Spinner[] spinnerMinutes = new Spinner[] {this.startMinute, this.durationMinute};

    for (Spinner spinner : spinnerHours) {
      SpinnerValueFactory<Integer>
          valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 23);
      spinner.setValueFactory(valueFactory);
      spinner.setEditable(true);
    }

    for (Spinner spinner : spinnerMinutes) {
      SpinnerValueFactory<Integer>
          valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 59);
      spinner.setValueFactory(valueFactory);
      spinner.setEditable(true);
    }
  }

  /**
   * represents setting the handlers
   */
  private void setHandlers() {
    saveButton.setOnAction(event -> handleSave());
    cancelButton.setOnAction(event -> handleCancel());
  }

  /**
   * represents handling the save button
   */
  private void handleSave() {
    String newStartMinute;
    String newDurationMinute;
    if (String.valueOf(startMinute.getValue()).length() == 1) {
      newStartMinute = "0" + startMinute.getValue();
    } else {
      newStartMinute = String.valueOf(startMinute.getValue());
    }
    if (String.valueOf(durationMinute.getValue()).length() == 1) {
      newDurationMinute = "0" + durationMinute.getValue();
    } else {
      newDurationMinute = String.valueOf(durationMinute.getValue());
    }
    String startTime = startHour.getValue() + ":" + newStartMinute;
    String durationTime = durationHour.getValue() + ":" + newDurationMinute;

    Event event = new Event(
        eventNameText.getText(), eventDescText.getText(), startTime, durationTime);
    if (eventNameText.getText().equals("")) {
      errorLabel.setText("Please provide valid input into all fields.");
    } else if (!bulletJournal.getWeek().checkAddEventType(dayChoiceBox.getValue(), event)) {
      errorLabel.setText("Max events reached for this day.");
    } else {
      bulletJournal.getWeek().addEventType(dayChoiceBox.getValue(), event);

      goToMainScreen();
    }
  }

  /**
   * represents handling the cancel button
   */
  private void handleCancel() {
    goToMainScreen();
  }

  /**
   * represents the method to go back to the main screen for cancel
   */
  private void goToMainScreen() {
    ControllerUtils.goToMainScreen(bulletJournalController, stage);
  }
}

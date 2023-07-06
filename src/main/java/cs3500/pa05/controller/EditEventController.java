package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Event;
import java.util.ArrayList;
import java.util.Arrays;
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
 * represents the controller to edit an event
 */
public class EditEventController implements Controller {
  private BulletJournal bulletJournal;
  private BulletJournalController bulletJournalController;
  private Stage stage;
  private Event event;
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
  @FXML
  private Label pageName;
  private ArrayList<String> dayList;
  private String oldDay;

  /**
   * controller to instantiate a edit event controller.
   *
   * @param bulletJournal           the current bullet journal
   * @param bulletJournalController the current bullet journal controller
   * @param stage                   the stage to set the scene
   * @param event                   the event to edit
   */
  public EditEventController(BulletJournal bulletJournal,
                             BulletJournalController bulletJournalController, Stage stage,
                             Event event) {
    this.bulletJournal = bulletJournal;
    this.bulletJournalController = bulletJournalController;
    this.stage = stage;
    this.event = event;
    this.dayChoiceBox = new ChoiceBox<>();
    this.startHour = new Spinner<>();
    this.startMinute = new Spinner<>();
    this.durationHour = new Spinner<>();
    this.durationMinute = new Spinner<>();
    this.oldDay = bulletJournal.getWeek().dayOfEventType(event);
    dayList = new ArrayList<>(
        Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday"));
  }

  /**
   * represents the run method to edit an event
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
        FXCollections.observableArrayList(dayList));
    dayChoiceBox.setValue(oldDay);

    pageName.setText("Edit Event");
    eventNameText.setText(event.getName());
    eventDescText.setText(event.getDescription());

    String startDate = event.getStartDate();
    String duration = event.getDuration();
    String[] startDateSplit = startDate.split(":");
    String[] durationSplit = duration.split(":");

    initializeSpinners(startDateSplit, startHour, startMinute);

    initializeSpinners(durationSplit, durationHour, durationMinute);
  }

  /**
   * initializes the spinners
   *
   * @param startDateSplit the start date split
   * @param startHour      the start hour spinner
   * @param startMinute    the start minute spinner
   */
  private void initializeSpinners(String[] startDateSplit, Spinner<Integer> startHour,
                                  Spinner<Integer> startMinute) {
    SpinnerValueFactory<Integer>
        valueFactoryStartHour = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23,
        Integer.valueOf(startDateSplit[0]));
    startHour.setValueFactory(valueFactoryStartHour);
    startHour.setEditable(true);

    SpinnerValueFactory<Integer>
        valueFactoryStartMinute = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59,
        Integer.valueOf(startDateSplit[1]));
    startMinute.setValueFactory(valueFactoryStartMinute);
    startMinute.setEditable(true);
  }

  /**
   * sets the handlers
   */
  private void setHandlers() {
    saveButton.setOnAction(event -> handleSave());
    cancelButton.setOnAction(event -> handleCancel());
  }

  /**
   * handles what to do when the save button is clicked
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

    Event newEvent = new Event(
        eventNameText.getText(), eventDescText.getText(), startTime, durationTime);
    if (eventNameText.getText().equals("")) {
      errorLabel.setText("Please provide valid input into all fields.");
    } else {
      bulletJournal.getWeek().addEventType(dayChoiceBox.getValue(), newEvent);
      bulletJournal.getWeek().getDaysList().get(dayList.indexOf(oldDay)).removeEventType(event);

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

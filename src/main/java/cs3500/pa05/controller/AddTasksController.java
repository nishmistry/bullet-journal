package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Task;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * represents adding the tasks controller
 */
public class AddTasksController implements Controller {
  private Stage stage;
  private BulletJournal bulletJournal;
  private Controller bulletJournalController;
  @FXML
  private AnchorPane addTasksAnchorPane;
  @FXML
  private TextField taskNameText;
  @FXML
  private TextField taskDescText;
  @FXML
  private ChoiceBox<String> dayChoiceBox;
  @FXML
  private CheckBox completeBox;
  @FXML
  private Label errorLabel;
  @FXML
  private Button saveButton;
  @FXML
  private Button cancelButton;

  /**
   * constructor to instantiate the add tasks controller
   *
   * @param bulletJournal           represents the bullet journal
   * @param bulletJournalController represents the bullet journal controller
   * @param stage                   represents the stage
   */
  public AddTasksController(BulletJournal bulletJournal, Controller bulletJournalController,
                            Stage stage) {
    this.bulletJournal = bulletJournal;
    this.bulletJournalController = bulletJournalController;
    this.stage = stage;
    this.dayChoiceBox = new ChoiceBox<>();
    this.completeBox = new CheckBox();
  }

  /**
   * represents the run method
   */
  @Override
  public void run() {
    InitializeUtils.changeAnchorPaneColors(addTasksAnchorPane, bulletJournal);
    initFields();
    setHandlers();
  }

  /**
   * initializes the fields for add task
   */
  private void initFields() {
    dayChoiceBox.setItems(
        FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday", "Sunday"));
    dayChoiceBox.setValue("Monday");
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
    Task task = new Task(taskNameText.getText(), taskDescText.getText(), completeBox.isSelected());
    if (taskNameText.getText().equals("")) {
      errorLabel.setText("Please provide valid input into all fields.");
    } else if (!bulletJournal.getWeek().checkAddEventType(dayChoiceBox.getValue(), task)) {
      errorLabel.setText("Max tasks reached for this day.");
    } else {
      bulletJournal.getWeek().addEventType(dayChoiceBox.getValue(), task);

      gotoMainScreen();
    }
  }

  /**
   * represents handling the cancel button
   */
  private void handleCancel() {
    gotoMainScreen();
  }

  /**
   * represents going back to the main screen
   */
  private void gotoMainScreen() {
    ControllerUtils.goToMainScreen(bulletJournalController, stage);
  }
}

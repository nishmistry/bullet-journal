package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.Arrays;
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
 * represents the controller to edit task
 */
public class EditTaskController implements Controller {
  private BulletJournal bulletJournal;
  private BulletJournalController bulletJournalController;
  private Stage stage;
  private Task task;
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
  @FXML
  private Label pageName;
  private ArrayList<String> dayList;
  private String oldDay;

  /**
   * constructor to instantiate the edit task controller
   *
   * @param bulletJournal           the current bullet journal
   * @param bulletJournalController the current bullet journal controller
   * @param stage                   the stage to set the scene on
   * @param task                    the task to be edited
   */
  public EditTaskController(BulletJournal bulletJournal,
                            BulletJournalController bulletJournalController, Stage stage,
                            Task task) {
    this.bulletJournal = bulletJournal;
    this.bulletJournalController = bulletJournalController;
    this.stage = stage;
    this.task = task;
    this.dayChoiceBox = new ChoiceBox<>();
    this.completeBox = new CheckBox();
    this.oldDay = bulletJournal.getWeek().dayOfEventType(task);
    dayList = new ArrayList<>(
        Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday"));
  }

  /**
   * the run method to edit a task
   */
  @Override
  public void run() {
    InitializeUtils.changeAnchorPaneColors(addTasksAnchorPane, bulletJournal);
    initFields();
    setHandlers();
  }

  /**
   * initializes the fields
   */
  private void initFields() {
    pageName.setText("Edit Task");
    dayChoiceBox.setItems(
        FXCollections.observableArrayList(dayList));
    dayChoiceBox.setValue(oldDay);
    taskNameText.setText(task.getName());
    taskDescText.setText(task.getDescription());
    if (task.isComplete()) {
      completeBox.fire();
    }
  }

  /**
   * sets the handlers
   */
  private void setHandlers() {
    saveButton.setOnAction(event -> handleSave());
    cancelButton.setOnAction(event -> handleCancel());
  }

  /**
   * handles what to do if the save button is clicked
   */
  private void handleSave() {
    if (taskNameText.getText().equals("")) {
      errorLabel.setText("Please provide valid input into all fields.");
    } else {
      Task newTask =
          new Task(taskNameText.getText(), taskDescText.getText(), completeBox.isSelected());
      bulletJournal.getWeek().addEventType(dayChoiceBox.getValue(), newTask);
      bulletJournal.getWeek().getDaysList().get(dayList.indexOf(oldDay)).removeEventType(task);
      gotoMainScreen();
    }
  }

  /**
   * handles what to do if the cancel button is clicked
   */
  private void handleCancel() {
    gotoMainScreen();
  }

  /**
   * sends the user back to the main screen
   */
  private void gotoMainScreen() {
    ControllerUtils.goToMainScreen(bulletJournalController, stage);
  }
}

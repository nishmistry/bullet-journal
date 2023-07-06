package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.view.AddEventsView;
import cs3500.pa05.view.AddTasksView;
import cs3500.pa05.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * represents the add chooser controller
 */
public class AddChooserController implements Controller {
  private Stage stage;
  private BulletJournal bulletJournal;
  private Controller bulletJournalController;
  @FXML
  private Button backButton;
  @FXML
  private Button addEventButton;
  @FXML
  private Button addTaskButton;
  @FXML
  private VBox addChooserVerticalBox;

  /**
   * constructor to instantiate the add chooser controller
   *
   * @param bulletJournal           represents the bullet journal
   * @param bulletJournalController represents the bullet journal controller
   * @param stage                   represents the stage
   */
  public AddChooserController(BulletJournal bulletJournal, Controller bulletJournalController,
                              Stage stage) {
    this.bulletJournal = bulletJournal;
    this.bulletJournalController = bulletJournalController;
    this.stage = stage;
  }

  /**
   * represents the run method
   */
  @Override
  public void run() {
    InitializeUtils.changeVboxColors(addChooserVerticalBox, bulletJournal);
    setHandlers();
  }

  /**
   * represents setting the handlers
   */
  private void setHandlers() {
    backButton.setOnAction(event -> handleBack());
    addEventButton.setOnAction(event -> handleAddEvent());
    addTaskButton.setOnAction(event -> handleAddTask());
  }

  /**
   * represents handling the back button
   */
  private void handleBack() {
    ControllerUtils.goToMainScreen(bulletJournalController, stage);
  }

  /**
   * represents the add event handler
   */
  private void handleAddEvent() {
    Controller eventsController =
        new AddEventsController(bulletJournal, bulletJournalController, stage);
    View addEventsView = new AddEventsView(eventsController, "AddEvents.fxml");
    stage.setScene(addEventsView.load());
    eventsController.run();
    stage.show();
    stage.centerOnScreen();
  }

  /**
   * represents handling the add task
   */
  private void handleAddTask() {
    Controller tasksController =
        new AddTasksController(bulletJournal, bulletJournalController, stage);
    View addTasksView = new AddTasksView(tasksController, "AddTasks.fxml");
    stage.setScene(addTasksView.load());
    tasksController.run();
    stage.show();
    stage.centerOnScreen();
  }
}

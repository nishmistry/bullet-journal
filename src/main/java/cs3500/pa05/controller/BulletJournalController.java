package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventType;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TypeOfEvent;
import cs3500.pa05.view.EditEventView;
import cs3500.pa05.view.EditTaskView;
import cs3500.pa05.view.View;
import java.awt.Desktop;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * represents a bullet journal controller
 */
public class BulletJournalController implements Controller {
  @FXML
  private VBox backgroundVbox;
  @FXML
  private Label monday;
  @FXML
  private Label tuesday;
  @FXML
  private Label wednesday;
  @FXML
  private Label thursday;
  @FXML
  private Label friday;
  @FXML
  private Label saturday;
  @FXML
  private Label sunday;
  @FXML
  private Label weeklyOverview;
  @FXML
  private Label qotd;
  @FXML
  private Label weekName;
  @FXML
  private Label totalEvents;
  @FXML
  private Label totalTasks;
  @FXML
  private ProgressBar taskPercentCompletedProgress;
  @FXML
  private Label taskCompletedLabel;
  @FXML
  private Label taskQueue;
  @FXML
  private ImageView save;
  @FXML
  private ImageView setting;
  @FXML
  private ImageView load;
  @FXML
  private ImageView add;
  private BulletJournal bulletJournal;
  private List<ImageView> imageViewList;
  private Stage stage;
  private String filePath;
  private View bulletJournalView;
  private List<Label> allLabels;
  @FXML
  private ScrollPane taskQueueScrollPane;
  @FXML
  private ScrollPane mondayScroll;
  @FXML
  private ScrollPane tuesdayScroll;
  @FXML
  private ScrollPane wednesdayScroll;
  @FXML
  private ScrollPane thursdayScroll;
  @FXML
  private ScrollPane fridayScroll;
  @FXML
  private ScrollPane saturdayScroll;
  @FXML
  private ScrollPane sundayScroll;


  /**
   * constructor to instantiate the bullet journal controller from a file
   *
   * @param filePath represents the file path for bullet journal
   * @param stage    represents the stage for journal controller
   */
  public BulletJournalController(String filePath, Stage stage) {
    this.filePath = filePath;
    bulletJournal = new BulletJournal(filePath);
    save = new ImageView();
    setting = new ImageView();
    load = new ImageView();
    add = new ImageView();
    this.stage = stage;
    this.bulletJournalView = null;
    this.allLabels = new ArrayList<>();
  }

  /**
   * constructor to instantiate a bullet journal controller from a new file
   *
   * @param stage    represents the stage for java fx
   * @param password represents the password for the bullet journal
   */
  public BulletJournalController(Stage stage, String password) {
    this.filePath = null;
    this.stage = stage;
    bulletJournal = new BulletJournal();
    bulletJournal.setPassword(password);
    this.bulletJournalView = null;
    this.allLabels = new ArrayList<>();
  }

  /**
   * represents the run method for controller
   */
  @Override
  public void run() {
    this.filePath = bulletJournal.getFilePath();
    initialize();
    initializeWeeklyOverview();
    initializeTaskQueue();
    initializeDays();
    setDimensions();
    setEventHandlers();
  }

  /**
   * presents the summary of week's statistics including total tasks and events completed
   * shows progress of tasks completed
   */
  private void initializeWeeklyOverview() {
    int totalTasks = 0;
    int totalEvents = 0;
    int sumTasksCompleted = 0;
    for (Day day : bulletJournal.getWeek().getDaysList()) {
      for (EventType eventType : day.getEventTypeList()) {
        if (eventType.getType().equals(TypeOfEvent.TASK.toString())) {
          totalTasks += 1;
          Task task = (Task) eventType;
          if (task.isComplete()) {
            sumTasksCompleted += 1;
          }
        }
        if (eventType.getType().equals(TypeOfEvent.EVENT.toString())) {
          totalEvents += 1;
        }
      }
    }
    this.totalTasks.setText("Total Tasks: " + totalTasks);
    this.totalEvents.setText("Total Events: " + totalEvents);
    DecimalFormat df = new DecimalFormat("#.#");
    df.setRoundingMode(RoundingMode.CEILING);
    initializeWeeklyOverviewHelper(sumTasksCompleted, totalTasks, df);
  }

  /**
   * Helper method for initializing the weekly overview.
   *
   * @param sumTasksCompleted all the completed tasks
   * @param totalTasks        the total number of tasks
   * @param df                the decimal format to follow
   */
  private void initializeWeeklyOverviewHelper(int sumTasksCompleted, int totalTasks,
                                              DecimalFormat df) {
    double percentTasksCompleted;
    if (totalTasks > 0) {
      percentTasksCompleted = ((double) sumTasksCompleted / totalTasks);
      double percentTasksCompleted2 = percentTasksCompleted * 100;
      String stringPercent = df.format(percentTasksCompleted2);
      taskCompletedLabel.setText("% of Tasks Completed: " + stringPercent + "%");
      taskPercentCompletedProgress.setProgress(percentTasksCompleted);
    } else {
      taskCompletedLabel.setText("% of Tasks Completed: " + 0 + "%");
      taskPercentCompletedProgress.setProgress(0.0);
    }
  }

  /**
   * represents initializing the images
   */
  private void initialize() {
    imageViewList = new ArrayList<>(Arrays.asList(save, setting, load, add));
    initializeIcons();
    initializeTextColor();
    weekName.setText(bulletJournal.getWeek().getName());
    qotd.setText(bulletJournal.getWeek().getQuote());
  }

  /**
   * represents initializing the icons for the bullet journal
   */
  private void initializeIcons() {
    List<Image> imageList =
        InitializeUtils.initializeImageList(bulletJournal.getTheme().getIcons());
    save.setImage(imageList.get(0));
    setting.setImage(imageList.get(1));
    add.setImage(imageList.get(2));
    load.setImage(imageList.get(3));
  }

  /**
   * represents initializing the text color
   */
  private void initializeTextColor() {
    allLabels.add(monday);
    allLabels.add(tuesday);
    allLabels.add(wednesday);
    allLabels.add(thursday);
    allLabels.add(friday);
    allLabels.add(saturday);
    allLabels.add(sunday);
    allLabels.add(weekName);
    allLabels.add(taskQueue);
    allLabels.add(weeklyOverview);
    allLabels.add(taskCompletedLabel);
    allLabels.add(totalTasks);
    allLabels.add(totalEvents);
    allLabels.add(qotd);
    InitializeUtils.changeLabelColor(allLabels, bulletJournal);
    InitializeUtils.changeVboxColors(backgroundVbox, bulletJournal);
  }

  /**
   * represents setting the dimensions
   */
  private void setDimensions() {
    for (ImageView i : imageViewList) {
      i.setFitWidth(100);
      i.setFitHeight(100);
    }
  }

  /**
   * represents the event handlers for the bullet journal
   */
  private void setEventHandlers() {
    for (ImageView i : imageViewList) {
      i.setOnMouseEntered(event -> i.setRotate(7.5));
      i.setOnMouseClicked(event -> handleClick(i));
      i.setOnMouseExited(event -> i.setRotate(0.0));
    }
  }

  /**
   * represents handling the click
   *
   * @param image represents the image for handling click
   */
  private void handleClick(ImageView image) {
    Controller controlPanelController =
        new ControlPanelController(image.getId(), bulletJournal, stage, filePath, this);
    controlPanelController.run();
  }

  /**
   * represents initializing the task queue with check boxes
   */
  private void initializeTaskQueue() {
    Pane topSpacer = new Pane();
    topSpacer.setPrefHeight(3);
    VBox taskQueue = new VBox();
    taskQueue.getChildren().add(topSpacer);
    taskQueue.setSpacing(15);
    taskQueueScrollPane.setContent(taskQueue);
    initializeTaskQueueHelper(taskQueue);
  }

  /**
   * helper method for initializing the task queue
   *
   * @param taskQueue the vbox to add elements to
   */
  private void initializeTaskQueueHelper(VBox taskQueue) {
    for (Day day : bulletJournal.getWeek().getDaysList()) {
      for (EventType eventType : day.getEventTypeList()) {
        if (eventType.getType().equals(TypeOfEvent.TASK.toString())) {
          Pane leftSpacer = new Pane();
          leftSpacer.setPrefWidth(10);
          HBox horizontalBox = new HBox();
          horizontalBox.setAlignment(Pos.CENTER_LEFT);
          horizontalBox.setSpacing(10);
          CheckBox checkBox = new CheckBox();
          Task task = (Task) eventType;
          if (task.isComplete()) {
            checkBox.fire();
          }
          checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
              task.setComplete(true);
              initializeDays();
            } else {
              task.setComplete(false);
              initializeDays();
            }
            initializeWeeklyOverview();
          });
          Label label = new Label(eventType.getName());
          setButtons(horizontalBox, leftSpacer, label, taskQueue, task, checkBox);
        }
      }
    }
  }

  /**
   * represents initializing the days
   */
  private void initializeDays() {
    ScrollPane[] paneArray =
        new ScrollPane[] {mondayScroll, tuesdayScroll, wednesdayScroll, thursdayScroll,
            fridayScroll, saturdayScroll, sundayScroll};
    for (int i = 0; i < bulletJournal.getWeek().getDaysList().size(); i++) {
      Pane topSpacer = new Pane();
      topSpacer.setPrefHeight(3);
      VBox dayList = new VBox();
      dayList.getChildren().add(topSpacer);
      dayList.setSpacing(15);
      paneArray[i].setContent(dayList);
      for (EventType eventType : bulletJournal.getWeek().getDaysList().get(i).getEventTypeList()) {
        Pane leftSpacer = new Pane();
        leftSpacer.setPrefWidth(10);
        HBox horizontalBox = new HBox();
        horizontalBox.setAlignment(Pos.CENTER_LEFT);
        horizontalBox.setSpacing(10);
        Label label = new Label(eventType.getName());
        initializeDaysHelper(eventType, horizontalBox, leftSpacer, label, dayList);
      }
    }
  }

  /**
   * helper method for initializing the days
   *
   * @param eventType  type of event
   * @param horizontalBox       the hbox to add elements to
   * @param leftSpacer a Pane used as a spacer
   * @param label      the label to add to the hbox
   * @param dayList    the vbox to add elements to
   */
  private void initializeDaysHelper(EventType eventType, HBox horizontalBox,
                                    Pane leftSpacer, Label label, VBox dayList) {
    if (eventType.getType().equals(TypeOfEvent.TASK.toString())) {
      Task task = (Task) eventType;
      CheckBox checkBox = new CheckBox();
      if (task.isComplete()) {
        checkBox.fire();
      }
      checkBox.setOnAction(event -> {
        if (checkBox.isSelected()) {
          task.setComplete(true);
          initializeTaskQueue();
        } else {
          task.setComplete(false);
          initializeTaskQueue();
        }
        initializeWeeklyOverview();
      });
      setButtons(horizontalBox, leftSpacer, label, dayList, task, checkBox);
    } else {
      Pane spacer = new Pane();
      spacer.setPrefWidth(19);
      Button open = new Button("Open Event");
      open.setOnAction(event -> eventDialog((Event) eventType));
      Button edit = new Button("Edit Event");
      edit.setOnAction(event -> editEvent((Event) eventType));
      horizontalBox.getChildren().addAll(leftSpacer, spacer, label, open, edit);
      dayList.getChildren().add(horizontalBox);
    }
  }

  /**
   * sets the buttons and the dayList Vboc
   *
   * @param horizontalBox       hbox to add elements to
   * @param leftSpacer pane used as a spacer
   * @param label      label to put in the hboc
   * @param dayList    the vbox to display information
   * @param task       the task to act on when clicked
   * @param checkBox   the checkbox to add
   */
  private void setButtons(HBox horizontalBox, Pane leftSpacer, Label label,
                          VBox dayList, Task task, CheckBox checkBox) {
    Button open = new Button("Open Task");
    open.setOnAction(event -> taskDialog(task));
    Button edit = new Button("Edit Task");
    edit.setOnAction(event -> editTask(task));
    horizontalBox.getChildren().addAll(leftSpacer, checkBox, label, open, edit);
    dayList.getChildren().add(horizontalBox);
  }

  /**
   * represents the mini viewer for a task
   *
   * @param task task to display
   */
  public void taskDialog(Task task) {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Task MiniView");
    Label label = new Label();
    VBox verticalBox = new VBox();
    String taskName = "Task Name: " + task.getName();
    String taskDesc = "Task Description: " + task.getDescription();
    String taskComplete = "Task Complete: " + task.isComplete();
    String dayOfWeek = "Day of Week: " + bulletJournal.getWeek().dayOfEventType(task);
    String fullText =
        taskName + System.lineSeparator() + taskDesc + System.lineSeparator() + taskComplete
        + System.lineSeparator() + dayOfWeek;
    dialogHelper(label, fullText, verticalBox, task, dialog);
  }

  /**
   * Helper method to abstract the same code for the dialogs
   *
   * @param label the label to use
   * @param fullText the text to put in the label
   * @param verticalBox the vBox to user
   * @param eventType the event type to use
   * @param dialog the dialog to user
   */
  private void dialogHelper(Label label, String fullText, VBox verticalBox,
                            EventType eventType, Dialog dialog) {
    label.setText(fullText);
    verticalBox.getChildren().add(label);
    handleLinks(verticalBox, eventType);
    ButtonType buttonType = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().setContent(verticalBox);
    dialog.getDialogPane().getButtonTypes().add(buttonType);
    dialog.showAndWait();
  }

  /**
   * represents the mini viewer for event
   *
   * @param event the event to display
   */
  public void eventDialog(Event event) {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Event MiniView");
    Label label = new Label();
    VBox verticalBox = new VBox();
    String taskName = "Task Name: " + event.getName();
    String taskDesc = "Task Description: " + event.getDescription();
    String startTime = "Start Time: " + event.getStartDate();
    String duration = "Duration: " + event.getDuration();
    String dayOfWeek = "Day of Week: " + bulletJournal.getWeek().dayOfEventType(event);
    String fullText =
        taskName + System.lineSeparator() + taskDesc + System.lineSeparator()
            + startTime + System.lineSeparator() + duration
        + System.lineSeparator() + dayOfWeek;
    dialogHelper(label, fullText, verticalBox, event, dialog);
  }

  /**
   * adds the links and allows the user to click on it
   *
   * @param verticalBox      vbox to add the links too
   * @param eventType eventType to parse through to find links
   */
  private void handleLinks(VBox verticalBox, EventType eventType) {
    List<Hyperlink> links = eventType.descriptionWithLink();
    for (Hyperlink link : links) {
      verticalBox.getChildren().add(link);
      link.setOnMouseClicked(e -> displayLink(link.getText()));
    }
  }

  /**
   * opens the link in the user's default browser
   *
   * @param url the url of the link to be opened
   */
  private void displayLink(String url) {
    Desktop desktop = Desktop.getDesktop();
    try {
      desktop.browse(new URL(url).toURI());
    } catch (IOException | URISyntaxException ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Failed to open URL");
      alert.setContentText("Please check your internet connection.");
      alert.showAndWait();
    }
  }

  /**
   * edits the task
   *
   * @param task task to be edited
   */
  public void editTask(Task task) {
    Controller editTaskController = new EditTaskController(bulletJournal, this, stage, task);
    View editTaskView = new EditTaskView(editTaskController, "AddTasks.fxml");
    stage.setScene(editTaskView.load());
    editTaskController.run();
    stage.show();
    stage.centerOnScreen();
  }

  /**
   * edits the event
   *
   * @param event event to be edited
   */
  public void editEvent(Event event) {
    Controller editEventController = new EditEventController(bulletJournal, this, stage, event);
    View editEventView = new EditEventView(editEventController, "AddEvents.fxml");
    stage.setScene(editEventView.load());
    editEventController.run();
    stage.show();
    stage.centerOnScreen();
  }
}

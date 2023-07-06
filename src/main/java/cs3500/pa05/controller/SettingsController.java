package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.theme.BlueTheme;
import cs3500.pa05.model.theme.DarkTheme;
import cs3500.pa05.model.theme.LightTheme;
import cs3500.pa05.model.theme.SuperDarkTheme;
import cs3500.pa05.model.theme.Theme;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * represents the settings controller
 */
public class SettingsController implements Controller {
  private Stage stage;
  private BulletJournal bulletJournal;
  private Controller bulletJournalController;
  @FXML
  private AnchorPane settingsAnchorPane;
  @FXML
  private Button saveButton;
  @FXML
  private TextField weekNameText;
  @FXML
  private TextArea quoteText;
  @FXML
  private ChoiceBox<Theme> themeSelector;
  @FXML
  private Spinner<Integer> maxTasksSpinner;
  @FXML
  private Button backButton;
  @FXML
  private Spinner<Integer> maxEventsSpinner;
  @FXML
  private Label errorLabel;

  /**
   * represents the settings controller
   *
   * @param bulletJournal           represents the bullet journal
   * @param bulletJournalController represents the bullet journal controller
   * @param stage                   represents the stage
   */
  public SettingsController(BulletJournal bulletJournal, Controller bulletJournalController,
                            Stage stage) {
    this.bulletJournal = bulletJournal;
    this.bulletJournalController = bulletJournalController;
    this.stage = stage;
    maxTasksSpinner = new Spinner<>();
    themeSelector = new ChoiceBox<>();
    maxEventsSpinner = new Spinner<>();
  }

  /**
   * represents the run method for settings controller
   */
  @Override
  public void run() {
    InitializeUtils.changeAnchorPaneColors(settingsAnchorPane, bulletJournal);
    initFields();
    setHandlers();
  }

  /**
   * initializes the fields
   */
  private void initFields() {
    weekNameText.setText(bulletJournal.getWeek().getName());
    quoteText.setText(bulletJournal.getWeek().getQuote());

    themeSelector.setItems(FXCollections.observableArrayList(new LightTheme(),
        new DarkTheme(), new SuperDarkTheme(), new BlueTheme()));
    themeSelector.setValue(bulletJournal.getTheme());

    SpinnerValueFactory<Integer> valueFactoryTasks =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(
            1, 15, bulletJournal.getWeek().getMaxTasks());
    SpinnerValueFactory<Integer> valueFactoryEvents =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(
            1, 15, bulletJournal.getWeek().getMaxEvents());
    maxTasksSpinner.setValueFactory(valueFactoryTasks);
    maxTasksSpinner.setEditable(true);
    maxEventsSpinner.setValueFactory(valueFactoryEvents);
    maxEventsSpinner.setEditable(true);
  }

  /**
   * represents setting the handlers
   */
  private void setHandlers() {
    saveButton.setOnAction(event -> handleSave());
    backButton.setOnAction(event -> handleBack());
  }

  /**
   * represents the functionality for save button
   */
  private void handleSave() {
    int currMaxTask = bulletJournal.getWeek().getCurrMaxTaskCount();
    int currMaxEvent = bulletJournal.getWeek().getCurrMaxEventCount();
    errorLabel.setWrapText(true);
    if (currMaxTask > maxTasksSpinner.getValue()) {
      errorLabel.setText("Cannot lower max. There is a day that contains more tasks than allowed");
    } else if (currMaxEvent > maxEventsSpinner.getValue()) {
      errorLabel.setText("Cannot lower max. There is a day that contains more events than allowed");
    } else {
      bulletJournal.getWeek().setName(weekNameText.getText());
      bulletJournal.getWeek().setQuote(quoteText.getText());
      bulletJournal.setTheme(themeSelector.getValue());
      bulletJournal.getWeek().setMaxTasks(maxTasksSpinner.getValue());
      bulletJournal.getWeek().setMaxEvents(maxEventsSpinner.getValue());

      ControllerUtils.goToMainScreen(bulletJournalController, stage);
    }
  }

  /**
   * represents handling the back button
   */
  private void handleBack() {
    ControllerUtils.goToMainScreen(bulletJournalController, stage);
  }
}

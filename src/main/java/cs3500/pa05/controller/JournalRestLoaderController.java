package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.view.BulletJournalView;
import cs3500.pa05.view.View;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * represents loading the journal for a controller
 */
public class JournalRestLoaderController implements Controller {
  @FXML
  private AnchorPane restAnchorPane;
  @FXML
  private Button openDirectoryButton;
  @FXML
  private Button newButton;
  private FileChooser fileChooser;

  private Stage stage;
  private BulletJournal bulletJournal;
  private Controller bulletJournalController;
  @FXML
  private Button backButton;


  /**
   * constructor to instantiate a journal rest loader controller
   *
   * @param bulletJournalController the current bullet journal controller
   * @param stage                   the stage to set the scene
   * @param bulletJournal           the current bullet journal
   */
  public JournalRestLoaderController(Controller bulletJournalController, Stage stage,
                                     BulletJournal bulletJournal) {
    this.stage = stage;
    fileChooser = new FileChooser();
    this.bulletJournalController = bulletJournalController;
    this.bulletJournal = bulletJournal;
  }

  /**
   * represents running the journal rest loader controller
   */
  @Override
  public void run() {
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("BUJO", "*.bujo");
    fileChooser.getExtensionFilters().add(filter);
    InitializeUtils.changeAnchorPaneColors(restAnchorPane, bulletJournal);
    openDirectoryButton.setOnAction(actionEvent -> handleDirectory());
    newButton.setOnAction(actionEvent -> handleNew());
    backButton.setOnAction(event -> handleBack());
  }

  /**
   * represents handling the directory
   */
  private void handleDirectory() {
    File selected = fileChooser.showOpenDialog(stage);
    String path = selected.getPath();
    Controller bulletJournalController = new BulletJournalController(
        path, stage);
    View bulletJournalView = new BulletJournalView(
        bulletJournalController, "WeekOverview.fxml");
    ControllerUtils.handlePassword(path, bulletJournalController, bulletJournalView, stage);
  }

  /**
   * represents handling a new file
   */
  private void handleNew() {
    ControllerUtils.handlePassword("", null, null, stage);
  }

  /**
   * represents handling the back button
   */
  private void handleBack() {
    ControllerUtils.goToMainScreen(bulletJournalController, stage);
  }
}

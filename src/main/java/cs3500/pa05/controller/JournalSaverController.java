package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import java.io.File;
import java.nio.file.Path;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * represents saving the journal
 */
public class JournalSaverController implements Controller {
  @FXML
  private AnchorPane saverAnchorPane;
  @FXML
  private Button saveButton;
  @FXML
  private Button openDirectoryButton;
  @FXML
  private Label errorLabel;
  @FXML
  private Button backButton;
  private FileChooser fileChooser;
  private Stage stage;
  private File selectedFile;
  private BulletJournal bulletJournal;
  private String originalFilePath;
  private Controller bulletJournalController;

  /**
   * represents teh journal saver controller
   *
   * @param bulletJournal           represents the bullet journal
   * @param originalFilePath        represents the original file path
   * @param bulletJournalController represents the bullet journal controller
   * @param stage                   represents the stage
   */
  public JournalSaverController(BulletJournal bulletJournal,
                                String originalFilePath,
                                Controller bulletJournalController, Stage stage) {
    this.bulletJournal = bulletJournal;
    this.selectedFile = null;
    this.stage = stage;
    this.originalFilePath = originalFilePath;
    this.fileChooser = new FileChooser();
    this.bulletJournalController = bulletJournalController;
  }

  /**
   * represents running journal saver controller
   */
  @Override
  public void run() {
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("BUJO", "*.bujo");
    fileChooser.getExtensionFilters().add(filter);
    InitializeUtils.changeAnchorPaneColors(saverAnchorPane, bulletJournal);
    openDirectoryButton.setOnAction(actionEvent -> handleDirectory());
    saveButton.setOnAction(actionEvent -> handleSave());
    backButton.setOnAction(event -> handleBack());
  }

  /**
   * represents handling directory
   */
  private void handleDirectory() {
    this.selectedFile = fileChooser.showSaveDialog(stage);
  }

  /**
   * represents handling the save button
   */
  private void handleSave() {
    if (this.selectedFile != null) {
      bulletJournal.saveFile(selectedFile);
      bulletJournal.setFilePath(selectedFile.getPath());
      ControllerUtils.goToMainScreen(bulletJournalController, stage);
    } else if (originalFilePath != null) {
      bulletJournal.saveFile(Path.of(originalFilePath).toFile());
      ControllerUtils.goToMainScreen(bulletJournalController, stage);
    } else {
      errorLabel.setText("Please select a file and try again");
    }
  }

  /**
   * represents handling the back button
   */
  private void handleBack() {
    ControllerUtils.goToMainScreen(bulletJournalController, stage);
  }
}

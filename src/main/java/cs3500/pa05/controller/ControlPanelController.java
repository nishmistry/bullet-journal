package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.view.AddChooserView;
import cs3500.pa05.view.JournalRestLoaderView;
import cs3500.pa05.view.JournalSaverView;
import cs3500.pa05.view.SettingsView;
import cs3500.pa05.view.View;
import javafx.stage.Stage;

/**
 * represents the control panel controller
 */
public class ControlPanelController implements Controller {
  private String imageId;
  private BulletJournal bulletJournal;
  private Stage stage;
  private String filePath;
  private Controller bulletJournalController;


  /**
   * the constructor to instantiate a control panel controller.
   *
   * @param imageId       a string of the imageid selected
   * @param bulletJournal the current bullet journal
   * @param stage         the stage
   * @param filePath      the file path of the bullet journal
   * @param controller    the bullet journal controller
   */
  public ControlPanelController(String imageId, BulletJournal bulletJournal, Stage stage,
                                String filePath, Controller controller) {
    this.imageId = imageId;
    this.bulletJournal = bulletJournal;
    this.stage = stage;
    this.filePath = filePath;
    this.bulletJournalController = controller;
  }

  @Override
  public void run() {
    handleClick(imageId);
  }

  private void handleClick(String imageId) {
    switch (imageId) {
      case "save":
        Controller journalSaver =
            new JournalSaverController(bulletJournal, filePath, bulletJournalController, stage);
        View journalSaverView = new JournalSaverView(journalSaver, "JournalSaver.fxml");
        stage.setScene(journalSaverView.load());
        journalSaver.run();
        stage.show();
        stage.centerOnScreen();
        break;
      case "load":
        Controller journalLoader =
            new JournalRestLoaderController(bulletJournalController, stage, bulletJournal);
        View journalLoaderView = new JournalRestLoaderView(journalLoader, "JournalRestLoader.fxml");
        stage.setScene(journalLoaderView.load());
        journalLoader.run();
        stage.show();
        stage.centerOnScreen();
        break;
      case "setting":
        Controller settings = new SettingsController(bulletJournal, bulletJournalController, stage);
        View settingsView = new SettingsView(settings, "Settings.fxml");
        stage.setScene(settingsView.load());
        settings.run();
        stage.show();
        stage.centerOnScreen();
        break;
      case "add":
        Controller chooser =
            new AddChooserController(bulletJournal, bulletJournalController, stage);
        View addChooserView = new AddChooserView(chooser, "AddChooser.fxml");
        stage.setScene(addChooserView.load());
        chooser.run();
        stage.show();
        stage.centerOnScreen();
        break;
      default:
        System.err.println("Unknown image clicked.");
        break;
    }
  }
}

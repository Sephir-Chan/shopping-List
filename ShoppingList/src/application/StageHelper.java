package application;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Eine Helper Klasse für die einzelnen Container und Szenen. Die Objekte werden
 * hier zentral organisiert und dafür gesorgt, dass kein Fenster zweimal
 * geöffnet wird.
 * 
 * @author Jasmin Siemes
 *
 */
public class StageHelper {

	private Stage editStage;
	private Stage mainStage;
	private Scene mainScene;
	private Scene editScene;

	/**
	 * Wenn noch kein Container für das Editierfenster wurde wird er erzeugt und
	 * anschließend zurück gegeben.
	 * 
	 * @param root
	 *            - Das AnchorPane des Hauptfenters
	 * @return Die {@link Stage} für das Editierfenster wird zurück gegeben
	 */
	public Stage getInstanceEditStage(AnchorPane root) {
		if (editStage == null) {
			constructStage(root);
		}
		return editStage;
	}

	/**
	 * Wenn das Editierfenster noch nicht angezeigt wird, wird der Titel gesetzt
	 * und das Fenster angezeigt. Wenn das Fenster bereits angezeigt wird, wird
	 * es kein zweites Mal geöffnet.
	 * 
	 * @param title
	 *            - Titel des Fensters
	 */
	public void showEditStage(String title) {
		if (editStage.isShowing()) {
			return;
		}
		editStage.setTitle(title);
		editStage.showAndWait();
	}

	/**
	 * Wenn noch kein Container für das Hauptfenster gesetzt wurde wird dieser
	 * gesetzt und die Szene wird erzeugt. Anschließend wird der Titel gesetzt
	 * und das Fenster sichtbar gemacht. Diese Funktionen werden auch ausgeführt
	 * wenn der Container bereits gesetzt wurde.
	 * 
	 * @param mainStage
	 *            - Hauptfenster
	 * @param root
	 *            - Das AnchorPane des Hauptfenters
	 */
	public void instantiateMainStage(Stage mainStage, AnchorPane root) {
		if (this.mainStage == null) {
			this.mainStage = mainStage;
			constructMainStage(root);
		}
		this.mainStage.setTitle("Einkaufsliste");
		this.mainStage.show();
	}

	/**
	 * Die Szene für das Hauptfenster wird erzeugt und gesetzt
	 * 
	 * @param root
	 *            - Das AnchorPane des Hauptfenters
	 */
	private void constructMainStage(AnchorPane root) {
		mainScene = new Scene(root);
		mainStage.setScene(mainScene);
	}

	/**
	 * Der Container für den Editierdialog wird erstellt
	 * 
	 * @param root
	 *            - Das AnchorPane des Hauptfenters
	 */
	private void constructStage(AnchorPane root) {
		editStage = new Stage();
		editStage.initModality(Modality.WINDOW_MODAL);
		editStage.initOwner(mainStage);
		editScene = new Scene(root, 350, 225);
		editStage.setScene(editScene);
	}

	/* Getter */

	public Stage getEditStage() {
		return editStage;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public Scene getMainScene() {
		return mainScene;
	}

	public Scene getEditScene() {
		return editScene;
	}
}

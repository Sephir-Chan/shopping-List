package application;

import java.io.IOException;

import application.controller.EditController;
import application.controller.MainController;
import application.listener.EditHandler;
import application.listener.ListenerHelper;
import application.listener.MainStageHandler;
import application.model.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Diese Klasse dient als Hauptklasse der Anwendung "Einlaufsliste". In ihr ist
 * die Main Methode zum starten vorhanden. Alle wichtigen Bestanteile der
 * Anwendung werden in dieser Klasse initialisiert.
 * 
 * @author Jasmin Siemes
 *
 */
public class Main extends Application {

	private EditController editController;
	private MainController mainController;
	private ObservableList<Product> products;
	private StageHelper helper;

	@Override
	public void start(Stage primaryStage) {
		try {
			products = FXCollections.observableArrayList();
			helper = new StageHelper();

			initiateMainDialog(primaryStage);
			initiateEditDialog();
			addKeyListenerToMainDialog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Der Hauptdialog wird initialisiert. Für das Aussehen wird das FXML
	 * ShoppingList verwendet. Im StageHelper werden alle benötigten Szenen
	 * erstellt. Der Controller für den Hauptdialog wird geladen.
	 * 
	 * @param primaryStage
	 *            - Hauptdialog
	 * @throws IOException
	 *             - Beim laden des AnchorPanes kann es zu einer Exception
	 *             kommen
	 */
	private void initiateMainDialog(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/controller/ShoppingList.fxml"));
		helper.instantiateMainStage(primaryStage, loader.load());
		mainController = loader.getController();
		mainController.setMain(this);
	}

	/**
	 * Editierdialog wird geladen Es wird ein seperater Controller für den
	 * Editierdialog erstellt
	 * 
	 * @throws IOException
	 *             - Beim laden des AnchorPanes kann es zu einer Exception
	 *             kommen
	 */
	private void initiateEditDialog() throws IOException {
		// Initialisieren des Editier- und Hinzufügungsdialogs
		FXMLLoader editLoader = new FXMLLoader();
		editLoader.setLocation(getClass().getResource("/application/controller/Edit.fxml"));
		helper.getInstanceEditStage(editLoader.load());
		editController = editLoader.getController();
		editController.setMain(this);
	}

	/**
	 * Damit man die Oberfläche auch mit der Tastatur bedienen kann werden
	 * verschiedene Listener hinzugefügt.
	 */
	private void addKeyListenerToMainDialog() {
		MainStageHandler handler = new MainStageHandler();
		handler.setMain(this);

		mainController.getAdd().addEventHandler(KeyEvent.KEY_PRESSED, handler);
		mainController.getEdit().addEventHandler(KeyEvent.KEY_PRESSED, handler);
		mainController.getDelete().addEventHandler(KeyEvent.KEY_PRESSED, handler);

		// Hauptfenster Listener
		helper.getMainScene().addEventHandler(KeyEvent.KEY_RELEASED, handler);
		helper.getMainScene().getAccelerators().putAll(ListenerHelper.getKeyCombinations(handler));

		EditHandler editHandler = new EditHandler();
		editHandler.setMain(this);
		
		// Editierfenster Listener
		helper.getEditScene().addEventHandler(KeyEvent.KEY_RELEASED, editHandler);
		helper.getEditScene().getAccelerators().put(ListenerHelper.getKeyCombination(), editHandler.getRunnableSave());
	}

	/* Getter */

	public ObservableList<Product> getProductData() {
		return products;
	}

	public StageHelper getStageHelper() {
		return helper;
	}

	public EditController getEditController() {
		return editController;
	}

	public MainController getController() {
		return mainController;
	}

	/**
	 * Main-Methode
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

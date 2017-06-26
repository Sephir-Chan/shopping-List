package application.controller;

import application.AlertBuilder;
import application.Constants;
import application.Main;
import application.model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

/**
 * Controller für das Hauptfenster
 * 
 * @author Jasmin Siemes
 *
 */
public class MainController {

	private Main main;

	/* Buttons und Tabelle auf der Oberfläche */

	@FXML
	private Button addButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;

	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, String> productName;
	@FXML
	private TableColumn<Product, Integer> productCount;

	/**
	 * Die Tabelle für die Produkte wird initialisiert. Es werden
	 * CellValueFactories für die Tabellenspalten gesetzt. Für die Spalte
	 * productName wird der Name vom {@link Product} und für die Spalte
	 * productCount die Anzahl von {@link Product} genutzt.
	 */
	@FXML
	public void initialize() {
		productName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		productCount.setCellValueFactory(cellData -> cellData.getValue().getCountProperty().asObject());
		productTable.setRowFactory(tv -> {
			TableRow<Product> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					edit();
				}
			});
			return row;
		});
	}

	/**
	 * Mit dem Betätigen des addButtons wird diese Methode ausgeführt. Es wird
	 * eine neues Objekt von {@link Product} erzeugt und im Editierfenster
	 * gesetzt. Das Editierfenster wird geöffnet, dort können die Werte für Name
	 * und Anzahl eingetragen werden. Wenn der Nutzer die Informationen
	 * gespeichert hat wird geprüft ob bereits ein {@link Product} mit der
	 * gleichen Bezeichnung in der Tabelle steht. Wenn ja wird die Anzahl
	 * addiert, wenn nicht wird das {@link Product} der {@link ObservableList}
	 * hinzugefügt. isSaved wird im Editierfenster wieder aus <code>false</code>
	 * gesetzt. Anschließend werden die Objekte der
	 */
	@FXML
	public void add() {
		Product newProduct = new Product();
		main.getEditController().setProduct(newProduct);
		main.getStageHelper().showEditStage("Hinzufügen");
		if (main.getEditController().isSaved()) {
			int index = main.getProductData().indexOf(newProduct);
			if (index >= 0) {
				main.getProductData().get(index).addToCount(newProduct.getCount().intValue());
			} else {
				main.getProductData().add(newProduct);
			}
			main.getEditController().resetIsSaved();
			productTable.setItems(main.getProductData());
		}
	}

	/**
	 * Mit dem Betätigen des editButtons wird diese Methode ausgeführt. Das
	 * ausgewählte Object wird aus der Tabelle geholt, wenn es nicht null ist
	 * wird das Editierfenster geöffnet und das {@link Product} gesetzt. Wenn es
	 * null gibt es eine Meldung, das ein {@link Product} ausgewählt werden
	 * muss.
	 */
	@FXML
	public void edit() {
		Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
		if (selectedProduct != null) {
			main.getEditController().setProduct(selectedProduct);
			main.getStageHelper().showEditStage("Editieren");
		} else {
			getAlertNoSelection().showAndWait();
		}
	}

	/**
	 * Mit dem Betätigen deseditButtons wird diese Methode ausgeführt. Der Index
	 * der seletktierten Zeile wird ermittelt. Wenn eine gültige Zeile
	 * ausgewählt wurde (Index >= 0) wird die entsprechende Zeile gelöscht.
	 * Ansonsten gibt es eine Meldung, das ein {@link Product} ausgewählt werden
	 * muss.
	 */
	@FXML
	public void delete() {
		int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			productTable.getItems().remove(selectedIndex);
		} else {
			getAlertNoSelection().showAndWait();
		}
	}

	private Alert getAlertNoSelection() {
		AlertBuilder builder = new AlertBuilder();
		builder.setButtonTypes(ButtonType.OK, ButtonType.CANCEL);
		builder.setContent(Constants.PLEASE_SELECT);
		builder.setHeader(Constants.NO_PRODUCT_SELECTED);
		builder.setOwner(main.getStageHelper().getMainStage());
		builder.setTitle(Constants.NO_SELECTION);
		builder.setType(AlertType.WARNING);
		return builder.getAlert();
	}

	/* Getter und Setter */

	public Button getAdd() {
		return addButton;
	}

	public Button getEdit() {
		return editButton;
	}

	public Button getDelete() {
		return deleteButton;
	}

	public void setMain(Main main) {
		this.main = main;
	}
}

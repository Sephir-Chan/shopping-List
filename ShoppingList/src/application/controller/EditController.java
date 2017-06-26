package application.controller;

import application.AlertBuilder;
import application.Constants;
import application.Main;
import application.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller für das Editierfenster
 * Das {@link Product}, dass gerade erstellt/bearbeitet wird und ob dieses gespeichert wurde werden als Variablen gespeichert.
 * 
 * @author Jasmin Siemes
 *
 */
public class EditController {

	private Main main;

	private Product product;
	private boolean isSaved;

	/* Buttons und Textfelder auf der Oberfläche */
	
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TextField productName;
	@FXML
	private TextField productCount;

	@FXML
	public void initialize() {
		isSaved = false;
	}

	/**
	 * Die Variablen aus dem {@link Product} werden in die Felder der Oberfläche gesetzt
	 * 
	 * @param product - Das zu editierende {@link Product}
	 */
	private void setProductInStage(Product product) {
		if (product != null) {
			productName.setText(product.getName());
			productCount.setText(product.getCount().toString());
		}
	}

	/**
	 * Das Editierfenster wird geschlossen
	 */
	public void cancel() {
		main.getStageHelper().getEditStage().close();
	}

	/**
	 * Der Name und die Anzahl werden in Variablen geschrieben und geprüft. Wenn die Einträge nicht gültig sind 
	 * wird der Vorgang abgebrochen.
	 * Ansonsten wird die Anzahl in einen int geparst und in das {@link Product} gesetzt.
	 * isSaved wird auf <code>true</code> gesetzt und das Editierfenster geschlossen.
	 */
	public void save() {
		String name = productName.getText();
		String countString = productCount.getText();
		if (!isEntryValid(name, countString)) {
			return;
		}
		int count = Integer.parseInt(countString);
		product.setName(name);
		product.setCount(count);
		isSaved = true;
		main.getStageHelper().getEditStage().close();
	}

	/**
	 * Es wird geprüft ob die Eingaben in Ordnung sind.
	 * Wenn beide Felder leer sind oder im Anzahlfeld keine reine Zahl steht wird eine Meldung ausgegeben 
	 * und <code>false</code> zurückgegeben.
	 * Wenn beide Felder geüllt und im Anzahlfeld nur Zahlen stehen wird <code>true</code> zurückgegeben.
	 * 
	 * @param name - Der Name des {@link Product}
	 * @param countString - Anzahl des {@link Product} als String
	 * @return wenn der Eintrag gültig ist <code>true</code>, sonst <code>false</code>
	 */
	private boolean isEntryValid(String name, String countString) {
		StringBuilder error = new StringBuilder();
		if (name == null || name.isEmpty()) {
			error.append(Constants.NO_PRODUCT_NAME);
			error.append(Constants.LINEBREAK);
		}
		try {
			Integer.parseInt(countString);
		} catch (NumberFormatException e) {
			error.append(Constants.NO_PRODUCT_COUNT);

		}
		if (error.length() > 0) {
			AlertBuilder builder = new AlertBuilder();
			builder.setContent(Constants.PLEASE_ENTER_ENTRIES);
			builder.setHeader(error.toString());
			builder.setOwner(main.getStageHelper().getEditStage());
			builder.setTitle(Constants.INVALID_ENTRIES);
			builder.setType(AlertType.WARNING);
			builder.getAlert().showAndWait();
			return false;
		}
		return true;
	}
	
	/* Setter und Getter */

	public void setMain(Main main) {
		this.main = main;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		setProductInStage(product);
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public TextField getProductName() {
		return productName;
	}

	public TextField getProductCount() {
		return productCount;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void resetIsSaved() {
		isSaved = false;
	}
}

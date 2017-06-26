package application.listener;

import java.util.Optional;

import application.AlertBuilder;
import application.Constants;
import application.Main;
import application.controller.EditController;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handler für das Editierfenster
 * 
 * @author Jasmin Siemes
 *
 */
public class EditHandler implements EventHandler<KeyEvent> {

	private Main main;

	/**
	 * {@link KeyEvent} für das Editierfenster. Es wird auf die Eingabe von
	 * Enter und Escape reagiert. Andere Tasten werden nicht mit diesem Listener
	 * unterstützt.
	 */
	@Override
	public void handle(KeyEvent event) {
		KeyCode key = event.getCode();
		switch (key) {
		case ENTER:
			handleKeyEvents();
			break;
		case ESCAPE:
			handleEscape();
			break;
		default:
			break;
		}
	}

	/**
	 * Wenn der saveButton aus {@link EditController} fokussiert ist werden die
	 * Eingaben im Editierfesnter gespeichert. Wenn der cancelButton aus
	 * {@link EditController} fokussiert ist wird das Editierfenster
	 * geschlossen.
	 */
	private void handleKeyEvents() {
		if (main.getEditController().getSaveButton().isFocused()) {
			main.getEditController().save();
		} else if (main.getEditController().getCancelButton().isFocused()) {
			handleEscape();
		}
	}

	/**
	 * Wenn das Namens- oder Anzahlfeld nicht leer ist wird eine Meldung
	 * angezeigt, die darauf hinweist. Wenn man mit 'OK' bestätigt wird das
	 * Fenster ohne die Eingaben zu speichern geschlossen. Ansonsten wird nur
	 * die Meldung geschlossen. Wenn beide Felder leer sind wird der Dialog
	 * geschlossen.
	 */
	private void handleEscape() {
		if (!productNameIsEmpty() || !productCountIsEmpty()) {
			AlertBuilder builder = new AlertBuilder();
			builder.setButtonTypes(ButtonType.OK, ButtonType.CANCEL);
			builder.setContent("");
			builder.setHeader(Constants.ENTRIES_MADE + Constants.LINEBREAK + Constants.ASK_CLOSE);
			builder.setOwner(main.getStageHelper().getMainStage());
			builder.setTitle(Constants.CLOSE);
			builder.setType(AlertType.CONFIRMATION);
			Optional<ButtonType> option = builder.getAlert().showAndWait();
			if (option.get() == ButtonType.OK) {
				main.getStageHelper().getEditStage().close();
			}
		} else {
			main.getStageHelper().getEditStage().close();
		}

	}

	/* Setter */

	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Es wird geprüft ob das Namensfeld leer ist.
	 * 
	 * @return wenn der Inhalt des Textfeldes null oder leer ist
	 *         <code>true</code> ansonsten <code>false</code>
	 */
	private boolean productNameIsEmpty() {
		return main.getEditController().getProductName().getText() == null
				|| main.getEditController().getProductName().getText().isEmpty();
	}

	/**
	 * Es wird geprüft ob das Anzahlfeld leer ist.
	 * 
	 * @return wenn der Inhalt des Textfeldes null oder leer ist
	 *         <code>true</code> ansonsten <code>false</code>
	 */
	private boolean productCountIsEmpty() {
		return main.getEditController().getProductCount().getText() == null
				|| main.getEditController().getProductCount().getText().isEmpty();
	}

	/**
	 * 
	 * @return
	 */
	public Runnable getRunnableSave() {
		return new Runnable() {

			@Override
			public void run() {
				main.getEditController().save();
			}
		};
	}
}

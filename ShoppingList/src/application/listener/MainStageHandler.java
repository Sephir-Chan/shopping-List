package application.listener;

import java.util.Optional;

import application.AlertBuilder;
import application.Constants;
import application.Main;
import application.controller.MainController;
import application.model.Product;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handler f�r das Hauptfenster
 * 
 * @author Jasmin Siemes
 *
 */
public class MainStageHandler implements EventHandler<KeyEvent> {

	private Main main;

	/**
	 * {@link KeyEvent} f�r das Hauptfenster. Es wird auf die Eingabe von Enter
	 * und Escape reagiert. Andere Tasten werden nicht mit diesem Listener
	 * unterst�tzt.
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
	 * Wenn der addButton aus {@link MainController} fokussiert ist wird das
	 * Editierfenster zum Hinzuf�gen ge�ffnet. Wenn der editButton aus
	 * {@link MainController} fokussiert ist wird das Editierfenster zum
	 * Editieren ge�ffnet. Wenn der deleteButton aus {@link MainController}
	 * fokussiert ist wird die ausgew�hlte Zeile gel�scht.
	 */
	private void handleKeyEvents() {
		if (main.getController().getAdd().isFocused()) {
			main.getController().add();
		} else if (main.getController().getEdit().isFocused()) {
			main.getController().edit();
		} else if (main.getController().getDelete().isFocused()) {
			main.getController().delete();
		}
	}

	/**
	 * Wenn die Escape Taste gedr�ckt wurde wird eine Best�tigung verlangt bevor
	 * das Fenster geschlossen wird. Wenn 'Abbrechen' gew�hlt wird passiert
	 * nichts.
	 */
	private void handleEscape() {
		AlertBuilder builder = new AlertBuilder();
		builder.setButtonTypes(ButtonType.OK, ButtonType.CANCEL);
		builder.setHeader(Constants.ASK_CLOSE);
		builder.setOwner(main.getStageHelper().getMainStage());
		builder.setTitle(Constants.CLOSE);
		builder.setType(AlertType.CONFIRMATION);
		Optional<ButtonType> option = builder.getAlert().showAndWait();
		if (option.get() == ButtonType.OK) {
			main.getStageHelper().getMainStage().close();
		}
	}

	/* Getter */

	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Es wird ein {@link Runnable} erzeugt, dass das Hinzuf�gen von
	 * {@link Product} erm�glicht, wenn eine bestimmte Tastenkombination
	 * get�tigt wird.
	 * 
	 * @return {@link Runnable} f�r das Hinzuf�gen von {@link Product} in die
	 *         Tabelle
	 */
	public Runnable getRunnableAdd() {
		return new Runnable() {

			@Override
			public void run() {
				main.getController().add();
			}
		};
	}

	/**
	 * Es wird ein {@link Runnable} erzeugt, dass das Editieren von
	 * {@link Product} erm�glicht, wenn eine bestimmte Tastenkombination
	 * get�tigt wird.
	 * 
	 * @return {@link Runnable} f�r das Editieren von {@link Product} in der
	 *         Tabelle
	 */
	public Runnable getRunnableEdit() {
		return new Runnable() {

			@Override
			public void run() {
				main.getController().edit();
			}
		};
	}

	/**
	 * Es wird ein {@link Runnable} erzeugt, dass das L�schen von
	 * {@link Product} erm�glicht, wenn eine bestimmte Tastenkombination
	 * get�tigt wird.
	 * 
	 * @return {@link Runnable} f�r das L�schen von {@link Product} aus der
	 *         Tabelle
	 */
	public Runnable getRunnableDelete() {
		return new Runnable() {

			@Override
			public void run() {
				main.getController().delete();
			}
		};
	}
}

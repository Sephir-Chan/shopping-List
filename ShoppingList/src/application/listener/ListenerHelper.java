package application.listener;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * Eine Helper Klasse für die Listener der Oberflächen
 * 
 * @author Jasmin Siemes
 *
 */
public class ListenerHelper {

	/**
	 * Es werden mehrere {@link KeyCombination} erzeugt. 
	 * 'a' + STRG -> Hinzufügen (Add) 
	 * 'e' + STRG -> Bearbeiten (Edit) 
	 * 'd' + STRG -> Löschen (delete) 
	 * Die {@link KeyCombination} werden einer Map hinzugefügt und
	 * zurück gegeben.
	 * 
	 * @param handler
	 *            - Handler für den Hauptcontainer
	 * @return Eine Map von {@link KeyCombination} und {@link Runnable} wird
	 *         zurückgegeben
	 */
	public static Map<KeyCodeCombination, Runnable> getKeyCombinations(MainStageHandler handler) {
		Map<KeyCodeCombination, Runnable> keyCodesMain = new HashMap<>();
		KeyCodeCombination combiAdd = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_ANY);
		KeyCodeCombination combiEdit = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_ANY);
		KeyCodeCombination combiDelete = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_ANY);
		keyCodesMain.put(combiAdd, handler.getRunnableAdd());
		keyCodesMain.put(combiEdit, handler.getRunnableEdit());
		keyCodesMain.put(combiDelete, handler.getRunnableDelete());
		return keyCodesMain;
	}

	/**
	 * Es wird eine {@link KeyCombination} erzeugt, die auf die Kombination von
	 * 's' und einer beliebigen Steuerungstaste reagiert.
	 * 
	 * @return eine {@link KeyCombination} wird zurückgegeben
	 */
	public static KeyCodeCombination getKeyCombination() {
		return new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY);
	}
}

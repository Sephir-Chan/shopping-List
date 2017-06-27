package application.model;

import application.Constants;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Objektklasse für ein Produkt Jedes Produkt hat einen Namen und eine Anzahl
 * 
 * @author Jasmin Siemes
 *
 */
public class Product {

	private final SimpleStringProperty name;
	private final SimpleIntegerProperty count;

	public Product() {
		this.name = new SimpleStringProperty(null);
		this.count = new SimpleIntegerProperty();
	}

	/* Getter und Setter */

	public StringProperty getNameProperty() {
		return name;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public IntegerProperty getCountProperty() {
		return count;
	}

	public Integer getCount() {
		return count.get();
	}

	public void setCount(int count) {
		this.count.set(count);
	}

	public void addToCount(int count) {
		int tempCount = this.count.add(count).intValue();
		this.count.set(tempCount);
	}

	/**
	 * Dieses Objekt wird mit einem übergebenen Objekt verglichen, wenn es sich
	 * bei dem übergebenen Objekt um ein {@link Product} handelt, bei beiden der
	 * Name gesetzt ist und dieser identisch ist wird <code>true</code> zurück
	 * gegeben, ansonsten <code>false</code>
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product otherProduct = (Product) obj;
			if (this.getName() != null && otherProduct.getName() != null
					&& this.getName().equals(otherProduct.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return Constants.PRODUCT_NAME + Constants.SEPARATOR_COLON + this.getName();
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		result = 31 * result + this.getName().hashCode();
		result = 31 * result + this.getCount().hashCode();
		return result;
	}
}
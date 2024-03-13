package ch.proco.objFilter.tools;

/**
 * Filtres clé / valeur
 */
public class FiltreElem {
	
	private int idField;
	private String value;

	public FiltreElem() {
	}

	public FiltreElem(int idField, String value) {
		this.idField = idField;
		this.value = value;
	}

	public int getIdField() {
		return idField;
	}
	public void setIdField(int idField) {
		this.idField = idField;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}

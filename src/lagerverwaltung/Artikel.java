package lagerverwaltung;

/**
 * Ein Produkt, das entweder in das Lager hinzugef&uuml;gt, oder durch eine Bestellung dort
 * entnommen werden soll,
 */
public class Artikel {

	private final String id;
	private String name;
	private String beschreibung;

	/**
	 * Erstellt einen neuen Artikel.
	 * 
	 * @param id           - Die ID des Artikels.
	 * @param name         - Der Name des Artikels.
	 * @param beschreibung - Die Beschreibung des Artikels.
	 */
	public Artikel(String id, String name, String beschreibung) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Artikel#" + id + "[" + name + " ~ " + beschreibung + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Artikel)) {
			return false;
		}
		Artikel artikel = (Artikel) obj;
		if(!(id.equals(artikel.id))) {
			return false;
		}
//		if(!(name.equals(artikel.name))) {
//			return false;
//		}
//		if(!(name.equals(artikel.name))) {
//			return false;
//		}
		return true;
	}
}

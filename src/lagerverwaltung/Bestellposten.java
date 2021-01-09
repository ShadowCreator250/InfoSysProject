package lagerverwaltung;

/**
 * Ein einzelner Posten, der zu einer Bestellung hinzugef&uuml;gt werden kann.
 */
public class Bestellposten {

	private final String artikelId;
	private int anzahl;

	/**
	 * Erstellt einen Posten für eine Bestellung.
	 * 
	 * @param artikelId - Die ID des {@link Artikel}s, der bestellt werden soll.
	 * @param anzahl    - Die Anzahl des {@link Artikel}s, wie oft er bestellt werden soll.
	 */
	public Bestellposten(String artikelId, int anzahl) {
		this.artikelId = artikelId;
		this.anzahl = anzahl;
	}

	public String getArtikelId() {
		return artikelId;
	}

	public int getAnzahl() {
		return anzahl;
	}

	@Override
	public String toString() {
		return "Bestellposten#" + artikelId + " ~ " + anzahl + "x";
	}

}

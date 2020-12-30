package lagerverwaltung;

public class Bestellposten {

	private final String artikelId;
	private int anzahl;

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

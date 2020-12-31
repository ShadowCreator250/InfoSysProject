package lagerverwaltung;

/**
 * Ein einzelner Posten, der den Bestand des Lagers von einem {@link Artikel} anzeigt. <br>
 * Der Posten muss erst in das Lager hinzugefügt werden.
 */
public class Lagerposten {

	private Artikel artikel;
	private int lagerbestand;
	private double preis;

	/**
	 * Erstellt einen Lagerposten, der im Lager gelagert werden soll.
	 * 
	 * @param artikel - Der {@link Artikel}, der in das Lager hinzugefügt werden soll.
	 * @param lagerbestand - Die Stückzahl des {@link Artikel}s.
	 * @param preis - Der Preis für einen {@link Artikel}.
	 */
	public Lagerposten(Artikel artikel, int lagerbestand, double preis) {
		this.artikel = artikel;
		this.lagerbestand = lagerbestand;
		this.preis = preis;
	}

	public int getLagerbestand() {
		return lagerbestand;
	}

	public void setLagerbestand(int lagerbestand) {
		this.lagerbestand = lagerbestand;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	@Override
	public String toString() {
		return "Lagerposten{" + artikel.toString() + " | Stück: " + lagerbestand + " | Preis: " + preis + "}";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Lagerposten)) {
			return false;
		}
		Lagerposten posten = (Lagerposten) obj;
		if(lagerbestand != posten.lagerbestand) {
			return false;
		}
		if(preis != posten.preis) {
			return false;
		}
		if(!(artikel.equals(posten.artikel))) {
			return false;
		}
		return true;
	}

}

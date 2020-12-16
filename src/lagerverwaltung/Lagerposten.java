package lagerverwaltung;

public class Lagerposten {

	private Artikel artikel;
	private int lagerbestand;
	private double preis;

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

}

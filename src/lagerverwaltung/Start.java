package lagerverwaltung;

public class Start {

	public static void main(String[] args) {
		Lagerverwaltung verwaltung = new Lagerverwaltung();
		Mitarbeiter bernd = new Mitarbeiter("BeM202", "Bernd Müller");
		Mitarbeiter hans = new Mitarbeiter("HaB084", "Hans Bieder");
		Mitarbeiter anna = new Mitarbeiter("AnR958", "Anne Richter");
		Mitarbeiter duke = new Mitarbeiter("DuM284", "Duke McCry");
		Mitarbeiter marie = new Mitarbeiter("MaF439", "Marie Fischer");
		Artikel schrauben = new Artikel("schrauben-13", "Schrauben", "Schrauben mit Gewindegröße 13");
		Lagerposten schraubenPosten = new Lagerposten(schrauben, 75, 0.25);

		verwaltung.berechtigungErteilen(bernd);
		verwaltung.berechtigungErteilen(hans);
		verwaltung.berechtigungErteilen(anna);
		verwaltung.berechtigungErteilen(duke);
		verwaltung.berechtigungErteilen(marie);
		verwaltung.berechtigungZurueckziehen(duke);

	}

}

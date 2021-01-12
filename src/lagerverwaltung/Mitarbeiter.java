package lagerverwaltung;

/**
 * Eine Person, die das Lager und alle damit verbundenen Aufgaben verwaltet.
 */
public class Mitarbeiter {

	private final String id;
	private String name;

	/**
	 * Fügt einen neuen Mitarbeiter f&uuml;r das Lager hinzu.
	 * 
	 * @param id   - Die ID des neuen Mitarbeiters.
	 * @param name - Der Name des neuen Mitarbeiters.
	 */
	public Mitarbeiter(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Mitarbeiter#" + id + "[" + name + "]";
	}

}

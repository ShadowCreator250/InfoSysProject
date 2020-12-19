package lagerverwaltung;

public class Mitarbeiter {

	private final String id;
	private String name;

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

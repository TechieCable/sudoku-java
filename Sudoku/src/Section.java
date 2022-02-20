public class Section {
	Tile[] tiles = new Tile[9];

	public Section(Tile[] t) {
		tiles = t;
	}

	public Value missing() {
		Value values = new Value();
		for (Tile t : tiles) {
			values.remove(t.value);
		}
		return values;
	}

	public int[] has() {
		int[] values = new int[9];
		for (int i = 0; i < 9; i++) {
			values[i] = tiles[i].value;
		}
		return values;
	}

	public String toString() {
		String res = "";
		for (int i = 0; i < tiles.length; i++) {
//			res += tiles[i].toString() + (i != 8 ? ", " : "");
			res += tiles[i].value + (i != 8 ? ", " : "");
		}
		return res;
	}

}

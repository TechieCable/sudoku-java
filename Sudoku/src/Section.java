public class Section {
	Tile[] tiles = new Tile[9];

	public Section(Tile[] t) {
		tiles = t;
	}

	public int[] has() {
		int[] values = new int[9];
		for (int i = 0; i < 9; i++) {
			values[i] = tiles[i].value;
		}
		return values;
	}

	public boolean has(int n) {
		return new Value(has()).contains(n);
	}

	public String toString() {
		String res = "";
		for (int i = 0; i < tiles.length; i++) {
			res += tiles[i].toString() + (i != 8 ? ", " : "");
		}
		return res;
	}

	public void highlight(boolean x) {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].highlight(x);
			tiles[i].active(false);
		}
	}

	public void active(boolean x) {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].active(x);
			tiles[i].highlight(false);
		}
	}

	public boolean duplicate() {
		int[] has = has();
		for (int i = 0; i < has.length; i++) {
			for (int j = i + 1; j < has.length; j++) {
				if (has[i] != 0 && has[i] == has[j]) {
					return true;
				}
			}
		}
		return false;
	}

}

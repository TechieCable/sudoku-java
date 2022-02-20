import java.util.ArrayList;

public class Value {
	private ArrayList<Integer> values = new ArrayList<Integer>();

	public Value() {
		for (int i = 1; i < 10; i++) {
			values.add(i);
		}
	}

	public int size() {
		return values.size();
	}

	public int remove(int n) {
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) == n) {
				return values.remove(i);
			}
		}
		return 0;
	}

	public void remove(int[] n) {
		for (int num : n) {
			remove(num);
		}
	}

	public boolean contains(int n) {
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) == n) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return values.toString();
	}

	public int get() {
		if (values.size() == 0) {
			return 0;
		}
		return values.get((int) (Math.random() * values.size()));
	}

}

package me.cobble.aeroglow;

import java.util.ArrayList;
import java.util.List;

public class BaseCompleter { // NO_UCD (unused code)
	private static Object[] arr;

	public BaseCompleter(Object[] a) {
		BaseCompleter.arr = a;
	}

	public List<String> run() {
		List<String> list = new ArrayList<>();
		for (Object s : arr) {
			list.add(s.toString());
		}
		return list;

	}

}

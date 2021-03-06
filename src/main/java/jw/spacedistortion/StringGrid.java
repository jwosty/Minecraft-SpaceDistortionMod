package jw.spacedistortion;

import java.util.Iterator;

public class StringGrid {
	private String[] strings;
	public int width;
	public int height;
	
	public StringGrid(String... strings) {
		this.strings = strings;
		this.width = this.findWidth();
		this.height = strings.length;
	}
	
	private int findWidth() {
		int w = 0;
		for (int i = 0; i < strings.length; i++) {
			int rowW = strings[i].length();
			if (w < rowW) {
				w = rowW;
			}
		}
		return w;
	}

	// Retrieves the character at the given coordinates
	public char get(int x, int y) {
		try {
			return strings[y].charAt(x);
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			return ' ';
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			return ' ';
		}
	}
}
package sap.pixelart.dashboard.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import sap.pixelart.core.domain.PixelGridSnapshot;

/**
 * 
 * Model of the Dashboard View
 * 
 */
public class PixelArtViewModel {
	private int nRows;
	private int nColumns;
	private int[][] grid; 
	private List<PixelArtViewModelListener> listeners;
	
	public PixelArtViewModel(PixelGridSnapshot snap) {
		this.nRows = snap.numRows();
		this.nColumns = snap.numColumns();
		grid = new int[nRows][nColumns];
		var pixels = snap.pixels();
		for (int y = 0; y < nRows; y++) {
			for (int x = 0; x < nColumns; x++) {
				var col = pixels[y][x];
				Color c = new Color(col.r(), col.g(), col.b());
				grid[y][x] = c.getRGB();
			}
		}
		listeners = new ArrayList<>();
	}

	public void set(final int x, final int y, final Color color) {
		int rgbColor = color.getRGB();
		grid[y][x] = rgbColor;
		for (PixelArtViewModelListener l: listeners) {
			l.notifiedPixelChanged(x, y, rgbColor);
		}
	}
	
	public int get(int x, int y) {
		return grid[y][x];
	}
	
	public int getNumRows() {
		return this.nRows;
	}

	public int getNumColumns() {
		return this.nColumns;
	}

	public void addListener(PixelArtViewModelListener l) {
		listeners.add(l);
	}
}

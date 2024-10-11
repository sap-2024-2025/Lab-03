package sap.pixelart.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Pixel grid implementation
 *
 */
public class PixelGridImpl implements PixelGrid {
	private final int nRows;
	private final int nColumns;
	private final RGBColor[][] grid;
    private final List<PixelGridEventObserver> pixelListeners;
	
	public PixelGridImpl(final int nRows, final int nColumns) {
		this.nRows = nRows;
		this.nColumns = nColumns;
		grid = new RGBColor[nRows][nColumns];
    	for (int y = 0; y < grid.length; y++ ) {
    		for (int x = 0; x < grid[y].length; x++) {
    			grid[y][x] = new RGBColor(255,255,255);
    		}
    	}
		pixelListeners = new ArrayList<>();
	}

	public void clear() {
    	for (int y = 0; y < grid.length; y++ ) {
    		for (int x = 0; x < grid[y].length; x++) {
    			grid[y][x] = new RGBColor(255,255,255);
    		}
    	}
	}
	
	public void set(final int x, final int y, final RGBColor color) {
		grid[y][x] = color;
		pixelListeners.forEach(l -> l.pixelColorChanged(x, y, color));

	}
	
	public RGBColor get(int x, int y) {
		return grid[y][x];
	}
	
	public int getNumRows() {
		return this.nRows;
	}
	

	public int getNumColumns() {
		return this.nColumns;
	}
	
    public void addPixelGridEventListener(PixelGridEventObserver l) { 
    	pixelListeners.add(l); 
    }
    
    public PixelGridSnapshot getSnapshot() {
    	var clone = grid.clone();    	
    	return new PixelGridSnapshot(nRows, nColumns, clone);
    }
	
}

package sap.pixelart.core.domain;

/**
 * 
 * Pixel grid interface
 * 
 */
public interface PixelGrid {

	void clear();
    
	void set(final int x, final int y, final RGBColor color);
	
	RGBColor get(int x, int y);
	
	int getNumRows();

	int getNumColumns();
		
    void addPixelGridEventListener(PixelGridEventObserver l);
    
    PixelGridSnapshot getSnapshot();	
}

package sap.pixelart.core.domain;

/**
 * 
 * Interface for the pixel grid observers
 *  
 */
public interface PixelGridEventObserver {
	void pixelColorChanged(int x, int y, RGBColor color);
}

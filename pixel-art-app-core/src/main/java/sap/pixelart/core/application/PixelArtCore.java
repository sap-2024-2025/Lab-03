package sap.pixelart.core.application;

import java.io.File;
import java.util.List;

import sap.pixelart.core.domain.*;

/**
 * 
 * Application layer interface
 * 
 *
 */
public interface PixelArtCore  {
	/**
	 * Create a new brush
	 * 
	 * @return the brushId
	 */
	String createBrush();
	
	/**
	 * Get the list of current brushes
	 * 
	 * @return a JSON array of brush ids 
	 * 		
	 */
	List<String> getCurrentBrushes();
	
	/**
	 * Get the state of a brush 
	 * 
	 * @param brushId
	 * @return a JSON object storing the current state of a brush
	 * 		   	- fields: "brushId": String, "x": int, "y": int, "color": int
	 * 
	 */
	BrushInfo getBrushInfo(String brushId);
	
	/**
	 * Get the current pixel grid state
	 * 
	 * @retur a JSON object containing current pixel grid state:
	 * 			- fields: "numColums": int, "numRows": int, "pixels": JSONArray (row by row)
	 */
	PixelGridSnapshot getPixelGridState();
	
	/**
	 * Move the specified brush to the specified position
	 * @param brushId
	 * @param y
	 * @param x
	 */
	void moveBrushTo(String brushId, int y, int x);

	/**
	 * Select (color) the pixel where the specified brush is located  
	 * 
	 * @param brushId
	 */
	void selectPixel(String brushId);
	
	/**
	 * 
	 * Change the color of the specified brush
	 * 
	 * @param brushId 
	 * @param color
	 */
	void changeBrushColor(String brushId, RGBColor color);
	
	/**
	 * 
	 * Remove the specified brush
	 * 
	 * @param brushId
	 */
	void destroyBrush(String brushId);
	
	/**
	 * 
	 * Subscribe the pixel grid events
	 * 
	 * @param l
	 */
	void subscribePixelGridEvents(PixelGridEventObserver l);
	
	/**
	 * 
	 * Load a new effect plugin
	 * 
	 * @param effectID effect id
	 * @param effectPluginFile jar file name
	 */
	void loadNewEffect(String effectID, File effectPluginFile);
	
	/**
	 * 
	 * Apply the specified effect
	 * 
	 * @param effectID effect id
	 */
	void applyEffect(String effectID);
	
}

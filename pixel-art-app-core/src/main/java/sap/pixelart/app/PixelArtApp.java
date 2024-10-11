package sap.pixelart.app;

import sap.pixelart.core.application.PixelArtCoreImpl;
import sap.pixelart.core.infrastructure.DashboardControllerAdapter;

public class PixelArtApp {
		
	public static void main(String[] args) {
		var pixelArtAppAPI = new PixelArtCoreImpl();
		pixelArtAppAPI.init();
		var guiAdapter = new DashboardControllerAdapter(pixelArtAppAPI);	    	
		guiAdapter.init();

	}
}

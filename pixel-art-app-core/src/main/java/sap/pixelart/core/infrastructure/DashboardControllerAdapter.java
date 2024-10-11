package sap.pixelart.core.infrastructure;

import java.awt.Color;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import sap.pixelart.core.application.*;
import sap.pixelart.core.domain.PixelGridEventObserver;
import sap.pixelart.core.domain.RGBColor;
import sap.pixelart.dashboard.model.PixelArtViewModel;
import sap.pixelart.dashboard.view.DashboardEventListener;
import sap.pixelart.dashboard.view.DashboardView;

/**
 * 
 * Controller functioning as Adapter for the GUI Dashboard 
 *
 */
public class DashboardControllerAdapter implements PixelGridEventObserver, DashboardEventListener {
    static Logger logger = Logger.getLogger("[PixelArtGUIAdapter]");	
	// private int port;
    
	private PixelArtViewModel pixelLocalViewModel;
	private DashboardView view;
	private String brushId;
	private PixelArtCore pixelArtAPI;
	
	public DashboardControllerAdapter(PixelArtCore pixelArtAPI) {	
		this.pixelArtAPI = pixelArtAPI;
	}
		
	public void init() {
		brushId = pixelArtAPI.createBrush();
		logger.log(Level.INFO, "Brush created: " + brushId);

		var grid = pixelArtAPI.getPixelGridState();	
		pixelLocalViewModel = new PixelArtViewModel(grid);

		pixelArtAPI.subscribePixelGridEvents(this);
		logger.log(Level.INFO, "PixelGrid subscribed. ");	
						
		view = new DashboardView(pixelLocalViewModel, 800, 800);
		view.setController(this);
		pixelLocalViewModel.addListener(view);
				
		view.display();
		
	}

	
	/* events notified by the GUI */

	public void selectedCell(int x, int y) {
		pixelArtAPI.moveBrushTo(brushId, y, x);
		pixelArtAPI.selectPixel(brushId);
	}

	public void colorChanged(Color color) {		
		pixelArtAPI.changeBrushColor(brushId, new RGBColor(color.getRed(), color.getGreen(), color.getBlue()));
	}

	/* events notified by the core */
	
	public void pixelColorChanged(int x, int y, RGBColor color) {
		logger.log(Level.INFO, "New event from service " + y + " " + x + " color: " + color);
		Color c = new Color(color.r(), color.g(), color.b());
		pixelLocalViewModel.set(x, y, c);
	}

	@Override
	public void newEffectToAddSelected(File file) {
		try {
			var effectID = file.getName().replaceFirst(".jar", "");
			pixelArtAPI.loadNewEffect(effectID, file);
			view.addNewEffect(file.getName().replaceFirst(".jar", ""));
		} catch (Exception ex) {}
	}

	@Override
	public void notifyApplyEffect(String name) {
		pixelArtAPI.applyEffect(name);
	}

}

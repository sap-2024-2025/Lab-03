package sap.pixelart.core.application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sap.pixelart.core.domain.Brush;
import sap.pixelart.core.domain.BrushInfo;
import sap.pixelart.core.domain.BrushManager;
import sap.pixelart.core.domain.PixelGrid;
import sap.pixelart.core.domain.PixelGridEventObserver;
import sap.pixelart.core.domain.PixelGridImpl;
import sap.pixelart.core.domain.PixelGridSnapshot;
import sap.pixelart.core.domain.RGBColor;

/**
 *
 * 
 * Application layer implementation
 * 
 * 
 */
public class PixelArtCoreImpl implements PixelArtCore {

	private BrushManager brushManager;
	private PixelGrid grid;
	private int brushCounter;
	
	private HashMap<String, EffectPlugin> pluginRegistry;
	
	public void init() {
		brushCounter = 0;
		brushManager = new BrushManager();
		grid = new PixelGridImpl(40,40);
		pluginRegistry = new HashMap<>();
		/*
        try {
        	this.registerNewEffectPlugin("test-effect", new File("plugins/TestEffect.jar"));
        	System.out.println("Plugin loaded.");
        } catch (Exception ex) {
        	ex.printStackTrace();
        }*/

	}

	@Override
	public String createBrush() {
		brushCounter++;
		String brushId ="brush-" + brushCounter; 
		brushManager.addBrush(brushId, new Brush(0,0,new RGBColor(0,0,0)));
		return brushId;
	}

	@Override
	public List<String> getCurrentBrushes() {
		var c = brushManager.getBrushesId();
		ArrayList<String> list = new ArrayList<>();
		for (String s: c) {
			list.add(s);
		}
		return list;
	}
	
	@Override
	public void moveBrushTo(String brushId, int y, int x) {
		Brush b = brushManager.getBrush(brushId);
		b.updatePosition(x, y);		
	}

	@Override
	public void selectPixel(String brushId) {
		Brush b = brushManager.getBrush(brushId);
		grid.set(b.getX(), b.getY(), b.getColor());
	}

	@Override
	public void changeBrushColor(String brushId, RGBColor color) {
		Brush b = brushManager.getBrush(brushId);
		b.setColor(color);
	}

	@Override
	public void destroyBrush(String brushId) {
		brushManager.removeBrush(brushId);
	}

	@Override
	public BrushInfo getBrushInfo(String brushId) {
		Brush b = brushManager.getBrush(brushId);
		return new BrushInfo(brushId, b.getX(), b.getY(), b.getColor());
	}

	@Override
	public PixelGridSnapshot getPixelGridState() {
		return grid.getSnapshot();
	}

	@Override
	public void subscribePixelGridEvents(PixelGridEventObserver l) {
		grid.addPixelGridEventListener(l);
	}

	/* plugin management and use */
	
	@Override
	public void loadNewEffect(String effectID, File effectPluginFile) {
		try {
			registerNewEffectPlugin(effectID, effectPluginFile);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void registerNewEffectPlugin(String pluginID, File libFile) throws Exception  {     
        var loader = new PluginClassLoader(libFile.getAbsolutePath());
        String className = "sap.pixelart.plugin.effects." + libFile.getName().replaceFirst(".jar","");
        Class<?> pluginClass = loader.loadClass(className);        
        EffectPlugin effectPlugin = (EffectPlugin) pluginClass.getDeclaredConstructor(null).newInstance(null);
		pluginRegistry.put(pluginID, effectPlugin);		
		log("Added plugin-in " + pluginID);
	}
	
	@Override
	public void applyEffect(String effectID) {
		EffectPlugin effectPlugin = pluginRegistry.get(effectID);
		effectPlugin.applyEffect(grid);
	}

	
	private void log(String msg) {
		System.out.println("[PixelArtApplication] " + msg);
	}
}


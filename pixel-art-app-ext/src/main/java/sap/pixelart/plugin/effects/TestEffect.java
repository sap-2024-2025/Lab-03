package sap.pixelart.plugin.effects;

import sap.pixelart.core.application.EffectPlugin;
import sap.pixelart.core.domain.PixelGrid;
import sap.pixelart.core.domain.RGBColor;

public class TestEffect implements EffectPlugin {

	@Override
	public void applyEffect(PixelGrid grid) {
		for (int y = 0; y < grid.getNumRows(); y++) {
			for (int x = 0; x < grid.getNumColumns(); x++) {
				var c = grid.get(x, y);
				var c1 = new RGBColor(
						(int)(((double)c.r())/1.25), 
						(int)(((double)c.g())/1.25), 
						(int)(((double)c.b())/1.25));
				grid.set(x, y, c1);
			}
			
		}
		log("applied");
	}
	
	private void log(String msg) {
		System.out.println("[TestEffectPlugin] " + msg);
	}

	
	
}

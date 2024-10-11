package sap.pixelart.dashboard.view;

import java.awt.Color;
import java.io.File;

public interface DashboardEventListener {

	void selectedCell(int x, int y);

    void colorChanged(Color color);
	
    void newEffectToAddSelected(File file);
    
    void notifyApplyEffect(String name);
    
}

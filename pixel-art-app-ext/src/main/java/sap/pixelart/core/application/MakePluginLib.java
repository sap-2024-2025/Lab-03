package sap.pixelart.core.application;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class MakePluginLib {

	public static void main(String[] args) throws Exception {
		var jarFileName = "TestEffect.jar";
		var dir = "sap";
		var p = Runtime.getRuntime().exec (new String[]{"jar", "cvf", jarFileName, dir}, new String[]{}, new File("target/classes"));		
		p.waitFor(10, TimeUnit.SECONDS);
		var dest = new File("../pixel-art-app-core/plugins/"+jarFileName);
		dest.delete();
		new File("target/classes/"+jarFileName).renameTo(dest);
		System.out.println("done.");
	}	

}

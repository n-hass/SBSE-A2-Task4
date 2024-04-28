package uoa.partII;

import java.util.ArrayList;
import java.io.Serializable;

public class ColourMapping implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> guiComponents;
	private ArrayList<ArrayList<Integer>> RGB;
	
	public ColourMapping() {
		guiComponents = new ArrayList<>();
		RGB = new ArrayList<>();
	}
	
	public ColourMapping(ArrayList<String> guiComponents, ArrayList<ArrayList<Integer>> RGB) {
		this.guiComponents = guiComponents;
		this.RGB = RGB;
	}

	public ArrayList<String> getGuiComponents() {
		return guiComponents;
	}

	public ArrayList<ArrayList<Integer>> getRGB() {
		return RGB;
	}
}

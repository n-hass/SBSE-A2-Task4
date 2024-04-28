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


	// mainFrameColor,9,91,135
	// jButton1,128,121,176
	// jButton2,76,241,3
	// jButton3,37,139,32
	// jButton4,96,91,165
	// jButton5,177,67,157
	// jButton6,138,175,84
	// jButton7,148,103,12
	// jButton8,121,48,107
	// jButton9,131,240,153
	// jButton10,81,55,60
	// jButton11,88,112,123
	// jButton12,5,235,141
	// jButton13,198,180,144
	// jButton14,33,54,96
	// jButton15,147,37,243
	// jButton16,95,66,252
	// jButton17,49,61,109
	// jButton18,158,178,112
	// jTextField1,21,193,216
	// jTextField1TextColor,59,101,162
	// jLabel1,55,232,196
	// jPanel1,218,128,47
	// jPanel2,79,114,176
	// jPanel3,152,104,97
	// jPanel4,183,115,42
	// jPanel5,24,225,89
	public static ArrayList<ArrayList<Integer>> OriginalImage = new ArrayList<ArrayList<Integer>>() {
		{
			add(new ArrayList<Integer>() {
				{
					add(9);
					add(91);
					add(135);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(128);
					add(121);
					add(176);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(76);
					add(241);
					add(3);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(37);
					add(139);
					add(32);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(96);
					add(91);
					add(165);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(177);
					add(67);
					add(157);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(138);
					add(175);
					add(84);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(148);
					add(103);
					add(12);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(121);
					add(48);
					add(107);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(131);
					add(240);
					add(153);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(81);
					add(55);
					add(60);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(88);
					add(112);
					add(123);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(5);
					add(235);
					add(141);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(198);
					add(180);
					add(144);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(33);
					add(54);
					add(96);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(147);
					add(37);
					add(243);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(95);
					add(66);
					add(252);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(49);
					add(61);
					add(109);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(158);
					add(178);
					add(112);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(21);
					add(193);
					add(216);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(59);
					add(101);
					add(162);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(55);
					add(232);
					add(196);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(218);
					add(128);
					add(47);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(79);
					add(114);
					add(176);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(152);
					add(104);
					add(97);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(183);
					add(115);
					add(42);
				}
			});
			add(new ArrayList<Integer>() {
				{
					add(24);
					add(225);
					add(89);
				}
			});
		}
	};

	public static double componentPixelCount(int componentIndex) {
		if (componentIndex == 0) return 2930024;
		
		if (componentIndex >= 1 && componentIndex <= 13) {
			return 4320;
		}
		if (componentIndex >= 14 && componentIndex <= 17) {
			return 1739;
		}
		if (componentIndex == 18) {
			return 2646;
		}
		if (componentIndex == 19) {
			return 1038;
		}
		if (componentIndex == 20) {
			return 194;
		}
		if (componentIndex >= 21) {
			return 388;
		}

		return 0;
	}
}

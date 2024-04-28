package uoa.partII;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import org.checkerframework.checker.units.qual.s;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;

public class ChargeProblem extends AbstractProblem {
	private static int TargetAppRunningtime = 1300;
	private static String ImageDir = "images";
	private static String ImageFormat = "png";
	private static int NumColourValues = 81; // 27 GUI components * 3 RGB values
	private static double Resolution = 2560 * 1440;
	
	private BufferedImage OriginalImage;

	public ChargeProblem() {
        super(NumColourValues, 2);

		try {
			Files.createDirectories(Paths.get("images"));
		} catch (IOException e) {
			System.err.println("Failed to create directory: images");
			e.printStackTrace();
		}

		// consider the original image is at images/original.png
		File originalFile = new File("original.png");
		try {
			this.OriginalImage = ImageIO.read(originalFile);
		} catch (IOException e) {
			System.err.println("Failed to read image: images/original.png");
			e.printStackTrace();
		}
    }

    @Override
    public void evaluate(Solution solution) {
        ColourMapping colourValues = (ColourMapping) solution.getAttribute("colourValues");
		ColourMapping tmpValues = colourValues;
		double f;
		double g;

		try {
			saveToCSV("color.csv", colourValues.getGuiComponents(), colourValues.getRGB());
		} catch (NullPointerException e) {
			tmpValues = generateColourValues();
			int x = 0;
			for (int i = 0; i < tmpValues.getRGB().size(); i++) {
				for (int j = 0; j < 3; j++) {
					var pixelVal = tmpValues.getRGB().get(i).get(j);
					solution.setVariable(x++, new RealVariable(pixelVal, 0, 255));
				}
			}

			saveToCSV("color.csv", tmpValues.getGuiComponents(), tmpValues.getRGB());
			solution.setAttribute("colourValues", tmpValues);
		}

		String imageFilename = "";
		try {
			imageFilename = doSampleRun();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}

		// read the image file
		File file = new File(ImageDir+"/"+imageFilename+"."+ImageFormat);
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.err.println("Failed to read image: "+ImageDir+"/"+imageFilename+"."+ImageFormat);
			e.printStackTrace();
			return;
		}

		// f: minimise charge consumption
		f = calculateChargeConsumption(image);
		// f = calculateChargeConsumptionFromComputed(tmpValues);

		// g: minimize total change from the original
		g = calculateTotalChange(image);
		// g = calculateTotalChangeFromComputed(tmpValues);

		solution.setAttribute("imageFileName", imageFilename);
		solution.setObjective(0, f);
		solution.setObjective(1, g);
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(NumColourValues, 2);

		ColourMapping newValues = generateColourValues();
		while (newValues == null) {
			System.out.println("Error: newValues is null. Generating new colour values.");
			newValues = generateColourValues();
		}
		
		int x = 0;
		for (int i = 0; i < newValues.getRGB().size(); i++) {
			for (int j = 0; j < 3; j++) {
				var pixelVal = newValues.getRGB().get(i).get(j);
				solution.setVariable(x++, new RealVariable(pixelVal, 0, 255));
			}
		}
		
		solution.setAttribute("colourValues", newValues);
        return solution;
    }

	private ColourMapping generateColourValues() {
    
            // guiComponents contains GUI components' name.
            ArrayList<String> guiComponents = new ArrayList<>();
            guiComponents.add("mainFrameColor"); // both apps
            guiComponents.add("jButton1");// both apps
            guiComponents.add("jButton2");
            guiComponents.add("jButton3");
            guiComponents.add("jButton4");
            guiComponents.add("jButton5");
            guiComponents.add("jButton6");
            guiComponents.add("jButton7");
            guiComponents.add("jButton8");
            guiComponents.add("jButton9");
            guiComponents.add("jButton10");
            guiComponents.add("jButton11");
            guiComponents.add("jButton12");
            guiComponents.add("jButton13");
            guiComponents.add("jButton14");
            guiComponents.add("jButton15");
            guiComponents.add("jButton16");
            guiComponents.add("jButton17");
            guiComponents.add("jButton18");
            
            guiComponents.add("jTextField1");// both apps
            guiComponents.add("jTextField1TextColor");// both apps

            guiComponents.add("jLabel1");// both apps

            guiComponents.add("jPanel1");// both apps
            guiComponents.add("jPanel2");
            guiComponents.add("jPanel3");
            guiComponents.add("jPanel4");
            guiComponents.add("jPanel5");

            ArrayList<ArrayList<Integer>> RGB = new ArrayList<>();
            Random randomInt = new Random();

            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));

            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
            RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));

            return new ColourMapping(guiComponents, RGB);
        
    }

	private void saveToCSV(String filePath, ArrayList<String> guiComponents, ArrayList<ArrayList<Integer>> RGB) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(new File(filePath)));
            String line = "";
            for (int i = 0; i < guiComponents.size(); i++) {
                line += guiComponents.get(i).concat(",").concat(RGB.get(i).toString().replace("[", "").replace("]", "").replaceAll("\\s", "")) + "\n";
                //System.out.println(line);
            }
            br.write(line);
            br.flush();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private String doSampleRun() throws InterruptedException, IOException {
		String filename = generateRandomString(12);

		Runtime runTime = Runtime.getRuntime();
		Process process = runTime.exec("java -jar calculator.jar");
		Thread.sleep(TargetAppRunningtime);
		Capture capture = new Capture();
		capture.takeScreenShot(ImageDir+"/"+filename);

		process.destroy();
		return filename;
	}

	private static String generateRandomString(int length) {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumericString.length());
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }


	private double calculateChargeConsumption(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		double totalCost = 0;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = image.getRGB(i, j);

				double red = Double.valueOf((pixel >> 16) & 0xff);
				double green = Double.valueOf((pixel >> 8) & 0xff);
				double blue = Double.valueOf(pixel & 0xff);

				totalCost += calculateCostOfPixel(red, green, blue);
			}
		}

		return totalCost;
	}

	private double calculateChargeConsumptionFromComputed(ColourMapping image) {
		var components = image.getRGB();

		double totalCost = 0;

		for (int i = 0; i < components.size(); i++) {
			double red = components.get(i).get(0);
			double green = components.get(i).get(1);
			double blue = components.get(i).get(2);

			totalCost += calculateCostOfPixel(red, green, blue) * (ColourMapping.componentPixelCount(i) / Resolution);
		}

		return totalCost;
	}

	private double calculateCostOfPixel(double r, double g, double b) {
		double RED = 131;
		double GREEN = 142;
		double BLUE = 241;

		return ((r*RED) + (g*GREEN) + (b*BLUE)) / Resolution;
	}

	private double calculateTotalChange(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		double totalChange = 0;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = image.getRGB(i, j);
				int originalPixel = OriginalImage.getRGB(i, j);

				double red = Double.valueOf((pixel >> 16) & 0xff);
				double green = Double.valueOf((pixel >> 8) & 0xff);
				double blue = Double.valueOf(pixel & 0xff);

				double originalRed = Double.valueOf((originalPixel >> 16) & 0xff);
				double originalGreen = Double.valueOf((originalPixel >> 8) & 0xff);
				double originalBlue = Double.valueOf(originalPixel & 0xff);

				totalChange += Math.abs(red - originalRed) + Math.abs(green - originalGreen) + Math.abs(blue - originalBlue) / Resolution;
			}
		}

		return totalChange;
	}

	private double calculateTotalChangeFromComputed(ColourMapping image) {
		var components = image.getRGB();

		var original = ColourMapping.OriginalImage;

		double totalChange = 0;

		for (int i = 0; i < components.size(); i++) {
			
			double red = components.get(i).get(0);
			double green = components.get(i).get(1);
			double blue = components.get(i).get(2);

			double originalRed = original.get(i).get(0);
			double originalGreen = original.get(i).get(1);
			double originalBlue = original.get(i).get(2);

			totalChange += Math.abs(red - originalRed) + Math.abs(green - originalGreen) + Math.abs(blue - originalBlue) / Resolution;
		}

		return totalChange;
	}
}

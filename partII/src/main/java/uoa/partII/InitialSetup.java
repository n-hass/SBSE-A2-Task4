package uoa.partII;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class InitialSetup {
	public static void setupFirstImage() {
		// copy the file base_color1.csv to color.csv
		String sourcePath = "base_color1.csv";
        String destinationPath = "color.csv";

        try {
            // Copy file with replacement of existing file at the destination
            Files.copy(Paths.get(sourcePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error occurred during file copying.");
            e.printStackTrace();
        }

		Runtime runTime = Runtime.getRuntime();

		try {
			Process process = runTime.exec("java -jar calculator.jar");
			Thread.sleep(1200);
			Capture capture = new Capture();
			capture.takeScreenShot("original");
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Setup complete");
		runTime.exit(0);
	}
}

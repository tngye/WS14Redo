package sg.edu.nus.iss.WS14Redo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ws14RedoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Ws14RedoApplication.class);
		String dataDir = null;

		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);

		if (cliOpts.containsOption("dataDir")){
			dataDir = cliOpts.getOptionValues("dataDir").get(0);
			System.out.println(">>>dataDir: " +dataDir);
			Path path = Paths.get(dataDir);
			System.out.println(">>>path: " +path);
			if(!Files.exists(path)){
				File file = new File(path.toString());
				file.mkdir();
				
				   System.out.println("Directory created successfully");
		
			}
		}
		app.run(args);	
	}

}

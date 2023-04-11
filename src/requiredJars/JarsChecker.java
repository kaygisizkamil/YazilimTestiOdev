package requiredJars;
import java.io.File;
import java.util.ArrayList;

public class JarsChecker {//missed user libraries you can check if you encounter any problem
/*
    private static final String[] REQUIRED_JARS = {"commons-lang3-3.12.0", "javafaker-1.0.2", "snakeyaml-1.29"
    		,"byte-buddy-1.12.7","byte-buddy-agent-1.12.7","mockito-core-4.3.1","mockito-junit-jupiter-4.3.1"
    		,"objenesis-3.2"
    		};

    public static void checkJars() {
    	
        ArrayList<String> missingJars = new ArrayList<>();
        for (String jarName : REQUIRED_JARS) {
            if (!new File(jarName).exists()) {
                missingJars.add(jarName);
            }
        }
        if (missingJars.isEmpty()) {
            System.out.println("All required jars are present.");
        } else {
            System.out.println("The following jars are missing:");
            for (String jarName : missingJars) {
                System.out.println(jarName);
            }
        }
    }*/
}
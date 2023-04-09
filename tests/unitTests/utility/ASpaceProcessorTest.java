package unitTests.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import utility.ASpaceProcessor;

class ASpaceProcessorTest {
    
    @Test
    public void tesDeletingLinetWithFaker() {
    	ASpaceProcessor aSpaceProcessor=new TrivialASpaceProcessorImpl();
    	Faker faker=new Faker();
        String input = faker.lorem().sentence();
        String expectedOutput = input.replaceAll("\\s+", "");
        String actualOutput = aSpaceProcessor.deleteSpaces(input);
        assertEquals(expectedOutput, actualOutput);
    }
}
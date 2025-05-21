package com.PriceComparatorBackend.PriceComparatorBackend;
import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.PriceComparatorBackend.PriceComparatorBackend.Utils.Utils;

@SpringBootApplication
public class PriceComparatorBackendApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PriceComparatorBackendApplication.class, args);
        Utils.createDataset();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/dataset.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch(Exception e)
        {   
            System.out.println(e.getMessage());
        }
    }
}

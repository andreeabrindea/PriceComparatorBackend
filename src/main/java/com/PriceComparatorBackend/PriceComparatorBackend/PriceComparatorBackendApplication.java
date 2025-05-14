package com.PriceComparatorBackend.PriceComparatorBackend;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.Reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.opencsv.CSVReader;


@SpringBootApplication
public class PriceComparatorBackendApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PriceComparatorBackendApplication.class, args);
		Path path = Paths.get("src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/input.csv");
        List<String[]> lines = readAllLines(path);
        for(String[] line : lines)
        {
            for(String ceva : line)
            {
                System.out.print( ceva );
            }

            System.out.println();
        }
    }

    public static List<String[]> readAllLines(Path filePath) throws Exception {
    try (Reader reader = Files.newBufferedReader(filePath)) {
        try (CSVReader csvReader = new CSVReader(reader)) {
            return csvReader.readAll();
        }
    }
}
}

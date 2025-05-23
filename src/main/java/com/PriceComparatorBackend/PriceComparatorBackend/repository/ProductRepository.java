package com.PriceComparatorBackend.PriceComparatorBackend.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.Utils.Utils;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    public List<Product> getAllProducts(){
        try
        {
            Utils.createDataset();
        }
        catch (Exception exception)
        {
            return null;
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/dataset.csv"))) {
            String line;
            int lineCount = 0;
            List<Product> products = new ArrayList<Product>();
            while ((line = br.readLine()) != null) {
                if (lineCount > 0)
                {
                    String[] entries = line.split(";");
                    products.add(new Product(entries[0], entries[1], entries[2], entries[3], Double.parseDouble(entries[4]), entries[5], Double.parseDouble(entries[6]), entries[7], entries[8], entries[9]));
                }
                lineCount++;
            }
            
            return products;
        }
        catch(Exception e)
        {   
            System.out.println(e.getMessage());
        }
        return null;
    }
}

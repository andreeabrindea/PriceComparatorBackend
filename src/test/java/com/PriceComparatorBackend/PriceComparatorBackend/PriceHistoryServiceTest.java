package com.PriceComparatorBackend.PriceComparatorBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.PriceComparatorBackend.PriceComparatorBackend.repository.DiscountRepository;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.ProductRepository;
import com.PriceComparatorBackend.PriceComparatorBackend.service.PriceHistoryService;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.model.ProductFilter;

public class PriceHistoryServiceTest {
    private final PriceHistoryService priceHistoryService = new PriceHistoryService(new ProductRepository(), new DiscountRepository());

    @Test
    public void test_filterProducts_byStore_shouldReturnExpected() {
        Product produsDinLidl = new Product("P001", "lapte", "lactate", "Zuzu", 1.0, "l", 10.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        Product produsDinProfi = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        List<Product> products = new ArrayList<>();
        products.add(produsDinLidl);
        products.add(produsDinProfi);

        ProductFilter filter = new ProductFilter();
        filter.setStoreName("profi");

        List<Product> actual = priceHistoryService.filterProducts(products, filter);
        List<Product> expected = new ArrayList<>();
        expected.add(produsDinProfi);
        assertEquals(expected, actual);
    }

    @Test
    public void test_filterProducts_byCategory_shouldReturnExpected() {
        Product lapte = new Product("P001", "lapte", "lactate", "Zuzu", 1.0, "l", 10.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        Product apa = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        Product iaurt = new Product("P005", "iaurt", "lactate", "Danone", 200.0, "g", 6.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        List<Product> products = new ArrayList<>();
        products.add(lapte);
        products.add(apa);
        products.add(iaurt);

        ProductFilter filter = new ProductFilter();
        filter.setProductCategory("lactate");

        List<Product> actual = priceHistoryService.filterProducts(products, filter);
        List<Product> expected = new ArrayList<>();
        expected.add(lapte);
        expected.add(iaurt);
        assertEquals(expected, actual);
    }

        @Test
    public void test_filterProducts_byBrand_shouldReturnExpected() {
        Product apaMicelaraKauflandClassic = new Product("P001", "apa micelara", "cosmetice", "kaufland classic", 500.0, "ml", 10.0, "RON", "kaufland",
                LocalDate.of(2025, 05, 20));
        Product solutiePodeleKauflandClassic = new Product("P003", "solutie podele", "curatenie", "kaufland classic", 1.0, "l", 13.0, "RON", "kaufland",
                LocalDate.of(2025, 05, 20));
        Product suncaSisi = new Product("P005", "sunca sisi", "mezeluri", "sisi", 200.0, "g", 6.0, "RON", "kaufland",
                LocalDate.of(2025, 05, 20));
        List<Product> products = new ArrayList<>();
        products.add(apaMicelaraKauflandClassic);
        products.add(solutiePodeleKauflandClassic);
        products.add(suncaSisi);

        ProductFilter filter = new ProductFilter();
        filter.setBrand("Kaufland Classic");

        List<Product> actual = priceHistoryService.filterProducts(products, filter);
        List<Product> expected = new ArrayList<>();
        expected.add(apaMicelaraKauflandClassic);
        expected.add(solutiePodeleKauflandClassic);
        assertEquals(expected, actual);
    }

    @Test
    public void test_filterProducts_byCategoryAndStore_shouldReturnExpected() {
        Product lapteDinLidl = new Product("P001", "lapte", "lactate", "Zuzu", 1.0, "l", 10.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        Product apa = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        Product iaurtDinProfi = new Product("P005", "iaurt", "lactate", "Danone", 200.0, "g", 6.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        List<Product> products = new ArrayList<>();
        products.add(lapteDinLidl);
        products.add(apa);
        products.add(iaurtDinProfi);

        ProductFilter filter = new ProductFilter();
        filter.setProductCategory("lactate");
        filter.setStoreName("lidl");
        List<Product> actual = priceHistoryService.filterProducts(products, filter);
        List<Product> expected = new ArrayList<>();
        expected.add(lapteDinLidl);
        assertEquals(expected, actual);
    }

    @Test
    public void test_filterProducts_byStoreAndBrand_shouldReturnExpected() {
        Product lapteZuzuDinLidl = new Product("P001", "lapte", "lactate", "Zuzu", 1.0, "l", 10.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        Product apaDinProfi = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        Product apaDinLidl = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        Product iaurtZuzuDinLidl = new Product("P005", "iaurt", "lactate", "Zuzu", 200.0, "g", 6.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        List<Product> products = new ArrayList<>();
        products.add(lapteZuzuDinLidl);
        products.add(apaDinLidl);
        products.add(apaDinProfi);
        products.add(iaurtZuzuDinLidl);

        ProductFilter filter = new ProductFilter();
        filter.setStoreName("lidl");
        filter.setBrand("Zuzu");
        List<Product> actual = priceHistoryService.filterProducts(products, filter);
        List<Product> expected = new ArrayList<>();
        expected.add(lapteZuzuDinLidl);
        expected.add(iaurtZuzuDinLidl);
        assertEquals(expected, actual);
    }

    @Test
    public void test_filterProducts_byCategoryAndBrand_shouldReturnExpected() {
        Product lapteZuzuDinKaufland = new Product("P001", "lapte", "lactate", "Zuzu", 1.0, "l", 10.0, "RON",
                "kaufland",
                LocalDate.of(2025, 05, 20));
        Product apaDinProfi = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        Product apaDinLidl = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        Product iaurtZuzuDinLidl = new Product("P005", "iaurt", "lactate", "Zuzu", 200.0, "g", 6.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        List<Product> products = new ArrayList<>();
        products.add(lapteZuzuDinKaufland);
        products.add(apaDinLidl);
        products.add(apaDinProfi);
        products.add(iaurtZuzuDinLidl);

        ProductFilter filter = new ProductFilter();
        filter.setProductCategory("lactate");
        filter.setBrand("Zuzu");
        List<Product> actual = priceHistoryService.filterProducts(products, filter);
        List<Product> expected = new ArrayList<>();
        expected.add(lapteZuzuDinKaufland);
        expected.add(iaurtZuzuDinLidl);
        assertEquals(expected, actual);
    }

    @Test
    public void test_filterProducts_byCategoryAndStoreAndBrand_shouldReturnExpected() {
        Product lapteDinLidlZuzu = new Product("P001", "lapte", "lactate", "Zuzu", 1.0, "l", 10.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        Product apa = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        Product iaurtDinProfi = new Product("P005", "iaurt", "lactate", "Danone", 200.0, "g", 6.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        Product iaurtDinLidlZuzu = new Product("P005", "iaurt", "lactate", "Zuzu", 200.0, "g", 6.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        List<Product> products = new ArrayList<>();
        products.add(lapteDinLidlZuzu);
        products.add(apa);
        products.add(iaurtDinLidlZuzu);
        products.add(iaurtDinProfi);

        ProductFilter filter = new ProductFilter();
        filter.setProductCategory("lactate");
        filter.setStoreName("lidl");
        filter.setBrand("Zuzu");
        List<Product> actual = priceHistoryService.filterProducts(products, filter);
        List<Product> expected = new ArrayList<>();
        expected.add(lapteDinLidlZuzu);
        expected.add(iaurtDinLidlZuzu);
        assertEquals(expected, actual);
    }

    @Test
    public void test_filterProducts_byNothing_shouldReturnAllProducts() {
        Product lapteDinLidlZuzu = new Product("P001", "lapte", "lactate", "Zuzu", 1.0, "l", 10.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        Product apa = new Product("P003", "apa", "bauturi", "Aqua Carpatica", 1.0, "l", 3.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        Product iaurtDinProfi = new Product("P005", "iaurt", "lactate", "Danone", 200.0, "g", 6.0, "RON", "profi",
                LocalDate.of(2025, 05, 20));
        Product iaurtDinLidlZuzu = new Product("P005", "iaurt", "lactate", "Zuzu", 200.0, "g", 6.0, "RON", "lidl",
                LocalDate.of(2025, 05, 20));
        List<Product> expected = new ArrayList<>();
        expected.add(lapteDinLidlZuzu);
        expected.add(apa);
        expected.add(iaurtDinLidlZuzu);
        expected.add(iaurtDinProfi);

        ProductFilter filter = new ProductFilter();
        List<Product> actual = priceHistoryService.filterProducts(expected, filter);
        assertEquals(expected, actual);
    }
}

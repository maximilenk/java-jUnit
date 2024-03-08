package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.ArrayList;
import java.util.List;

public class ProductsRepositoryImplTest {
    private ProductsRepository pr;
    private EmbeddedDatabase db;
    private final List<Product> EXPECTED_FIND_ALL = new ArrayList<>();
    private final Product EXPECTED_FIND_BY_ID = new Product(4L, "berry", 244);
    private final List<Product> EXPECTED_REMOVE_BY_ID = new ArrayList<>();
    private final Product EXPECTED_UPDATE = new Product(3L, "Monitor", 800);
    private final Product EXPECTED_SAVE = new Product(6L, "Table", 200);

    @BeforeEach
    public void init() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScripts("schema.sql", "data.sql")
                .build();
        pr = new ProductsRepositoryImpl(db);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertTrue(pr.findById(4L).isPresent());
        Assertions.assertEquals(pr.findById(4L).get(), EXPECTED_FIND_BY_ID);
    }

    @Test
    public void updateTest() {
        Product p = new Product(3L, "Monitor", 800);
        pr.update(p);
        Assertions.assertTrue(pr.findById(3L).isPresent());
        Assertions.assertEquals(pr.findById(3L).get(), EXPECTED_UPDATE);
    }

    @Test
    public void saveTest() {
        Product p = new Product(6L, "Table", 200);
        pr.save(p);
        Assertions.assertTrue(pr.findById(6L).isPresent());
        Assertions.assertEquals(pr.findById(6L).get(), EXPECTED_SAVE);
    }

    @Test
    public void deleteTest() {
        EXPECTED_REMOVE_BY_ID.clear();
        EXPECTED_REMOVE_BY_ID.add(new Product(1L, "banana", 100));
        EXPECTED_REMOVE_BY_ID.add(new Product(3L, "melon", 136));
        EXPECTED_REMOVE_BY_ID.add(new Product(5L, "peach", 312));
        pr.delete(2L);
        pr.delete(4L);
        Assertions.assertEquals(EXPECTED_REMOVE_BY_ID, pr.findAll());
    }

    @Test
    public void findAllTest() {
        EXPECTED_FIND_ALL.clear();
        EXPECTED_FIND_ALL.add(new Product(1L, "banana", 100));
        EXPECTED_FIND_ALL.add(new Product(2L, "apple", 55));
        EXPECTED_FIND_ALL.add(new Product(3L, "melon", 136));
        EXPECTED_FIND_ALL.add(new Product(4L, "berry", 244));
        EXPECTED_FIND_ALL.add(new Product(5L, "peach", 312));
        Assertions.assertEquals(EXPECTED_FIND_ALL, pr.findAll());
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }
}

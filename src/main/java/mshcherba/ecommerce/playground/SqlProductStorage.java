package mshcherba.ecommerce.playground;

import mshcherba.ecommerce.catalog.Product;
import mshcherba.ecommerce.catalog.ProductStorage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.UUID;

public class SqlProductStorage implements ProductStorage {

    public void setUpDatabase(){
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` IF EXISTS;");

        var createTableSql = """
            CREATE TABLE `product_catalog__products` (
                `id` VARCHAR(255) NOT NULL,
                `name` VARCHAR(100) NOT NULL,
                `description` VARCHAR(512) NOT NULL,
                `price` DECIMAL(12,2),
                PRIMARY KEY(id)
            );
            
        """;
        jdbcTemplate.execute(createTableSql);
    }

    private JdbcTemplate jdbcTemplate;
    public SqlProductStorage() {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> allProducts() {
        String sql = "SELECT * FROM `product_catalog__products`";
        return jdbcTemplate.query(sql,productRowMapper());
    }

    @Override
    public void add(Product newProduct) {
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    (?, ?, ?, ?);
                """;
        jdbcTemplate.update(sql, newProduct.getId(), newProduct.getName(), newProduct.getDescription(), newProduct.getPrice());
    }


    @Override
    public Product getProductBy(String id) {
        var sql = """
            SELECT * from `product_catalog__products` where id = ? and 1 = ?
        """;
        return jdbcTemplate.queryForObject(sql, new Object[]{id, 1}, productRowMapper());
    }

    private RowMapper<Product> productRowMapper(){
        return (rs, i) -> new Product(
                UUID.randomUUID(),
                rs.getString("NAME"),
                rs.getString("DESCRIPTION"),
                rs.getBigDecimal("PRICE")
        );
    }

}
package com.nami.dao;

import com.nami.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class ProductDataAccessService implements ProductDao{

    private final JdbcTemplate jdbcTemplate;

    public ProductDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertProduct(UUID id, Product product) {
        String sql = "INSERT INTO product(id, name) VALUES(?,?)";
        return jdbcTemplate.update(sql, id, product.getName());
    }

    @Override
    public List<Product> selectAllProducts() {
        String sql = "Select id, name FROM product";
        return jdbcTemplate.query(sql, (resultSet, i) ->{
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Product(id, name);
        });
    }

    @Override
    public Optional<Product> selectProductById(UUID id) {
        String sql = "Select id, name FROM product WHERE id = ?";
        Product product =  jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) ->{
            UUID productId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Product(productId, name);
        });
        return Optional.ofNullable(product);
    }

    @Override
    public int deleteProductById(UUID id) {
        String sql = "DELETE FROM product WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updateProductById(UUID id, Product product) {
        String sql = "UPDATE product set name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, product.getName(), id);
    }
}

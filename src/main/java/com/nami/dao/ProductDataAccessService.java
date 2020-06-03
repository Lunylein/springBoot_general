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
        return 0;
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
        return Optional.empty();
    }

    @Override
    public int deleteProductById(UUID id) {
        return 0;
    }

    @Override
    public int updateProductById(UUID id, Product product) {
        return 0;
    }
}

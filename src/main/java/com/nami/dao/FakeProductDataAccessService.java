package com.nami.dao;

import com.nami.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeProductDataAccessService implements ProductDao {

    private static List<Product> DB = new ArrayList<>();

    @Override
    public int insertProduct(UUID id, Product product) {
        DB.add(new Product(id, product.getName()));
        return 1;
    }

    @Override
    public List<Product> selectAllProducts() {
        return DB;
    }

    @Override
    public Optional<Product> selectProductById(UUID id) {
        return DB.stream().filter(product ->  product.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteProductById(UUID id) {
        Optional<Product> productMaybe = selectProductById(id);
        if(productMaybe.isPresent()){
            DB.remove(productMaybe);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateProductById(UUID id, Product product) {
        return selectProductById(id).map(p -> {
            int indexOfProductToUpdate = DB.indexOf(p);
            if(indexOfProductToUpdate >= 0){
                DB.set(indexOfProductToUpdate, new Product(id, product.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}

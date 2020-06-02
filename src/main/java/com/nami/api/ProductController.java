package com.nami.api;

import com.nami.model.Product;
import com.nami.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/product")
@RestController
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addPerson(@NonNull @RequestBody @Validated @Valid Product product){
        productService.addProduct(product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") UUID id){
        productService.deleteProduct(id);
    }

    @PutMapping(path = "{id}")
    public void updateProduct(@PathVariable("id") UUID id, @NonNull @RequestBody @Validated Product product){
        productService.updateProduct(id, product);
    }

    @GetMapping(path = "{id}")
    public Product getProductById(@PathVariable("id") UUID id){
        return productService.getProductById(id).orElse(null);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}

package kh.edu.istad.restdemo.controller;

import kh.edu.istad.restdemo.db.ProductDbConfig;
import kh.edu.istad.restdemo.domain.Product;
import kh.edu.istad.restdemo.dto.CreateProductRequest;
import kh.edu.istad.restdemo.dto.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDbConfig productDb;

    // CRUD: Create, Read, Update, Delete

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) String title
    ) {
        return productDb.selectAll(title);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productDb.selectById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        productDb.removeById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product createProduct(
            @RequestBody CreateProductRequest productRequest
    ) {
        return productDb.save(productRequest);
    }

    @PatchMapping("/{id}")
    public Product updateProductById(
            @PathVariable Integer id,
            @RequestBody UpdateProductRequest updateRequest
    ) {

        return productDb.updateById(id, updateRequest);

    }

}

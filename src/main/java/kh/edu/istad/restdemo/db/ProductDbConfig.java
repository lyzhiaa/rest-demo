package kh.edu.istad.restdemo.db;

import kh.edu.istad.restdemo.domain.Product;
import kh.edu.istad.restdemo.dto.CreateProductRequest;
import kh.edu.istad.restdemo.dto.UpdateProductRequest;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class ProductDbConfig {

    private final List<Product> products;
    private final Random random;

    public ProductDbConfig() {

        products = new ArrayList<>();
        random = new Random();

        Product pro1 = new Product();
        pro1.setId( random.nextInt(999) );
        pro1.setTitle("Coca Cola");
        pro1.setPrice( BigDecimal.valueOf(1.25) );
        pro1.setQty(30);
        pro1.setIsActive(true);

        Product pro2 = new Product();
        pro2.setId( random.nextInt(999) );
        pro2.setTitle("Pepsi");
        pro2.setPrice( BigDecimal.valueOf(1.5) );
        pro2.setQty(20);
        pro2.setIsActive(true);

//        products.addAll(List.of(pro1, pro2));
        products.add(pro1);
        products.add(pro2);
    }

    public List<Product> selectAll(String title) {

        if (title == null) {
            return products;
        }

        return products.stream()
                .filter(
                        pro -> pro.getTitle().toLowerCase()
                                .contains(title.toLowerCase())
                ).toList();

    }

    public Product selectById(Integer id) {
        return products.stream()
                .filter(pro -> pro.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void removeById(Integer id) {
        products.removeIf(
                pro -> pro.getId().equals(id)
        );
    }


    public Product save(CreateProductRequest productRequest) {

        Product product = new Product();
        product.setId( random.nextInt(999) );
        product.setTitle(productRequest.title());
        product.setPrice(productRequest.price());
        product.setQty(productRequest.qty());
        product.setIsActive(true);

        products.add(product);

        return product;
    }

    public Product updateById(Integer id, UpdateProductRequest updateRequest) {

        Product foundProduct = selectById(id);

        if (updateRequest.title() != null) {
            foundProduct.setTitle(updateRequest.title());
        }

        if (updateRequest.price() != null) {
            foundProduct.setPrice(updateRequest.price());
        }

        if (updateRequest.qty() != null) {
            foundProduct.setQty(updateRequest.qty());
        }

        if (updateRequest.isActive() != null) {
            foundProduct.setIsActive(updateRequest.isActive());
        }

        return foundProduct;
    }
}

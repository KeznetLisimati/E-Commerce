package zw.co.kez.productservice.services;

import zw.co.kez.productservice.dtos.ProductDTO;
import zw.co.kez.productservice.models.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductDTO productDTO, Long productId);

    List<Product> findAllProducts();

    public Long quantity();

    Product findProductById(Long productId);

//    List<Product> findProductByCategory(Long categoryId);

    void deleteAllProduct();

    void deleteProductById(Long productId);

    void updateProduct(ProductDTO productDTO, Long productId);

}

package zw.co.kez.productservice.services;

import zw.co.kez.productservice.dtos.ProductDTO;
import zw.co.kez.productservice.models.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductDTO productDTO);

    List<Product> findAllProducts();

    Product findProductById(Long id);

    void deleteAllProduct();

    void deleteProductById(Long id);

    void updateProduct(ProductDTO productDTO, Long id);

}

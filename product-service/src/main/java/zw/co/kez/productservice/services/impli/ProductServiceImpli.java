package zw.co.kez.productservice.services.impli;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.kez.productservice.dtos.ProductDTO;
import zw.co.kez.productservice.enums.ProductStatus;
import zw.co.kez.productservice.models.Product;
import zw.co.kez.productservice.repositories.ProductRepo;
import zw.co.kez.productservice.services.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpli implements ProductService {

    private final ProductRepo productRepo;
    @Override
    public void saveProduct(ProductDTO productDTO) {
        log.info("Now building the product before saving to the database");
          Product product = Product.builder()
                  .name(productDTO.getName())
                  .description(productDTO.getDescription())
                  .quantity(productDTO.getQuantity())
                  .build();

          productRepo.save(product);
          log.info("Product save success");
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepo.findAllByProductStatus(ProductStatus.ACTIVE);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow();
    }

    @Override
    public void deleteAllProduct() {
        List<Product> products = findAllProducts();
        for (Product product : products){
            product.setProductStatus(ProductStatus.INACTIVE);
            productRepo.save(product);
        }
    }

    @Override
    public void deleteProductById(Long id) {
      Product product = findProductById(id);
              product.setProductStatus(ProductStatus.INACTIVE);
              productRepo.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO, Long id) {
        Product updateProduct = productRepo.findById(id).orElseThrow();
        updateProduct.setName(productDTO.getName() == null? updateProduct.getName() : productDTO.getName());
        updateProduct.setDescription(productDTO.getDescription() == null?
                updateProduct.getDescription() : productDTO.getDescription());
        updateProduct.setQuantity(updateProduct.getQuantity());
        log.info("Product updated successfully");
    }
}

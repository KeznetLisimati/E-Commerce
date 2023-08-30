package zw.co.kez.productservice.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.kez.productservice.dtos.ProductDTO;
import zw.co.kez.productservice.enums.ProductStatus;
import zw.co.kez.productservice.exceptions.ResourceNotFoundException;
import zw.co.kez.productservice.models.Category;
import zw.co.kez.productservice.models.Product;
import zw.co.kez.productservice.repositories.CategoryRepository;
import zw.co.kez.productservice.repositories.ProductRepository;
import zw.co.kez.productservice.services.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public void saveProduct(ProductDTO productDTO, Long productId) {
        log.info("Received product payload -> {} ", productDTO);
        Optional<Product> prod = productRepository.findById(productId);

        if (prod.isEmpty()) {

            Category category = categoryRepository.findById(productDTO.getCategory().getCategoryId())
                    .orElseThrow(()-> new ResourceNotFoundException("Category with given Id "
                            +productDTO.getCategory().getCategoryId()+" not found!"));

            Product product = Product.builder()
                    .productId(productDTO.getProductId())
                    .name(productDTO.getName())
                    .description(productDTO.getDescription())
                    .price(productDTO.getPrice())
                    .dateCreated(productDTO.getDateCreated())
                    .lastUpdated(productDTO.getLastUpdated())
                    .productStatus(productDTO.getProductStatus())
                    .quantity(productDTO.getQuantity())
                    .image(productDTO.getName())
                    .build();
            product.setCategory(category);

            productRepository.save(product);
        }else {

            Category category = categoryRepository.findById(productDTO.getCategory().getCategoryId())
                    .orElseThrow(()-> new ResourceNotFoundException("Category with given Id "
                            +productDTO.getCategory().getCategoryId()+" not found!"));

            prod.get().setProductId(prod.get().getProductId());
            prod.get().setName(prod.get().getName());
            prod.get().setDescription(prod.get().getDescription());
            prod.get().setPrice(prod.get().getPrice());
            prod.get().setDateCreated(prod.get().getDateCreated());
            prod.get().setLastUpdated(prod.get().getLastUpdated());
            prod.get().setProductStatus(prod.get().getProductStatus());
            prod.get().setQuantity(productDTO.getQuantity());
            prod.get().setImage(prod.get().getImage());
            prod.get().setCategory(prod.get().getCategory());
            productRepository.save(prod.get());
        }

    }
    public Page<Product> showList(Pageable pageable){
        return productRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.DESC, "amount"))));

    }

    @Override
    public List<Product> findAllProducts() {
        long count = productRepository.count();
        log.info("There are " + count + " products available");
        return productRepository.findAllByProductStatus(ProductStatus.AVAILABLE);

    }

    @Override
    public Long quantity() {
        return productRepository.count();
    }
    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow();
    }

//    @Override
//    public List<Product> findProductByCategory(Long categoryId) {
//        return productRepository.findProductByCategory(categoryId);
//    }

    @Override
    public void deleteAllProduct() {
        List<Product> products = findAllProducts();
        for (Product product : products){
            product.setProductStatus(ProductStatus.UNAVAILABLE);
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProductById(Long productId) {
      Product product = findProductById(productId);
              product.setProductStatus(ProductStatus.UNAVAILABLE);
              productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO, Long productId) {

        Product updateProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("The requested product does not found"));

        updateProduct.setName(productDTO.getName() == null?
                updateProduct.getName() : productDTO.getName());
        updateProduct.setDescription(productDTO.getDescription() == null?
                updateProduct.getDescription() : productDTO.getDescription());
        updateProduct.setProductStatus(updateProduct.getProductStatus() == null?
                productDTO.getProductStatus() : updateProduct.getProductStatus());
        updateProduct.setDateCreated(updateProduct.getDateCreated() == null?
                productDTO.getDateCreated() : updateProduct.getDateCreated());

        updateProduct.setCategory(updateProduct.getCategory());
        updateProduct.setLastUpdated(productDTO.getLastUpdated());
        updateProduct.setPrice(productDTO.getPrice());
        updateProduct.setQuantity(productDTO.getQuantity());
        updateProduct.setImage(updateProduct.getImage() == null?
                productDTO.getImage() : updateProduct.getImage());

        productRepository.save(updateProduct);

        log.info("Product "+ updateProduct +" updated successfully");

    }
}

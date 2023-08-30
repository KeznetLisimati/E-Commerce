package zw.co.kez.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zw.co.kez.productservice.enums.ProductStatus;
import zw.co.kez.productservice.models.Category;
import zw.co.kez.productservice.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{
   List<Product> findAllByProductStatus(ProductStatus productStatus);
   //List<Product> findProductByCategory(Long category);

}

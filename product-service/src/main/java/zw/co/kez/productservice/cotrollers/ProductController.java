package zw.co.kez.productservice.cotrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.kez.productservice.dtos.ProductDTO;
import zw.co.kez.productservice.exceptions.ArrayIndexOutOfBoundsExcep;
import zw.co.kez.productservice.models.Product;
import zw.co.kez.productservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    @PostMapping("/addById/{id}")
    public ResponseEntity<String> saveProduct(@RequestBody ProductDTO productDTO,
                                              @PathVariable("id") Long productId){

        if(productDTO.getQuantity() < 1 || productDTO.getQuantity() > 1000){
              return new ResponseEntity<>("Failed to save product, " +
                    "quantity should be greater than '1' and less than '1000'", HttpStatus.BAD_REQUEST);
        } else {
            productService.saveProduct(productDTO, productId);
            return new ResponseEntity<>("Product " +
                    productDTO.getName() + " successfully saved ", HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long productId){
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
//    @GetMapping("/findProductByCategory/{id}")
//    public ResponseEntity<List<Product>> findProductByCategory(@PathVariable("id") Long categoryId){
//        List<Product> product = productService.findProductByCategory(categoryId);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> product = productService.findAllProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/getQuantity")
    public ResponseEntity<Long> getQuantity(){
        Long count = productService.quantity();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") Long productId) {
        productService.updateProduct(productDTO, productId);
        return new ResponseEntity<>("Product " + productDTO.getName() + " was updated successfully", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") ProductDTO productDTO, Long productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>("Product" + productDTO.getName() + " deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllProducts() {
        productService.deleteAllProduct();
        return new ResponseEntity<>("All products deleted successfully", HttpStatus.OK);
    }

}

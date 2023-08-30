package zw.co.kez.productservice.cotrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.kez.productservice.dtos.ProductDTO;
import zw.co.kez.productservice.models.Product;
import zw.co.kez.productservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping("/add")
    public ResponseEntity<String> saveProduct(@RequestBody ProductDTO productDTO){
        productService.saveProduct(productDTO);
        return new ResponseEntity<>("Product "+
                productDTO.getName() + "successfully saved ", HttpStatus.ACCEPTED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id){
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> product = productService.findAllProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") ProductDTO productDTO, Long id) {
        productService.updateProduct(productDTO, id);
        return new ResponseEntity<>("Product " + productDTO.getName() + " was updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") ProductDTO productDTO, Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Movie " + productDTO.getName() + " deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllProducts() {
        productService.deleteAllProduct();
        return new ResponseEntity<>("All movies successfully deleted", HttpStatus.OK);
    }

}

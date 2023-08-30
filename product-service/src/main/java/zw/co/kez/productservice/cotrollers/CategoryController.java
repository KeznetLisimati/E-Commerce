package zw.co.kez.productservice.cotrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.kez.productservice.dtos.CategoryDTO;
import zw.co.kez.productservice.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping("/createById/{id}")
    public ResponseEntity<String> saveProduct(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Long categoryId){
        categoryService.saveCategory(categoryDTO, categoryId);
        return new ResponseEntity<>("Category "+
                categoryDTO.getName() + " successfully saved ", HttpStatus.ACCEPTED);
    }

}

package zw.co.kez.productservice.services.impli;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.kez.productservice.dtos.CategoryDTO;
import zw.co.kez.productservice.models.Category;
import zw.co.kez.productservice.repositories.CategoryRepo;
import zw.co.kez.productservice.services.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepository;
    @Override
    public void saveCategory(CategoryDTO categoryDTO) {

        Category category = Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();

        categoryRepository.save(category);

    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public void deleteAllCategory() {
       categoryRepository.deleteAll();
    }

    @Override
    public void deleteCategoryById(Long id) {
     categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO, Long id) {
        Category updatedCategory = categoryRepository.findById(id).orElseThrow();
        updatedCategory.setName(categoryDTO.getName() == null? updatedCategory.getName() : categoryDTO.getName());
        updatedCategory.setDescription(categoryDTO.getDescription() == null? updatedCategory.getDescription() : categoryDTO.getDescription());
        categoryRepository.save(updatedCategory);
    }
}

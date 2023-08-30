package zw.co.kez.productservice.services.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.kez.productservice.dtos.CategoryDTO;
import zw.co.kez.productservice.models.Category;
import zw.co.kez.productservice.repositories.CategoryRepository;
import zw.co.kez.productservice.services.CategoryService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public String saveCategory(CategoryDTO categoryDTO, Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        log.info("Inside here.................................................................");
        if (category.isEmpty()){
            Category categoryBuilt = Category.builder()
                    .categoryId(categoryDTO.getCategoryId())
                    .name(categoryDTO.getName())
                    .description(categoryDTO.getDescription())
                    .build();

            categoryRepository.save(categoryBuilt);
        } else {
            return "Category with id -> " + category.get().getCategoryId() + "is already saved!";
        }
       return null;
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

package zw.co.kez.productservice.services;

import zw.co.kez.productservice.dtos.CategoryDTO;
import zw.co.kez.productservice.models.Category;

import java.util.List;

public interface CategoryService {

    public String saveCategory(CategoryDTO categoryDTO, Long categoryId);

    List<Category> findAllCategories();

    Category findCategoryById(Long id);

    void deleteAllCategory();

    void deleteCategoryById(Long id);

    void updateCategory(CategoryDTO categoryDTO, Long id);
}

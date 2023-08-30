package zw.co.kez.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.kez.productservice.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}

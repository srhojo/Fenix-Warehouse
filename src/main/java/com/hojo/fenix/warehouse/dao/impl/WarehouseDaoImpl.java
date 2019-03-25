package com.hojo.fenix.warehouse.dao.impl;

import com.hojo.fenix.warehouse.dao.WarehouseDao;
import com.hojo.fenix.warehouse.dao.repositories.CategoryRepository;
import com.hojo.fenix.warehouse.dao.repositories.FoodRepository;
import com.hojo.fenix.warehouse.dao.repositories.SubCategoryRepository;
import com.hojo.fenix.warehouse.domain.entities.CategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.SubCategoryEntity;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.hojo.fenix.warehouse.utils.exceptions.ExceptionConstants.*;

@Repository
@Transactional
public class WarehouseDaoImpl implements WarehouseDao {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final FoodRepository foodRepository;

    public WarehouseDaoImpl(final CategoryRepository categoryRepository,
                            final SubCategoryRepository subCategoryRepository, final FoodRepository foodRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.foodRepository = foodRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryEntity createCategory(final CategoryEntity categoryEntity) {
        if (categoryEntity.getId() != null) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE, ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return categoryRepository.save(categoryEntity);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryEntity updateCategory(final CategoryEntity categoryEntity) {
        this.getCategory(categoryEntity.getId());
        return categoryRepository.save(categoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryEntity getCategory(final Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCategory(final Long id) {
        final CategoryEntity category = this.getCategory(id);
        category.getSubcategories().forEach(subCategoryEntity -> {
            subCategoryEntity.setCategoryId(null);
            subCategoryRepository.save(subCategoryEntity);
        });
        categoryRepository.deleteById(category.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategoryEntity> searchCategories(final Specification<CategoryEntity> spec) {
        return categoryRepository.findAll(spec);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubCategoryEntity createSubCategory(final SubCategoryEntity subCategoryEntity) {
        if (subCategoryEntity.getId() != null) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE, ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return subCategoryRepository.save(subCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubCategoryEntity updateSubCategory(final SubCategoryEntity subCategoryEntity) {
        this.getSubcategory(subCategoryEntity.getId());
        return subCategoryRepository.save(subCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubCategoryEntity getSubcategory(final Long id) {
        return subCategoryRepository.findById(id).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSubcategory(Long id) {
        SubCategoryEntity subCategoryEntity = getSubcategory(id);
        if (subCategoryEntity.getCategoryId() != null) {
            CategoryEntity categoryEntity = categoryRepository.findById(subCategoryEntity.getCategoryId()).get();
            categoryEntity.getSubcategories().removeIf(sc -> sc.getCategoryId().equals(id));
            categoryRepository.save(categoryEntity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubCategoryEntity> searchSubCategories(final Specification<SubCategoryEntity> specification) {
        return subCategoryRepository.findAll(specification);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity createFood(final FoodEntity foodEntity) {
        if (!StringUtils.isEmpty(foodEntity.getName())) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE, ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return foodRepository.save(foodEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity updateFood(final FoodEntity foodEntity) {
        this.getFood(foodEntity.getName());
        return foodRepository.save(foodEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity getFood(final String name) {
        return foodRepository.findById(name).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, name)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodEntity> searchFoods(final Specification<FoodEntity> specification) {
        return foodRepository.findAll(specification);
    }

}

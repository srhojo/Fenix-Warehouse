package com.gft.demos.warehouse.dao.impl;

import com.gft.demos.warehouse.dao.WarehouseCategoriesDao;
import com.gft.demos.warehouse.dao.repositories.SubCategoryRepository;
import com.gft.demos.warehouse.domain.entities.ProductCategoryEntity;
import com.gft.demos.warehouse.domain.entities.ProductSubCategoryEntity;
import com.gft.demos.warehouse.utils.exceptions.ExceptionConstants;
import com.gft.demos.warehouse.utils.exceptions.WarehouseException;
import com.gft.demos.warehouse.dao.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
class WarehouseCategoriesDaoImpl implements WarehouseCategoriesDao {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;


    public WarehouseCategoriesDaoImpl(final CategoryRepository categoryRepository,
                                      final SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryEntity createCategory(final ProductCategoryEntity productCategoryEntity) {
        if (categoryRepository.existsById(productCategoryEntity.getName())) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return categoryRepository.save(productCategoryEntity);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryEntity updateCategory(final ProductCategoryEntity productCategoryEntity) {
        this.getCategory(productCategoryEntity.getName());
        return categoryRepository.save(productCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryEntity getCategory(final String name) {
        return categoryRepository.findById(name).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, name)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCategory(final String name) {
        final ProductCategoryEntity category = this.getCategory(name);
        category.getSubcategories().forEach(subCategoryEntity -> {
            subCategoryEntity.setCategoryName(null);
            subCategoryRepository.save(subCategoryEntity);
        });
        categoryRepository.deleteById(category.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductCategoryEntity> searchCategories(final Specification<ProductCategoryEntity> spec) {
        return categoryRepository.findAll(spec);
    }

    @Override
    public Page<ProductCategoryEntity> searchCategories(Specification<ProductCategoryEntity> spec, Pageable pageable) {
        return categoryRepository.findAll(spec, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductSubCategoryEntity createSubCategory(final ProductSubCategoryEntity productSubCategoryEntity) {
        if (subCategoryRepository.existsById(productSubCategoryEntity.getName())) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return subCategoryRepository.save(productSubCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductSubCategoryEntity updateSubCategory(final ProductSubCategoryEntity productSubCategoryEntity) {
        this.getSubcategory(productSubCategoryEntity.getName());
        return subCategoryRepository.save(productSubCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductSubCategoryEntity getSubcategory(final String name) {
        return subCategoryRepository.findById(name).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, name)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSubcategory(String name) {
        ProductSubCategoryEntity productSubCategoryEntity = getSubcategory(name);
        if (productSubCategoryEntity.getCategoryName() != null) {
            categoryRepository.findById(productSubCategoryEntity.getCategoryName()).ifPresent(productCategoryEntity -> {
                productCategoryEntity.getSubcategories().removeIf(sc -> sc.getCategoryName().equals(name));
                categoryRepository.save(productCategoryEntity);
            });
        }
        subCategoryRepository.deleteById(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductSubCategoryEntity> searchSubCategories(final Specification<ProductSubCategoryEntity> specification) {
        return subCategoryRepository.findAll(specification);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ProductSubCategoryEntity> searchSubCategories(Specification<ProductSubCategoryEntity> specification, Pageable pageable) {
        return subCategoryRepository.findAll(specification, pageable);
    }


}

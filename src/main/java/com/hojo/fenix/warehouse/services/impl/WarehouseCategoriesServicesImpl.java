package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.domain.cdm.OffsetPagination;
import com.hojo.fenix.warehouse.domain.mappers.ProductCategoryMapper;
import com.hojo.fenix.warehouse.domain.mappers.ProductSubCategoryMapper;
import com.hojo.fenix.warehouse.domain.requests.CategoryRequest;
import com.hojo.fenix.warehouse.domain.requests.SubCategoryRequest;
import com.hojo.fenix.warehouse.dao.WarehouseCategoriesDao;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.cdm.OffsetPaginationRequest;
import com.hojo.fenix.warehouse.domain.entities.ProductCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.ProductSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.services.WarehouseCategoriesServices;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
class WarehouseCategoriesServicesImpl implements WarehouseCategoriesServices {

    private final WarehouseCategoriesDao warehouseCategoriesDao;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductSubCategoryMapper productSubCategoryMapper;
    private final QueryLanguajeComponentImpl<ProductCategoryEntity> qlCategory;
    private final QueryLanguajeComponentImpl<ProductSubCategoryEntity> qlSubCategory;

    public WarehouseCategoriesServicesImpl(final WarehouseCategoriesDao warehouseCategoriesDao,
                                           final ProductCategoryMapper productCategoryMapper,
                                           final ProductSubCategoryMapper productSubCategoryMapper,
                                           final QueryLanguajeComponentImpl<ProductCategoryEntity> qlCategory,
                                           final QueryLanguajeComponentImpl<ProductSubCategoryEntity> qlSubCategory) {
        this.warehouseCategoriesDao = warehouseCategoriesDao;
        this.productCategoryMapper = productCategoryMapper;
        this.productSubCategoryMapper = productSubCategoryMapper;
        this.qlCategory = qlCategory;
        this.qlSubCategory = qlSubCategory;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryEntity createCategory(final CategoryRequest category) {
        return warehouseCategoriesDao.createCategory(productCategoryMapper.mapToInner(category));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryEntity updateCategory(CategoryRequest category) {
        return warehouseCategoriesDao.updateCategory(productCategoryMapper.mapToInner(category));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryEntity updateSubCategoriesToCategory(CategoryUpdateSubCategoriesRequest request) {
        final ProductCategoryEntity productCategoryEntity = warehouseCategoriesDao.getCategory(request.getCategoryId());

        final List<ProductSubCategoryEntity> subCategoryEntities = new ArrayList<>();
        request.getSubcategoryIds().forEach(subcategoryId -> subCategoryEntities.add(warehouseCategoriesDao.getSubcategory(subcategoryId)));

        productCategoryEntity.setSubcategories(subCategoryEntities);

        return warehouseCategoriesDao.updateCategory(productCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<ProductCategoryEntity> getCategories(final String filter, final Integer limit, final Long offset) {
        if (offset != null && limit != null) {
            final Pageable pageable = OffsetPaginationRequest.of(limit, offset);
            final Page<ProductCategoryEntity> categoryEntityPage = warehouseCategoriesDao.searchCategories(qlCategory.parse(filter), pageable);
            final OffsetPagination offsetPagination = new OffsetPagination(limit, offset, categoryEntityPage.getTotalElements());
            return new ContainerList<>(categoryEntityPage.get().collect(Collectors.toList()), offsetPagination);
        } else {
            return new ContainerList<>(warehouseCategoriesDao.searchCategories(qlCategory.parse(filter)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCategory(String name) {
        warehouseCategoriesDao.deleteCategory(name);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ProductSubCategoryEntity createSubCategory(final SubCategoryRequest subCategoryRequest) {
        return warehouseCategoriesDao.createSubCategory(productSubCategoryMapper.mapToInner(subCategoryRequest));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductSubCategoryEntity updateSubCategory(SubCategoryRequest subCategoryRequest) {
        return warehouseCategoriesDao.updateSubCategory(productSubCategoryMapper.mapToInner(subCategoryRequest));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<ProductSubCategoryEntity> searchSubCategories(final String filter, final Integer limit, final Long offset) {
        if (offset != null && limit != null) {
            final Pageable pageable = OffsetPaginationRequest.of(limit, offset);
            final Page<ProductSubCategoryEntity> productSubCategoryPage = warehouseCategoriesDao.searchSubCategories(qlSubCategory.parse(filter), pageable);
            final OffsetPagination offsetPagination = new OffsetPagination(limit, offset, productSubCategoryPage.getTotalElements());
            return new ContainerList<>(productSubCategoryPage.get().collect(Collectors.toList()), offsetPagination);
        } else {
            return new ContainerList<>(warehouseCategoriesDao.searchSubCategories(qlSubCategory.parse(filter)));
        }
    }


}

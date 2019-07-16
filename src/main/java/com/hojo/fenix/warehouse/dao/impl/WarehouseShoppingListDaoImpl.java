package com.hojo.fenix.warehouse.dao.impl;

import com.hojo.fenix.warehouse.dao.WarehouseShoppingListDao;
import com.hojo.fenix.warehouse.dao.repositories.ShoppingListRepository;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseException;
import com.hojo.fenix.warehouse.utils.exceptions.ExceptionConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@Transactional
class WarehouseShoppingListDaoImpl implements WarehouseShoppingListDao {

    private final ShoppingListRepository shoppingListRepository;

    public WarehouseShoppingListDaoImpl(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity getById(Long id) {
        return shoppingListRepository.findById(id).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, id)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShoppingListEntity> search(Specification<ShoppingListEntity> specification) {
        return shoppingListRepository.findAll(specification);
    }

    @Override
    public Page<ShoppingListEntity> search(Specification<ShoppingListEntity> specification, Pageable pageable) {
        return shoppingListRepository.findAll(specification, pageable);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity create(ShoppingListEntity shoppingListEntity) {
        if (!StringUtils.isEmpty(shoppingListEntity.getId())) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return save(shoppingListEntity);

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity update(ShoppingListEntity shoppingListEntity) {
        this.getById(shoppingListEntity.getId());
        return save(shoppingListEntity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        ShoppingListEntity entity = this.getById(id);
        shoppingListRepository.delete(entity);
    }

    private ShoppingListEntity save(ShoppingListEntity shoppingListEntity) {
        try {
            return shoppingListRepository.save(shoppingListEntity);
        } catch (Exception ex) {
            throw new WarehouseException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionConstants.ERRORS_DAO_SAVE_SHOPPING_LIST_CODE, String.format(ExceptionConstants.ERRORS_DAO_SAVE_SHOPPING_LIST_MESSAGE, shoppingListEntity, ex.getMessage()));
        }
    }
}

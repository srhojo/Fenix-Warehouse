package com.hojo.fenix.warehouse.dao.impl;

import com.hojo.fenix.warehouse.dao.WarehouseShoppingListDao;
import com.hojo.fenix.warehouse.dao.repositories.ShoppingListRepository;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
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
public class WarehouseShoppingListDaoImpl implements WarehouseShoppingListDao {

    private final ShoppingListRepository shoppingListRepository;

    public WarehouseShoppingListDaoImpl(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity getById(Long id) {
        return shoppingListRepository.findById(id).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, id)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShoppingListEntity> search(Specification<ShoppingListEntity> specification) {
        return shoppingListRepository.findAll(specification);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity create(ShoppingListEntity shoppingListEntity) {
        if (!StringUtils.isEmpty(shoppingListEntity.getId())) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE, ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return shoppingListRepository.save(shoppingListEntity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity update(ShoppingListEntity shoppingListEntity) {
        this.getById(shoppingListEntity.getId());
        return shoppingListRepository.save(shoppingListEntity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        ShoppingListEntity entity = this.getById(id);
        shoppingListRepository.delete(entity);
    }
}

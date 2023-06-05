package com.emte.storemanager.dao;

import com.emte.model.StoreTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<StoreTransaction, Integer> {
}

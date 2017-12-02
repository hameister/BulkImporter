package org.hameister.bulk.data;

import org.hameister.bulk.data.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hameister on 01.12.17.
 */
@Repository
@Transactional
public interface ItemRepository extends CrudRepository<Item, String> {
    List<Item> findAll();
}

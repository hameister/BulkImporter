package org.hameister.bulk.service;

import org.hameister.bulk.data.ItemRepository;
import org.hameister.bulk.data.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hameister on 01.12.17.
 */
@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {

        Assert.notNull(itemRepository, "Repo must not be null");
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }


    //@Transactional
    public List<Item> bulkImport(List<Item> items) {

        List<Item> importedItems = new ArrayList<>();

        Iterable<Item> itemIterable = itemRepository.save(items);
        Iterator<Item> it = itemIterable.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            importedItems.add(i);
        }

        return importedItems;
    }
}

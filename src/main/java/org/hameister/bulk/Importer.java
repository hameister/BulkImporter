package org.hameister.bulk;

import org.hameister.bulk.data.Item;
import org.hameister.bulk.data.ItemRepository;
import org.hameister.bulk.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hameister on 01.12.17.
 */
@Component
public class Importer {
    private ItemRepository itemRepository;

    @Autowired
    public Importer(ItemService itemService, ItemRepository itemRepository) {
        Assert.notNull(itemService, "Service must not be null");
        Assert.notNull(itemRepository, "Repo must not be null");

        this.itemRepository = itemRepository;
        List items = new ArrayList();

        for (int i = 0; i < 3; i++) {
            Item item = new Item();
            item.setId(UUID.randomUUID().toString());
            item.setLocation("Table");
            item.setDescription("Item "+i);
            items.add(item);
        }

        List<Item> importedItems = itemService.bulkImport(items);
        System.out.println("Imported items:" + importedItems.size());


        itemRepository.findAll().forEach(i -> System.out.println(i.getId()+":"+ i.getLocation()+" "+i.getDescription()));
    }
}

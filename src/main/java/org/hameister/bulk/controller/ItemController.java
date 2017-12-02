package org.hameister.bulk.controller;

import org.hameister.bulk.data.Item;
import org.hameister.bulk.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hameister on 02.12.17.
 */
@RestController
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        Assert.notNull(itemService, "Service must not be null");
        this.itemService = itemService;
    }

    @PostMapping(value = "/items")
    public ResponseEntity<List<Item>> importItems(@RequestBody List<Item> items) {
        return new ResponseEntity<List<Item>>(itemService.bulkImport(items), HttpStatus.CREATED);
    }

    @GetMapping(value = "/items")
    public ResponseEntity<List<Item>> getItems() {
        return new ResponseEntity<List<Item>>(itemService.getItems(), HttpStatus.OK);
    }
}

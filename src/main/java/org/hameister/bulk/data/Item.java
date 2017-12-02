package org.hameister.bulk.data;

import javax.persistence.*;

/**
 * Created by hameister on 01.12.17.
 */
@Entity
@Table(name = "Item")
public class Item {

    @Id
    String id;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

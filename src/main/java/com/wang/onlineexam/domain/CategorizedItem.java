package com.wang.onlineexam.domain;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CATEGORY_ITEM")
@org.hibernate.annotations.Immutable
public class CategorizedItem {

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "CATEGORY_ID")
        private Long categoryId;
        @Column(name = "ITEM_ID")
        private Long itemId;

        public Id() {
        }

        public Id(Long categoryId, Long itemId) {
            this.categoryId = categoryId;
            this.itemId = itemId;
        }
        //implementing equals and hashCode
    }

    @EmbeddedId
    private Id id = new Id();
    @Column(updatable = false)
    @NotNull
    private String addedBy;
    @Column(updatable = false)
    @NotNull
    @CreationTimestamp
    private LocalDateTime addedOn;
    @ManyToOne
    @JoinColumn(
            name = "CATEGORY_ID",
            insertable = false, updatable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(
            name = "ITEM_ID",
            insertable = false, updatable = false)
    private Item item;

    public CategorizedItem(
            String addedByUsername,
            Category category,
            Item item) {
        this.addedBy = addedByUsername;
        this.category = category;
        this.item = item;
        this.id.categoryId = category.getId();
        this.id.itemId = item.getId();
        //category.addCategorizedItem(this);
        //item.addCategorizedItem(this);
    }
    // ...

    public CategorizedItem() {

    }
}

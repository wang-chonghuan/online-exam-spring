package com.wang.onlineexam.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class DemoManyToMany {
/*
    @Entity
    public class Blog {
        @Id
        @Column(name="id")
        private int id;

        @ManyToMany(cascade= CascadeType.ALL)
        @JoinTable(
                name = "blog_tag_relation",
                joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
        private List<Tag> tags = new ArrayList<Tag>();
    }

    @Entity
    public class Tag {
        @Id
        @Column(name="id")
        private int id;
        @ManyToMany(mappedBy="BlogTagRelation")
        private List<Blog> blogs = new ArrayList<Blog>();
    }

    @Entity
    public class BlogTagRelation {
        @Id
        @Column(name="id")
        private int id;

        @Column(name="blog_id")
        private int blogId;

        @Column(name="tag_id")
        private int tagId;
    }

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

    @Entity
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Long id;
        //…
        @OneToMany(mappedBy = "category")
        private Set<CategorizedItem> categorizedItems = new HashSet<>();
        // ...

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    @Entity
    public class Item {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Long id;
        //…
        @OneToMany(mappedBy = "item")
        private Set<CategorizedItem> categorizedItems = new HashSet<>();
        // ...

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
*/
}




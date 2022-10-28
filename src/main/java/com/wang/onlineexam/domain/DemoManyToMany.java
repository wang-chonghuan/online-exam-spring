package com.wang.onlineexam.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
*/
}




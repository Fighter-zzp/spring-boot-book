package com.zzp.spring.boot.admin.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

    private static final long serialVersionUID = -7536613142331362542L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String tagName;
    @Temporal(TemporalType.DATE)
    private Date tagInputDate;

    @ManyToMany(cascade = { CascadeType.REFRESH})
    @JoinTable(name = "article_tag", joinColumns = {@JoinColumn(name = "tag_id")}, inverseJoinColumns = {@JoinColumn(name = "article_id")})
    private List<Article> articleList;

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}

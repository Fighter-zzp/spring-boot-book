package com.zzp.spring.boot.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "tag")
//@Data
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

    @ManyToMany/*(mappedBy = "tagList",fetch = FetchType.EAGER)*/
//    @JsonIgnore( = true)
    @JoinTable(name = "article_tag", joinColumns = {@JoinColumn(name = "tag_id")}, inverseJoinColumns = {@JoinColumn(name = "article_id")})
    private List<Article> articleList = new ArrayList<>();


}

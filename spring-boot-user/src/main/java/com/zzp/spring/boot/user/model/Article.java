package com.zzp.spring.boot.user.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "article")
//@Data
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    private static final long serialVersionUID = 4967006908141911451L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    private String articleName;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String articleContent;
    private String articleAuthors;
    @Temporal(TemporalType.DATE)
    private Date articleInputDate;
    private Integer articleReadingTime;
    private Integer isTop;
    private Integer isEnable;

    @ManyToMany/*(targetEntity = Tag.class,cascade = ALL,fetch = FetchType.EAGER)*/
    @JoinTable(name = "article_tag", joinColumns = {@JoinColumn(name = "article_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tagList=new ArrayList<>();


    //项目内使用，非数据库字段
    @Transient
    private int imageNo;
    @Transient
    private String articleIntroduction;
    @Transient
    private String articleShowContent;

}

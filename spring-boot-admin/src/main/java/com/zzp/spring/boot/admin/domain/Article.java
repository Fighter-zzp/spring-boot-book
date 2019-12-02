package com.zzp.spring.boot.admin.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article")
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
    private Long articleInputUser;
    private Integer articleReadingTime;
    private Integer isTop;
    private Integer isEnable;

    @ManyToMany(cascade = { CascadeType.REFRESH})
    @JoinTable(name = "article_tag", joinColumns = {@JoinColumn(name = "article_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tagList;

    @Transient
    private String tagsStr;

}

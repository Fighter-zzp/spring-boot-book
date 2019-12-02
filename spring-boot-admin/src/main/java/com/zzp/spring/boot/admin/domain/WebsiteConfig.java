package com.zzp.spring.boot.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "website_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebsiteConfig implements Serializable {

    private static final long serialVersionUID = 7023358255818152969L;
    @Id
    private Long Id;
    private String blogName;
    private String authorName;
    private Long aboutPageArticleId;
    private String recordNumber;
    private String domainName;
    private String emailUsername;
}

package com.zzp.spring.boot.admin.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private Integer isEnable;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;

    @Transient
    private List<Long> roleIdList;

    public User(Long userId,String username, String password, String email, Integer isEnable,List<Role> roleList) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isEnable = isEnable;
        this.roleList = roleList;
    }
}

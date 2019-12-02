package com.zzp.spring.boot.admin.service.impl;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.domain.Pages;
import com.zzp.spring.boot.admin.domain.Role;
import com.zzp.spring.boot.admin.domain.User;
import com.zzp.spring.boot.admin.repository.UserRepository;
import com.zzp.spring.boot.admin.service.IRoleService;
import com.zzp.spring.boot.admin.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Override
    public Page<User> findAllBySearch(Pages pages, Long userId, String username) {
        var pageable = PageRequest.of(pages.getPage(), pages.getPageSize(), Sort.Direction.DESC, "userId");
        return userRepository.findAll(this.getWhereClause(userId, username), pageable);
    }

    @Override
    public User findUserByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String saveOrUpdateUser(User user) {
        var roleList = new ArrayList<Role>();
        var roleIds = user.getRoleIdList();
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                Role role = roleService.findRoleByRoleId(roleId);
                roleList.add(role);
            }
            user.setRoleList(roleList);
        }
        if (user.getUserId() == null) {
            user.setIsEnable(Constants.NO);
        } else if (userRepository.findByUsername(user.getUsername()) != null) {
            return "用户名已存在！";
        }
        userRepository.save(user);
        return "保存成功！";
    }
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateUserIsEnable(Long userId, Integer isEnable) {
        User user = userRepository.findById(userId).get();
        user.setIsEnable(isEnable);
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private Specification<User> getWhereClause(Long userId, String username) {
        return (root, query, cb) ->{
            List<Predicate> predicate = new ArrayList<>();
            if (userId != null) {
                predicate.add(
                        cb.or(cb.equal(root.get("userId"), userId))
                );
            }
            if (StringUtils.isNotBlank(username)) {
                predicate.add(
                        cb.or(cb.like(root.get("username"), username + "%"))
                );
            }
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        };
    }
}

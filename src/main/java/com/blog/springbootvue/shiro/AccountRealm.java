package com.blog.springbootvue.shiro;

import com.blog.springbootvue.entity.User;
import com.blog.springbootvue.service.UserService;
import com.blog.springbootvue.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return (AuthorizationInfo) this.getAuthenticationCacheKey(principalCollection);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwt = (JwtToken) token;
        log.info("jwt---------------->{}", jwt);
        String userId = jwtUtils.checkToken((String)jwt.getPrincipal()).getSubject();
        User user = userService.getById(Long.parseLong(userId));
        if (user == null){
            throw new UnknownAccountException("账户不存在!");
        }
        if(user.getEnabled() == 0){
            throw new LockedAccountException("账户已被锁定！");
        }
        AccountProfile profile = new AccountProfile();
        try {
            BeanUtils.copyProperties(user, profile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}

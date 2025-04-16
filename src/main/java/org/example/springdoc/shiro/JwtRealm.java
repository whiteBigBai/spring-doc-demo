package org.example.springdoc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.example.springdoc.domain.User;
import org.example.springdoc.service.UserService;
import org.example.springdoc.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String userName = JwtUtil.getUserName(token);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (StringUtils.isEmpty(userName)) {
            return null;
        } else {
            User user = userService.queryUserByUserName(userName);
            info.addRole(user.getRole());
            return info;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        if (JwtUtil.isExpire(token)) {
            String userName = JwtUtil.getUserName(token);
            return new SimpleAuthenticationInfo(token, token, userName);
        } else {
            throw new AuthenticationException("token 无效!");
        }
    }
}

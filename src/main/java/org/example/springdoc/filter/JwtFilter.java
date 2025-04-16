package org.example.springdoc.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.example.springdoc.shiro.JwtToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter implements Filter {
    public static final String AUTHORIZATION = "Authorization";

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);
        HttpServletResponse res = WebUtils.toHttp(response);
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setHeader("Content-Type", "application/json;charset=UTF-8");
            res.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = WebUtils.toHttp(request);
        String token = req.getHeader(AUTHORIZATION);
        JwtToken jwtToken = new JwtToken(token);
        try {
            getSubject(request, response).login(jwtToken);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("isAccessAllowed");
        HttpServletRequest req = WebUtils.toHttp(request);
        log.info("isAccessAllowed URI : {} ", req.getRequestURI());
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);
        HttpServletResponse res = WebUtils.toHttp(response);
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        res.setHeader("Content-Type", "application/json;charset=UTF-8");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", 401);
        jsonObject.put("statusMsg", "登录失效!");
        res.getWriter().print(JSON.toJSONString(jsonObject));
        return false;
    }
}

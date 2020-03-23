package com.czxy.duidui.filter;

import com.czxy.duidui.domain.User;
import com.czxy.duidui.utils.JwtUtils;
import com.czxy.duidui.utils.Path;
import com.czxy.duidui.utils.RasUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request =
                currentContext.getRequest();

        String authorization = request.getHeader("authorization");

        if (authorization!=null) {
            currentContext.addZuulRequestHeader("token", authorization);

            try {
                JwtUtils.getObjectFromToken(authorization, RasUtils.getPublicKey(Path.publicPath), User.class);
            } catch (Exception e) {
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(403);
            }

        }


        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}

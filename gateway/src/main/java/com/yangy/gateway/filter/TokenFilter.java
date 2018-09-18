package com.yangy.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token校验
 *
 * @author yang yang
 * @create 2018/9/14
 * @since 1.0.0
 */
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext context = RequestContext.getCurrentContext();
//        return (boolean) context.get("isSuccess");

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();

        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("未授权");
            context.set("isSuccess", false);
        } else {
            context.setSendZuulResponse(true);
            context.setResponseStatusCode(200);
            context.set("isSuccess", token);
        }
        return null;
    }
}
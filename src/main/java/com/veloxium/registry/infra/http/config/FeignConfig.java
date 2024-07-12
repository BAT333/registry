package com.veloxium.registry.infra.http.config;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes!= null){
            final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
            requestTemplate.header(HttpHeaders.AUTHORIZATION,httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        }




   }
}

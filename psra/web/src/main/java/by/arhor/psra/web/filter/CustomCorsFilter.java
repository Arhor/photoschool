package by.arhor.psra.web.filter;

import static org.springframework.http.HttpHeaders.*;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCorsFilter extends OncePerRequestFilter {

    private static final String[] ALLOWED_HEADERS = {
            "x-requested-with",
            "authorization",
            "Content-Type",
            "Authorization",
            "credential",
            "X-XSRF-TOKEN",
            "X-CSRF-TOKEN"
    };

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain chain)
            throws IOException, ServletException {

        String headers = String.join(",", ALLOWED_HEADERS);

        String requestOrigin = request.getHeader(ORIGIN);

        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, requestOrigin);
        response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, "PATCH,POST,PUT,GET,OPTIONS,DELETE");
        response.setHeader(ACCESS_CONTROL_MAX_AGE, "3600");
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, headers);

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }

    }

}

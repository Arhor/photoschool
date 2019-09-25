package by.arhor.psra.web.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_MAX_AGE;
import static org.springframework.http.HttpHeaders.ORIGIN;

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
  public void doFilterInternal(
      HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {

    final var headers = String.join(",", ALLOWED_HEADERS);

    final var requestOrigin = req.getHeader(ORIGIN);

    res.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, requestOrigin);
    res.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
    res.setHeader(ACCESS_CONTROL_ALLOW_METHODS, "PATCH,POST,PUT,GET,OPTIONS,DELETE");
    res.setHeader(ACCESS_CONTROL_MAX_AGE, "3600");
    res.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, headers);

    // FIXME: what does it condition for?
    if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
      res.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(req, res);
    }
  }
}

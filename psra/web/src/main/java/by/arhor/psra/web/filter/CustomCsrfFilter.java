package by.arhor.psra.web.filter;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Class CustomCsrfFilter implements stateless CSRF protection. To successfully
 * pass a CSRF check, the request must contain the CSRF token generated on the
 * client side in the appropriate header and cookie, and the token in the header
 * must match the token in the cookie.
 *
 * @author Maksim Buryshynets
 * @version 1.0 11 March 2019
 */
@Component("customCsrfFilter")
public class CustomCsrfFilter extends OncePerRequestFilter {

    private static final String ERROR_MSG = "CSRF tokens missing or not matching";

    private static final String CSRF_COOKIE = "CSRF-TOKEN";
    private static final String CSRF_HEADER = "X-CSRF-TOKEN";
    private static final String SAFE_METHOD = "^(GET|HEAD|TRACE|OPTIONS)$";

    private final AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (csrfTokenIsRequired(request)) {

            String csrfHeaderToken = request.getHeader(CSRF_HEADER);
            String csrfCookieToken = getCsrfCookieToken(request);

            if (csrfCookieToken == null || !csrfCookieToken.equals(csrfHeaderToken)) {
                accessDeniedHandler.handle(request, response, new AccessDeniedException(ERROR_MSG));
                return;
            }

        }

        filterChain.doFilter(request, response);
    }

    private boolean csrfTokenIsRequired(HttpServletRequest request) {
        String actualMethod = request.getMethod();
        // TODO: replace method with instance Pattern method call
        return !Pattern.matches(SAFE_METHOD, actualMethod);
    }

    private String getCsrfCookieToken(HttpServletRequest request) {
        String csrfCookieToken = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            csrfCookieToken = Stream.of(cookies)
                    .filter(cookie -> cookie.getName().equals(CSRF_COOKIE))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }

        return csrfCookieToken;
    }
}

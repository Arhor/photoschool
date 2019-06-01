package by.arhor.psra.web.filter

import java.io.IOException

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import javax.servlet.{FilterChain, ServletException}
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.{AccessDeniedHandler, AccessDeniedHandlerImpl}
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

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
class CustomCsrfFilter extends OncePerRequestFilter {

  import CustomCsrfFilter._

  private val accessDeniedHandler: AccessDeniedHandler = new AccessDeniedHandlerImpl()

  @throws(classOf[ServletException])
  @throws(classOf[IOException])
  override protected def doFilterInternal(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          filterChain: FilterChain): Unit = {
    if (isTokenRequired(request)) {

      val csrfHeaderToken = request.getHeader(CSRF_HEADER)
      val csrfCookieToken = getCsrfCookieToken(request)

      if (csrfCookieToken == null || !csrfCookieToken.equals(csrfHeaderToken)) {
        accessDeniedHandler.handle(request, response, new AccessDeniedException(ERROR_MSG))
        return
      }

    }

    filterChain.doFilter(request, response)
  }

  private def isTokenRequired(request: HttpServletRequest): Boolean = request.getMethod match {
    case SAFE_METHOD() => true
    case _ => false
  }

  private def getCsrfCookieToken(request: HttpServletRequest): String = {
    val cookies = for {
      array  <- Some(request.getCookies).toArray
      cookie <- array
    } yield cookie

    cookies. filter { _.getName.equals(CSRF_COOKIE) }. map { _.getValue }. head
  }
}

object CustomCsrfFilter {

  private val ERROR_MSG   = "CSRF tokens missing or not matching"
  private val CSRF_COOKIE = "XSRF-TOKEN"
  private val CSRF_HEADER = "X-XSRF-TOKEN"
  private val SAFE_METHOD = "^(GET|HEAD|TRACE|OPTIONS)$".r

}

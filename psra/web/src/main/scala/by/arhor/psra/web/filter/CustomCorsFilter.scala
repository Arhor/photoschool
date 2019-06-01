package by.arhor.psra.web.filter

import java.io.IOException

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import javax.servlet.{FilterChain, ServletException}
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders._
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CustomCorsFilter extends OncePerRequestFilter {

  import CustomCorsFilter._

  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override def doFilterInternal(request: HttpServletRequest,
                                response: HttpServletResponse,
                                chain: FilterChain): Unit = {

    val headers = ALLOWED_HEADERS.mkString(",")
    val requestOrigin = request.getHeader(ORIGIN)

    response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, requestOrigin)
    response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
    response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, "PATCH,POST,PUT,GET,OPTIONS,DELETE")
    response.setHeader(ACCESS_CONTROL_MAX_AGE, "3600")
    response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, headers)

    // FIXME: what does it condition for?
    if ("OPTIONS".equalsIgnoreCase(request.getMethod)) {
      response.setStatus(HttpServletResponse.SC_OK)
    } else {
      chain.doFilter(request, response)
    }
  }

}

object CustomCorsFilter {
  private val ALLOWED_HEADERS = Array(
    "x-requested-with",
    "authorization",
    "Content-Type",
    "Authorization",
    "credential",
    "X-XSRF-TOKEN",
    "X-CSRF-TOKEN"
  )
}

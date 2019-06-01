package by.arhor.psra.config

import org.springframework.context.annotation.{Bean, Configuration}
import springfox.documentation.builders.{ApiInfoBuilder, PathSelectors, RequestHandlerSelectors}
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class Swagger2Config {

    @Bean def api(): Docket = new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("by.arhor.psrm.web.controller"))
      .paths(PathSelectors.ant("/*"))
      .build()
      .apiInfo(apiEndpointsInfo())


    private def apiEndpointsInfo(): ApiInfo = new ApiInfoBuilder()
      .title("REST API Title")
      .description("REST API Description")
      .version("1")
      .build()

}

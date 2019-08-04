package by.arhor.psra.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class ServiceConfig {

	@Bean def modelMapper: ModelMapper = new ModelMapper

}

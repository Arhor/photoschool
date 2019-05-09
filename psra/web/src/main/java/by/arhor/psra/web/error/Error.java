package by.arhor.psra.web.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.Value;

@Value
public class Error implements Serializable {

	private static final long serialVersionUID = 5507028058917355385L;
		
	private Code code;
	private String message;
	
	public enum Code {

		NOT_FOUND("10001");
		
		@Getter
		@JsonValue
		private final String code;
		
		private Code(String code) {
			this.code = code;
		}

	}

}

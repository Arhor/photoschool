package by.arhor.psra.web.error;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import by.arhor.psra.CoreVersion;

public final class Error implements Serializable {

	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;

	private final Code code;
	private final String message;

	public Error(Code code, String message) {
		this.code = code;
		this.message = message;
	}

	public Code getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Error error = (Error) o;
		return code == error.code
				&& message.equals(error.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, message);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Error.class.getSimpleName() + "[", "]")
				.add("code=" + code)
				.add("message='" + message + "'")
				.toString();
	}
}

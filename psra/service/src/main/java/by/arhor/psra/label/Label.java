package by.arhor.psra.label;

import lombok.Getter;

public enum Label {

    ERROR_PHOTO_NOT_FOUND("error.not.found.photo");

    @Getter
    private final String value;

    private Label(String value) {
        this.value = value;
    }

}

package by.arhor.psra.localization;

import lombok.Getter;

public enum Error implements Label {

    PHOTO_NOT_FOUND("error.not.found.photo"),
	GALLERY_NOT_FOUND("error.not.found.gallery");

    @Getter
    private final String value;

    private Error(String value) {
        this.value = value;
    }

}

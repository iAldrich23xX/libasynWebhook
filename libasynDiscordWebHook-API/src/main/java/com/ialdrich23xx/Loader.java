package com.ialdrich23xx;

import lombok.Getter;
import org.apache.commons.validator.UrlValidator;

public class Loader {

    @Getter
    private static Loader instance;

    public Loader() {
        instance = this;
    }

    public Boolean isValidUrl(String url)
    {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}

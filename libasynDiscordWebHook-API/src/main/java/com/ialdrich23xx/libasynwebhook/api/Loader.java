package com.ialdrich23xx.libasynwebhook.api;

import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base.BodyException;
import lombok.Getter;
import org.apache.commons.validator.routines.UrlValidator;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Set;

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

    public String quote(String string) {
        return "\"" + string + "\"";
    }

    public String formatToJson(Set<Map.Entry<String, Object>> format) {
        StringBuilder builder = new StringBuilder();

        builder.append("{");

        int i = 0;

        for (Map.Entry<String, Object> entry : format) {
            Object value = entry.getValue();
            builder.append(quote(entry.getKey())).append(":");

            if (value instanceof String) {
                builder.append(quote(String.valueOf(value)));
            } else if (value instanceof Integer) {
                builder.append(Integer.valueOf(String.valueOf(value)));
            } else if (value instanceof Boolean) {
                builder.append(value);
            } else if (value.getClass().isArray()) {
                builder.append("[");

                int len = Array.getLength(value);

                for (int j = 0; j < len; j++) {
                    builder.append(Array.get(value, j).toString()).append(j != len - 1 ? "," : "");
                }

                builder.append("]");
            } else {
                throw new BodyException((Throwable) value);
            }

            builder.append(++i == format.size() ? "}" : ",");
        }

        return builder.toString();
    }
}

package com.ialdrich23xx.libasynwebhook.api.discord.body.embed.components;

import com.ialdrich23xx.libasynwebhook.api.discord.body.embed.base.Structure;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Timestamp extends Structure {

    private DateTimeFormatter date;
    private OffsetDateTime format;

    public Timestamp(DateTimeFormatter date, OffsetDateTime format) {
        this.date = date;
        this.format = format;
    }

    public static Timestamp make(DateTimeFormatter date, OffsetDateTime format) {
        return new Timestamp(date, format);
    }

    public static Timestamp make(DateTimeFormatter date) {
        return new Timestamp(date, OffsetDateTime.now(ZoneId.of("UTC")));
    }

    public Timestamp setFormat(OffsetDateTime format) {
        this.format = format;

        return this;
    }

    public OffsetDateTime getFormat() {
        return this.format;
    }

    public DateTimeFormatter getDate() {
        return this.date;
    }

    @Override
    public Boolean build() {
        return date != null;
    }

    /**
     * @no-require
     */
    @Override
    public Map<String, Object> toArray() {
        return new HashMap<>();
    }

    @Override
    public String toString() {
        return "Timestamp(data=" + this.getFormat().format(this.getDate()) + ",timezone=" + this.getFormat().toString() + ")";
    }
}

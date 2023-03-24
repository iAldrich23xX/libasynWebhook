package com.ialdrich23xx.discord.body.embed.components;

import com.ialdrich23xx.discord.body.embed.base.Name;
import com.ialdrich23xx.discord.body.embed.base.Structure;

import java.util.Map;

public class Field extends Structure implements Name {

    private String value;
    private Boolean inline;

    public Field(String name, String value, Boolean inline) {
        this.setName(name);
        this.value = value;
        this.inline = inline;
    }

    public static Field make(String name, String value, Boolean inline) {
        return new Field(name, value, inline);
    }

    public static Field make(String name, String value) {
        return new Field(name, value, false);
    }

    public Field setValue(String value)
    {
        this.value = value;

        return this;
    }

    public String getValue()
    {
        return this.value;
    }

    public Field setInline(Boolean inLine)
    {
        this.inline = inLine;

        return this;
    }

    public Boolean getInline()
    {
        return this.inline;
    }

    @Override
    public Boolean build() {
        return !this.getValue().isEmpty() && !this.getName().isEmpty();
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = this.nameToArray();

        result.put("value", this.getName());
        result.put("inline", this.getInline());

        return result;
    }

    @Override
    public String toString() {
        return "Field(" + this.nameToString() + ",value=" + this.getValue() + ",inline=" + this.getInline().toString() + ")";
    }
}

package com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed;

import com.ialdrich23xx.libasynwebhook.nukkit.Loader;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.base.Structure;
import com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.components.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmbedManager extends Structure {

    private String title;
    private String description;
    private Integer color;

    private Author author = null;
    private List<Field> fields = new ArrayList<>();
    private Footer footer = null;
    private Thumbnail thumbnail = null;
    private Image image = null;
    private Timestamp timestamp = null;

    public EmbedManager(String title, String description, Integer color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public static EmbedManager make(String title, String description, Integer color) {
        return new EmbedManager(title, description, color);
    }

    public static EmbedManager make(String title, String description) {
        return new EmbedManager(title, description, EmbedColors.Default);
    }

    public EmbedManager setTitle(String title) {
        this.title = title;

        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public EmbedManager setDescription(String description) {
        this.description = description;

        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public EmbedManager setColor(Integer color) {
        this.color = color;

        return this;
    }

    public Integer getColor() {
        return this.color;
    }

    public EmbedManager setAuthor(Author author) {
        if (!author.build()) {
            Loader.getInstance().getLogger().error("Author is invalid: " + author);
        } else this.author = author;

        return this;
    }

    public EmbedManager removeAuthor() {
        this.author = null;

        return this;
    }

    public Author getAuthor() {
        return this.author;
    }

    public EmbedManager addField(Field field) {
        if (!field.build()) {
            Loader.getInstance().getLogger().error("Field is invalid: " + field);
        } else this.fields.add(field);

        return this;
    }

    public EmbedManager resetFields() {
        this.fields = new ArrayList<>();

        return this;
    }

    public List<Field> getFields()
    {
        return this.fields;
    }

    public EmbedManager setFooter(Footer footer) {
        if (!footer.build()) {
            Loader.getInstance().getLogger().error("Footer is invalid: " + footer);
        } else this.footer = footer;

        return this;
    }

    public EmbedManager removeFooter() {
        this.footer = null;

        return this;
    }

    public Footer getFooter() {
        return this.footer;
    }

    public EmbedManager setThumbnail(Thumbnail thumbnail) {
        if (!thumbnail.build()) {
            Loader.getInstance().getLogger().error("Thumbnail is invalid: " + thumbnail);
        } else this.thumbnail = thumbnail;

        return this;
    }

    public EmbedManager removeThumbnail() {
        this.thumbnail = null;

        return this;
    }

    public Thumbnail getThumbnail() {
        return this.thumbnail;
    }

    public EmbedManager setImage(Image image) {
        if (!image.build()) {
            Loader.getInstance().getLogger().error("Image is invalid: " + image);
        } else this.image = image;

        return this;
    }

    public EmbedManager removeImage() {
        this.image = null;

        return this;
    }

    public Image getImage() {
        return this.image;
    }

    public EmbedManager setTimestamp(Timestamp timestamp) {
        if (!timestamp.build()) {
            Loader.getInstance().getLogger().error("Image is invalid: " + timestamp);
        } else this.timestamp = timestamp;

        return this;
    }

    public EmbedManager removeTimestamp() {
        this.timestamp = null;

        return this;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    @Override
    public Boolean build() {
        return !this.getTitle().isEmpty() && !this.getDescription().isEmpty() || !this.getFields().isEmpty();
    }

    @Override
    public Map<String, Object> toArray() {
        Map<String, Object> result = new HashMap<>();

        result.put("title", this.getTitle());
        result.put("description", this.getDescription());
        result.put("color", this.getColor());

        if (this.getAuthor() != null) result.put("author", this.getAuthor().toArray());
        if (this.getFooter() != null) result.put("footer", this.getFooter().toArray());
        if (this.getThumbnail() != null) result.put("thumbnail", this.getThumbnail().toArray());
        if (this.getImage() != null) result.put("image", this.getImage().toArray());
        if (this.getTimestamp() != null) result.put("timestamp", this.getTimestamp().getFormat().format(this.getTimestamp().getDate()));

        List<Map<String, Object>> fieldList = new ArrayList<>();

        this.getFields().forEach(field -> {
            fieldList.add(field.toArray());
        });

        if (!fieldList.isEmpty()) {
            result.put("fields", fieldList);
        }

        return result;
    }

    @Override
    public String toString() {
        return null;
    }
}

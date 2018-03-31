package service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class PageTemplate extends ResourceSupport {

    private final String content;

    @JsonCreator
    public PageTemplate(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && obj.getClass().equals(this.getClass())) {
            return this.content.equals(((PageTemplate) obj).getContent());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.content.hashCode();
    }
}
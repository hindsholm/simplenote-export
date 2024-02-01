package dk.hindsholm.simplenote;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Note(String id, String content, Date creationDate, Date lastModified) {

    public String getTitle() {
        return content
            .split("\\R", 2)[0]                 // First line
            .replaceAll("^[#\\s]+", "");        // Ignore leading #'s and whitespace
    }

    public String getFileName() {
        return getTitle()
            .replaceAll("[^\\p{L}\\d ]", " ")   // Only allow letters, digits and spaces
            .trim()
            + ".md";
    }

}

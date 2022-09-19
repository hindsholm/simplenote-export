package dk.hindsholm.dendron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("squid:S6218")
public record Export(Note[] activeNotes, Note[] trashedNotes) {
}

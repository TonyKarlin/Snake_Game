package utils.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// class to represent the response from the hiscores endpoint, which contains an embedded list of GameDTOs
@JsonIgnoreProperties(ignoreUnknown = true)
public class HiscoresResponse {
    
    @JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }
}

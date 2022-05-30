package models.album;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "height",
    "url",
    "width"
})
public class Image {

    @JsonProperty("height")
    public Integer height;
    @JsonProperty("url")
    public String url;
    @JsonProperty("width")
    public Integer width;

}

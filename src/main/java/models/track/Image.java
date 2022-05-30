package models.track;

import javax.annotation.Generated;
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
@Generated("jsonschema2pojo")
public class Image {

    @JsonProperty("height")
    public Integer height;
    @JsonProperty("url")
    public String url;
    @JsonProperty("width")
    public Integer width;

}

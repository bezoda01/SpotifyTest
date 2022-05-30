
package models.album;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "text",
    "type"
})
@Generated("jsonschema2pojo")
public class Copyright {

    @JsonProperty("text")
    public String text;
    @JsonProperty("type")
    public String type;

}

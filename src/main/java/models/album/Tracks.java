
package models.album;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href",
    "items",
    "limit",
    "next",
    "offset",
    "previous",
    "total"
})
@Generated("jsonschema2pojo")
public class Tracks {

    @JsonProperty("href")
    public String href;
    @JsonProperty("items")
    public List<Item> items = null;
    @JsonProperty("limit")
    public Integer limit;
    @JsonProperty("next")
    public Object next;
    @JsonProperty("offset")
    public Integer offset;
    @JsonProperty("previous")
    public Object previous;
    @JsonProperty("total")
    public Integer total;

}

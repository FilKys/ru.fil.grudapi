package ru.api.modles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {

    @JsonProperty(value = "limit",defaultValue = "10")
    int limit;

    @JsonProperty(value = "offset",defaultValue = "0")
    int offset;

    @JsonProperty(value = "total_count",defaultValue = "0")
    long totalCount;


    //    public Pagination(int limit){
//        this.limit = limit;
//    }
//
//    public Pagination(int offset){
//        this.offset = offset;
//    }
//    public Pagination(int limit, int offset, long totalCount) {
//        this.limit = limit;
//        this.offset = offset;
//        this.totalCount = totalCount;
//    }
}

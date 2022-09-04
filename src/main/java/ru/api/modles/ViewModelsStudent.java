package ru.api.modles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ViewModelsStudent {

    @JsonProperty("students")
    List<Student> studentList;

    @JsonProperty("pagination")
    Pagination pagination;

}


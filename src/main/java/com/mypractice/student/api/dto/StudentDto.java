package com.mypractice.student.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @NotBlank(message = "FirstName is required!")
    private JsonNullable<String> firstName =  JsonNullable.undefined();
    private JsonNullable<String> lastName= JsonNullable.undefined();
    @JsonProperty("class")
    @NotBlank(message = "Class is required!")
    private JsonNullable<String> division= JsonNullable.undefined();
    private JsonNullable<String> nationality= JsonNullable.undefined();
}

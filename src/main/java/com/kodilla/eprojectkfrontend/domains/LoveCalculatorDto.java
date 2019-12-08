package com.kodilla.eprojectkfrontend.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoveCalculatorDto {
    @JsonProperty("fname")
    private String fname;
    @JsonProperty("sname")
    private String sname;
    @JsonProperty("percentage")
    private String percentage;
    @JsonProperty("result")
    private String result;

    @Override
    public String toString() {
        return "LoveCalculatorDto{" +
                "fname='" + fname + '\'' +
                ", sname='" + sname + '\'' +
                ", percentage='" + percentage + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

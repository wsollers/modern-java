package dev.wsollers.web.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import dev.wsollers.northwinds.domain.UsState;

import java.util.List;

@JacksonXmlRootElement(localName = "UsStates")
public record UsStatesResponse(
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "UsState")
        List<UsState> states
) {
    public static UsStatesResponse from(List<UsState> states) {
        return new UsStatesResponse(states);
    }
}
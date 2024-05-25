package com.drinkwater.apidrinkwater.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                                                                                        throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("pageNumber", page.getNumber());
        jsonGenerator.writeNumberField("pageSize", page.getSize());
        jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
        jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
        jsonGenerator.writeBooleanField("firstPage", page.isFirst());
        jsonGenerator.writeBooleanField("lastPage", page.isLast());
        jsonGenerator.writeFieldName("content");
        serializerProvider.defaultSerializeValue(page.getContent(), jsonGenerator);

        jsonGenerator.writeEndObject();
    }
}

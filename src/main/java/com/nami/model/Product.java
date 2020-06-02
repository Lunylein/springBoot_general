package com.nami.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;
public class Product {

    private final UUID id;

    @NotBlank
    @NotEmpty
    @NotNull
    private final String name;

    public Product(@JsonProperty("id") UUID id,
                   @JsonProperty(value = "name", required = true) @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

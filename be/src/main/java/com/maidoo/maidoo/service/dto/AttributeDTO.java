package com.maidoo.maidoo.service.dto;

import com.maidoo.maidoo.service.util.validate.Add;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeDTO {
    private Long id;
    @NotNull(groups = Add.class)
    private String name;
    private String value;
}

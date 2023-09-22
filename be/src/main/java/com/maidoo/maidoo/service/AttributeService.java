package com.maidoo.maidoo.service;

import com.maidoo.maidoo.config.exception.common.NotFoundException;
import com.maidoo.maidoo.domain.Attribute;
import com.maidoo.maidoo.repository.IAttributeRepository;
import com.maidoo.maidoo.service.dto.AttributeDTO;
import com.maidoo.maidoo.service.mapper.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttributeService {
    private final IAttributeRepository attributeRepository;

    public AttributeDTO saveAttribute(AttributeDTO attributeDTO) {
        Attribute attribute = MapperUtils.map(attributeDTO, Attribute.class);
        this.attributeRepository.save(attribute);
        return attributeDTO;
    }

    public void deleteAttribute(Long id) {
        Attribute attribute = this.attributeRepository.findById(id).orElseThrow(NotFoundException::new);
        this.attributeRepository.deleteById(attribute.getId());
    }

    public List<AttributeDTO> getAllAttribute(String name) {
        return MapperUtils.mapList(this.attributeRepository.getAllAttributeByNameContains(name), AttributeDTO.class);
    }

    public AttributeDTO getAttribute(Long id) {
        return MapperUtils.map(this.attributeRepository.findById(id).orElseThrow(NotFoundException::new),AttributeDTO.class);
    }

    public static class WishListService {
    }
}

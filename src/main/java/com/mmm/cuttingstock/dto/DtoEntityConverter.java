package com.mmm.cuttingstock.dto;


import com.mmm.cuttingstock.model.Purchase;
import com.mmm.cuttingstock.model.SingleCut;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DtoEntityConverter {

    private final ModelMapper mapper;

    public DtoEntityConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Purchase convertToOrder(OrderDto orderDto){
       return mapper.map(orderDto, Purchase.class);
    }

    public Set<SingleCut> convertAllToSingleCuts(List<SingleCutDto> singleCutDTOs){
        return singleCutDTOs.stream()
                .map(this::convertToSingleCut).collect(Collectors.toSet());
    }

    public SingleCut convertToSingleCut(SingleCutDto singleCutDto){
        return mapper.map(singleCutDto, SingleCut.class);
    }

    public List<SingleCutDto> convertAllToSingleCutDTOs(Set<SingleCut> singleCuts){
        return singleCuts.stream()
                .map(this::convertToSingleCutDto).collect(Collectors.toList());
    }

    public SingleCutDto convertToSingleCutDto(SingleCut singleCut){
        return mapper.map(singleCut, SingleCutDto.class);
    }
}

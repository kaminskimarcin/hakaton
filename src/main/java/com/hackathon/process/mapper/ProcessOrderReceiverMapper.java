package com.hackathon.process.mapper;

import com.hackathon.process.domain.dto.ProcessOrderReceiverItemDTO;
import com.hackathon.process.domain.entities.ProcessOrderReceiverItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProcessOrderReceiverMapper {

    public ProcessOrderReceiverItem mapProcessOrderReceiverItemDtoToEntity(ProcessOrderReceiverItemDTO processOrderReceiverItemDTO) {
        return new ProcessOrderReceiverItem (
                null,
                processOrderReceiverItemDTO.getId(),
                processOrderReceiverItemDTO.getDescription(),
                processOrderReceiverItemDTO.getQuantity(),
                processOrderReceiverItemDTO.getBatch(),
                processOrderReceiverItemDTO.getStatus()
        );
    }

    public ProcessOrderReceiverItemDTO mapProcessOrderReceiverItemEntityToDto(ProcessOrderReceiverItem processOrderReceiverItem) {
        return new ProcessOrderReceiverItemDTO (
                processOrderReceiverItem.getId(),
                processOrderReceiverItem.getDescription(),
                processOrderReceiverItem.getQuantity(),
                processOrderReceiverItem.getBatch(),
                processOrderReceiverItem.getStatus()
        );
    }
}

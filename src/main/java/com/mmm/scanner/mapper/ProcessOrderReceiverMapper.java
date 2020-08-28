package com.mmm.scanner.mapper;

import com.mmm.scanner.domain.dto.ProcessOrderReceiverItemDTO;
import com.mmm.scanner.domain.entities.ProcessOrderReceiverItem;
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

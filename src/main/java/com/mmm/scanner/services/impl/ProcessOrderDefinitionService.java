package com.mmm.scanner.services.impl;

import com.mmm.scanner.domain.dto.ProcessOrderReceiverDTO;
import com.mmm.scanner.repository.ProcessOrderDefinitionRepository;
import com.mmm.scanner.repository.ProcessOrderReceiverItemRepository;
import com.mmm.scanner.repository.ProcessOrderReceiverRepository;
import com.mmm.scanner.services.IProcessOrderDefinitionService;
import com.mmm.scanner.utils.SaveReportToFile;
import com.mmm.scanner.domain.dto.ProcessOrderReceiverItemDTO;
import com.mmm.scanner.domain.entities.ProcessOrderDefinition;
import com.mmm.scanner.domain.entities.ProcessOrderItem;
import com.mmm.scanner.domain.entities.ProcessOrderReceiver;
import com.mmm.scanner.mapper.ProcessOrderReceiverMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcessOrderDefinitionService implements IProcessOrderDefinitionService {
    private ProcessOrderDefinitionRepository processOrderDefinitionRepository;
    private ProcessOrderReceiverRepository processOrderReceiverRepository;
    private ProcessOrderReceiverItemRepository processOrderReceiverItemRepository;

    @Override
    public List<ProcessOrderItem> findProcessOrderItemsById(Long id) {
        Optional<ProcessOrderDefinition> processOrderDefinition = processOrderDefinitionRepository.findById(id);
        return processOrderDefinition.orElseGet(ProcessOrderDefinition::new).getItems();
    }

    public void updateProcessOrder(ProcessOrderReceiverDTO receiverDto) {
        ProcessOrderReceiver receiver = new ProcessOrderReceiver();
        receiver.setOrderId(receiverDto.getOrderId());
        for (ProcessOrderReceiverItemDTO item : receiverDto.getItems()) {
            receiver.getItems().add(ProcessOrderReceiverMapper.mapProcessOrderReceiverItemDtoToEntity(item));
        }
        receiver.setItems(receiver.getItems());
        processOrderReceiverItemRepository.saveAll(receiver.getItems());
        processOrderReceiverRepository.save(receiver);
    }

    public void generateReport(ProcessOrderReceiverDTO receiverDto) {
        SaveReportToFile.saveProcessOrderListToFile(receiverDto);
    }

    public List<ProcessOrderReceiver> getAllAvailableCheckedProcess() {
        return processOrderReceiverRepository.findAll();
    }
}

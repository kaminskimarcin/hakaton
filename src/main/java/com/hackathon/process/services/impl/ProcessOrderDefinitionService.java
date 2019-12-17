package com.hackathon.process.services.impl;

import com.hackathon.process.domain.dto.ProcessOrderReceiverDTO;
import com.hackathon.process.domain.dto.ProcessOrderReceiverItemDTO;
import com.hackathon.process.domain.entities.ProcessOrderDefinition;
import com.hackathon.process.domain.entities.ProcessOrderItem;
import com.hackathon.process.domain.entities.ProcessOrderReceiver;
import com.hackathon.process.mapper.ProcessOrderReceiverMapper;
import com.hackathon.process.repository.ProcessOrderDefinitionRepository;
import com.hackathon.process.repository.ProcessOrderReceiverItemRepository;
import com.hackathon.process.repository.ProcessOrderReceiverRepository;
import com.hackathon.process.services.IProcessOrderDefinitionService;
import com.hackathon.process.utils.SaveReportToFile;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;
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

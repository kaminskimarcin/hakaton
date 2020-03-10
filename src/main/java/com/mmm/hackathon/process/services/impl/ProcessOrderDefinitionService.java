package com.mmm.hackathon.process.services.impl;

import com.mmm.hackathon.process.domain.dto.ProcessOrderReceiverDTO;
import com.mmm.hackathon.process.repository.ProcessOrderDefinitionRepository;
import com.mmm.hackathon.process.repository.ProcessOrderReceiverItemRepository;
import com.mmm.hackathon.process.repository.ProcessOrderReceiverRepository;
import com.mmm.hackathon.process.services.IProcessOrderDefinitionService;
import com.mmm.hackathon.process.utils.SaveReportToFile;
import com.mmm.hackathon.process.domain.dto.ProcessOrderReceiverItemDTO;
import com.mmm.hackathon.process.domain.entities.ProcessOrderDefinition;
import com.mmm.hackathon.process.domain.entities.ProcessOrderItem;
import com.mmm.hackathon.process.domain.entities.ProcessOrderReceiver;
import com.mmm.hackathon.process.mapper.ProcessOrderReceiverMapper;
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

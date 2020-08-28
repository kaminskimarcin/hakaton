package com.mmm.scanner.utils;

import com.mmm.scanner.domain.dto.ProcessOrderReceiverDTO;
import com.mmm.scanner.domain.dto.ProcessOrderReceiverItemDTO;
import lombok.experimental.UtilityClass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

@UtilityClass
public class SaveReportToFile {

    public void saveProcessOrderListToFile(ProcessOrderReceiverDTO receiver) {
        LocalDateTime date = LocalDateTime.now();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Report-" + Long.toString(receiver.getOrderId()) + ".txt"))) {
            writer.write("Raport " + date.toString());
            writer.newLine();
            writer.write("Order type: ");
            writer.write(Long.toString(receiver.getOrderId()));
            writer.newLine();
            writer.write("Lp. Name  BatchSize   Quantity");
            writer.newLine();
            writer.write("---------------------------------------------------------------");
            writer.newLine();
            int i = 1;
            for (ProcessOrderReceiverItemDTO item : receiver.getItems()) {
                writer.write(i + ". " + item.getDescription() + " " + item.getBatch() + " " + item.getQuantity());
                writer.newLine();
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

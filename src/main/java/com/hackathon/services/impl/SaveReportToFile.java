package com.hackathon.services.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hackathon.entities.ProcessOrderReceiver;
import com.hackathon.entities.ProcessOrderReceiverItem;

@Service
public class SaveReportToFile {

	public boolean saveProcessOrderListToFile(ProcessOrderReceiver reciever) {
		LocalDateTime date = LocalDateTime.now();
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("Report-"+ date.toString() + ".txt"));){
			writer.write("Raport" + date.toString());
			writer.newLine();
			writer.write("Order type: ");
			writer.write(new Integer(reciever.getOrderId()).toString());
			writer.newLine();
			writer.write("---------------------------------------------------------------");
			writer.newLine();
			int i=1;
			for (ProcessOrderReceiverItem item : reciever.getItems()) {
				writer.write(i + ". " + item.getDescription() + " " + item.getBatch() + " " + item.getQuantity());
				writer.newLine();
				i++;
			}
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}

		return true;
	}
}

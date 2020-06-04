package com.mmm.cutting.stock.service;

import com.mmm.cutting.stock.csv.CsvReader;
import com.mmm.cutting.stock.model.Order;
import com.mmm.cutting.stock.model.SingleOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {
    public void calculate(MultipartFile file) throws IOException {
        Order order = CsvReader.readCsv(file);
        List<SingleOrder> singleOrders = order.getSingleOrders();
        List<Integer> integers = new ArrayList<>();

        for (SingleOrder singleOrder: singleOrders) {
            for (int i = 0; i < singleOrder.getOrderQuantity(); i++) {
                integers.add(Math.toIntExact(singleOrder.getWidth()));
            }
        }

        List<Integer> readyList = new ArrayList<>();

        Integer rollWidth = 1500;

        /*for (int i = 0; i < integers.size(); i++) {
            List<Integer> fittingInt = new ArrayList<>();

            int singleCut = integers.get(i);

            if (fittingInt.stream().mapToInt(Integer::valueOf).sum() == 1500) {
                integers.remove(i);
            } else if (singleCut < 1500) {

            } else {

            }

        }*/
        List<Integer> fittingInt = new ArrayList<>();

        permutation(integers, fittingInt, readyList);
        System.out.println(integers);

    }

    public static void permutation(List<Integer> integers, List<Integer> fittingList, List<Integer> readyList) {
        int sumOfFittingList = fittingList.stream().mapToInt(Integer::valueOf).sum();
        if (sumOfFittingList == 1500) {
            readyList.addAll(fittingList);
            integers.removeAll(fittingList);
        } else if (sumOfFittingList > 1500 || integers.size() == 0){
            fittingList.remove(fittingList.size() - 1);
        } else if (sumOfFittingList < 1500){
            for (int i = 0; i < integers.size(); i++) {
                List<Integer> temporaryList = new ArrayList<>();
                temporaryList.addAll(integers);
                fittingList.add(integers.get(i));
                temporaryList.remove(i);
                permutation(temporaryList, fittingList, readyList);
            }
        }


    }
}

package com.mmm.cuttingstock.controller;

import com.mmm.cuttingstock.dto.CheckDto;
import com.mmm.cuttingstock.service.SingleCutService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class SingleCutController {

    private SingleCutService singleCutService;

    @PatchMapping("/singleCut/{id}")
    public ResponseEntity<String> checkSingleCut(@RequestBody CheckDto checkDto, @PathVariable("id") int id) {
         var isCheckedProperly = singleCutService.checkedSingleCut(checkDto.getIsChecked(), id);

         var res = isCheckedProperly ? "Single Cut checked properly" :
                 "Single Cut did check properly";

         return ResponseEntity.ok(res);
    }
}

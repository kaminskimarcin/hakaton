package com.mmm.cuttingstock.dto;

import lombok.Data;

@Data
public class CalcOrderDto {

   private String jumbo_width;
   private String uniqueWidthString;
   private String widthOccurrencesString;

   public CalcOrderDto(String jumboWidth, String uniqueWidthString, String widthOccurrencesString) {
      this.jumbo_width = jumboWidth;
      this.uniqueWidthString = uniqueWidthString;
      this.widthOccurrencesString = widthOccurrencesString;
   }
}

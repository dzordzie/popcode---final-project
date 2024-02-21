package com.greenfox.avuspopcode.dtos;

import com.greenfox.avuspopcode.enums.RentStatus;
import lombok.Data;

import java.util.List;

@Data
public class RentPutDTO {
  List<Long> ids;
  RentStatus status;
}

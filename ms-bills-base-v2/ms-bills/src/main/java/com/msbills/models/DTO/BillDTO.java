package com.msbills.models.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BillDTO {

    private String idBill;

    private Date billingDate;

    private Double totalPrice;

    private UserDTO userDTO;

}

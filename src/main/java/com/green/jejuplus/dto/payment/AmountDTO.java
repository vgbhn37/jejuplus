package com.green.jejuplus.dto.payment;

import lombok.Data;

@Data
public class AmountDTO {
	private Integer total, tax_free, vat, point, discount;
}

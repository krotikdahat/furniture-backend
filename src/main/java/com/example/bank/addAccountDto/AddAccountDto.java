package com.example.bank.addAccountDto;

import lombok.Data;

@Data
public class AddAccountDto {
	private String accountHolderName;
	private String bankName;
	private Double balance;
	private Double initialAmount;
	private String address;
}

package com.sukute1712.ketfilmstore.payload.dto.user;

import com.sukute1712.ketfilmstore.payload.dto.transaction.TransactionDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserListDto {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String phoneNo;
    List<TransactionDto> transactionList;
}

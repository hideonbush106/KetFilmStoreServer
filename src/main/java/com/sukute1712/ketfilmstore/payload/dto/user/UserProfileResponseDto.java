package com.sukute1712.ketfilmstore.payload.dto.user;

import com.sukute1712.ketfilmstore.entity.Address;
import com.sukute1712.ketfilmstore.entity.Transaction;
import com.sukute1712.ketfilmstore.payload.dto.address.AddressResponseDto;
import com.sukute1712.ketfilmstore.payload.dto.transaction.TransactionDto;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
public class UserProfileResponseDto {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String phoneNo;
    List<AddressResponseDto> addressList;
    List<TransactionDto> transactionList;
}

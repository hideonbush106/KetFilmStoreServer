package com.sukute1712.ketfilmstore.service;

import com.sukute1712.ketfilmstore.entity.Address;
import com.sukute1712.ketfilmstore.entity.User;
import com.sukute1712.ketfilmstore.exception.AppException;
import com.sukute1712.ketfilmstore.exception.ResourceNotFoundException;
import com.sukute1712.ketfilmstore.jwt.JwtAuthenticationFilter;
import com.sukute1712.ketfilmstore.jwt.JwtTokenProvider;
import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.payload.dto.address.AddressResponseDto;
import com.sukute1712.ketfilmstore.repository.AddressRepository;
import com.sukute1712.ketfilmstore.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    public Message addAddress(AddressResponseDto addressResponseDto, HttpServletRequest request) {
        User user = userService.getAuthenticatedUser(request);
        Address address = modelMapper.map(addressResponseDto, Address.class);
        address.setCreatedBy(user);
        address.setUser(user);
        addressRepository.save(address);
        return new Message("Address added successfully!");
    }

    public AddressResponseDto updateAddress(UUID id, AddressResponseDto addressResponseDto, HttpServletRequest request) {
        User user = userService.getAuthenticatedUser(request);
        List<Address> addressList = addressRepository.findByUser(user);
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address"));
        if (!addressList.contains(address)) {
            throw new ResourceNotFoundException("Address");
        } else {
            address.setAddressNumber(addressResponseDto.getAddressNumber());
            address.setStreet(addressResponseDto.getStreet());
            address.setQuarter(addressResponseDto.getQuarter());
            address.setWard(addressResponseDto.getWard());
            address.setDistrict(addressResponseDto.getDistrict());
            address.setProvince(addressResponseDto.getProvince());
            address.setLastModifiedBy(user);
            addressRepository.save(address);
            return modelMapper.map(address, AddressResponseDto.class);
        }

    }

    public Message deleteAddress(UUID id, HttpServletRequest request) {
        User user = userService.getAuthenticatedUser(request);
        List<Address> addressList = addressRepository.findByUser(user);
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address"));
        if (!addressList.contains(address)) {
            throw new ResourceNotFoundException("Address");
        } else {
            addressRepository.deleteById(id);
            return new Message("Address deleted");
        }
    }

}

package ru.itis.services;

import ru.itis.dto.AddressForm;
import ru.itis.dto.UserDto;
import ru.itis.models.Address;
import ru.itis.repositories.AddressRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAddresses(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        return addressRepository.getAddressByAccountId(userDto.getId());
    }

    @Override
    public void addAddress(HttpSession session, AddressForm addressForm) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        System.out.println("wotofak");
        Address address = Address.builder()
                .accountId(userDto.getId())
                .city(addressForm.getCity())
                .country(addressForm.getCountry())
                .firstName(addressForm.getFirstName())
                .lastName(addressForm.getLastName())
                .phone(addressForm.getPhone())
                .postcode(addressForm.getPostcode())
                .street(addressForm.getStreet()).build();
        addressRepository.save(address);
    }


}

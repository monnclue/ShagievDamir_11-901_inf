package ru.itis.services;

import ru.itis.dto.AddressForm;
import ru.itis.models.Address;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AddressService {
    List<Address> getAddresses(HttpSession session);
    void addAddress(HttpSession session, AddressForm addressForm);
    Address getAddressOrSaveIfDoesntExist(HttpSession session, AddressForm addressForm);
}

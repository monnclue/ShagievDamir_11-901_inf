package ru.itis.repositories;

import ru.itis.dto.AddressForm;
import ru.itis.models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address>{
    List<Address> getAddressByAccountId(Long id);

    Optional<Address> getAddressByData(AddressForm addressForm);
}

package ru.itis.repositories;

import ru.itis.models.Address;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address>{
    List<Address> getAddressByAccountId(Long id);
}

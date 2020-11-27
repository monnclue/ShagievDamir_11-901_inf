package ru.itis.repositories;

import ru.itis.models.Promo;

import java.util.Optional;

public interface PromocodeRepository extends CrudRepository<Promo>{

    Optional<Promo> getPromoByCode(String code);
}

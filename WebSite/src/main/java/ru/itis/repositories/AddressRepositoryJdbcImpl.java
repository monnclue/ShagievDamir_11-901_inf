package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Address;
import ru.itis.models.Product;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class AddressRepositoryJdbcImpl implements AddressRepository{


    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_BY_ACCOUNT_ID
            = "select * from addresses where account_id = ?";
    //language=SQL
    private static final String SQL_INSERT
            = "insert into addresses (account_id, first_name, last_name," +
            " country, city, street_with_house_with_room," +
            " postal_code, phone_number) values (?,?,?,?,?,?,?,?)";

    public AddressRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Address> addressRowMapper = (row, rowNumber) -> Address.builder()
            .accountId(row.getLong("account_id"))
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .country(row.getString("country"))
            .city(row.getString("city"))
            .street(row.getString("street_with_house_with_room"))
            .postcode(row.getString("postal_code"))
            .phone(row.getString("phone_number"))
            .build();




    @Override
    public List<Address> getAddressByAccountId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_ACCOUNT_ID, addressRowMapper, id);
    }

    @Override
    public void save(Address entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getAccountId(),
                entity.getFirstName(), entity.getLastName(),
                entity.getCountry(), entity.getCity(),
                entity.getStreet(), entity.getPostcode(),
                entity.getPhone());
    }

    @Override
    public void update(Address entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Address> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Address> findAll() {
        return null;
    }
}

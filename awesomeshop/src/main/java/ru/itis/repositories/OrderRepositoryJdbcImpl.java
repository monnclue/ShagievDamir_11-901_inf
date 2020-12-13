package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Order;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryJdbcImpl implements OrderRepository{
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "insert into orders " +
            "(date, price, account_id, address_id, if_order_was_shipped, " +
            "if_order_was_payed, shipping_method)" +
            "VALUES (current_date,?,?,?,?,false,?)";

    //language=SQL
    private static final String SQL_DELETE_NOT_PAYED =
            "DELETE FROM orders WHERE account_id = ? and if_order_was_payed = false";
    //language=SQL
    private static final String SQL_SELECT_BY_ACCOUNT_ID_NOT_PAYED =
            "select * from orders where account_id = ? and if_order_was_payed = false";
    //language=SQL
    private static final String SQL_SELECT_BY_ACCOUNT_ID_PAYED =
            "select * from orders where account_id = ? and if_order_was_payed = true";

    public OrderRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Order> orderRowMapper = (row, rowNumber) -> Order.builder()
            .price(row.getInt("price"))
            .accountId(row.getLong("account_id"))
            .addressId(row.getLong("address_id"))
            .isOrderShipped(row.getBoolean("if_order_was_shipped"))
            .shipMethod(row.getString("shipping_method")).build();


    @Override
    public void save(Order entity) {
        System.out.println("jsjfsjfj");
        System.out.println(entity.getPrice());
        System.out.println(entity.getAccountId());
        System.out.println(entity.getAddressId());
        System.out.println(entity.isOrderShipped());
        System.out.println(entity.getShipMethod());
        jdbcTemplate.update(SQL_INSERT, entity.getPrice(), entity.getAccountId(),
                entity.getAddressId(), entity.isOrderShipped(),
                entity.getShipMethod());
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_NOT_PAYED, id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findByAccountIdNotPayed(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(
                    SQL_SELECT_BY_ACCOUNT_ID_NOT_PAYED, orderRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findByAccountIdPayed(Long id) {
        return jdbcTemplate.query(
                SQL_SELECT_BY_ACCOUNT_ID_PAYED, orderRowMapper, id);
    }


}

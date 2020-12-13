package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Promo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PromocodeRepositoryJdbcImpl implements PromocodeRepository{

    private JdbcTemplate jdbcTemplate;

    public PromocodeRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=sql
    private static final String SQL_SELECT_BY_CODE = "select * from promocodes where code ilike ?";
    //language=sql
    private static final String SQL_INSERT = "insert into promocodes (code) values (?)";
    //language=sql
    private static final String SQL_SELECT_ALL_CODES = "select code from promocodes";

    private RowMapper<Promo> promoRowMapper = (row, rowNumber) ->
            Promo.builder().code(row.getString("code"))
                    .price(row.getInt("price")).build();

    @Override
    public void save(Promo entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getCode());
    }

    @Override
    public void update(Promo entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Promo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Promo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CODES, promoRowMapper);
    }

    @Override
    public Optional<Promo> getPromoByCode(String code) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_CODE, promoRowMapper, code));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

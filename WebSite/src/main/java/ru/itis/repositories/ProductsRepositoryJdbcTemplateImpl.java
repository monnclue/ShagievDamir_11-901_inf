package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Product;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcTemplateImpl implements ProductsRepository{

    //language=SQL
    private static final String SQL_SELECT_BY_TYPE = "select * from products where type = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_BY_NAME = "select * from products where funcfunc(name) like '%' || funcFunc(?) || '%' limit 10";
    //language=SQL
    private static final String SQL_INSERT = "insert into products(name, type, imageURL, description, price) " +
            "values (?,?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_DELETE_BY_ID = "DELETE FROM products WHERE id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from products";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from products where id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_ORDERS =
            "with w2_cte(product_id) as " +
                    "(with w_cte (product_id, count) as " +
                        "(select product_id, count(*) " +
                        "from orders group by product_id) " +
                    "select product_id " +
                    "from w_cte order by count desc limit ?) " +
            "select id, name, type, imageURL, description, price " +
            "from w2_cte join products on w2_cte.product_id = products.id";

    private JdbcTemplate jdbcTemplate;

    public ProductsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Product> productRowMapper = (row, rowNumber) -> Product.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .type(row.getString("type"))
            .imageURL(row.getString("imageURL"))
            .description(row.getString("description"))
            .price(row.getInt("price")).build();


    @Override
    public List<Product> findByType(String type) {
        return jdbcTemplate.query(SQL_SELECT_BY_TYPE, productRowMapper, type);
    }


    @Override
    public List<Product> findByName(String name) {
            return jdbcTemplate.query(
                    SQL_SELECT_ALL_PRODUCTS_BY_NAME, productRowMapper,  name );
    }

    @Override
    public List<Product> selectByTopOrders(int topCount) {
        return jdbcTemplate.query(SQL_SELECT_BY_ORDERS, productRowMapper, topCount);
    }


    @Override
    public void save(Product entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getName(),
                entity.getType(),
                entity.getImageURL(),
                entity.getDescription(),
                entity.getPrice());
    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, productRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, productRowMapper);
    }
}

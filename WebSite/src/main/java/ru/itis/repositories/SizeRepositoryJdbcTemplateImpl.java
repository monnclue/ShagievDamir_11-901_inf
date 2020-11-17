package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dto.ProductForCartForm;
import ru.itis.models.ProductForCart;
import ru.itis.models.ProductSize;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class SizeRepositoryJdbcTemplateImpl implements SizeRepository {

    //language=SQL
    private static final String SQL_INSERT =
            "insert into product_size (product_id, count, size) values (?,?,?)";

    //language=SQL
    private static final String SQL_UPDATE_COUNT_BY_PROD_ID =
            "update product_size set count = count + ? where product_id = ? and size = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_PROD_ID_AND_SIZE =
            "select * from product_size where product_id = ? and size = ?";
    //language=SQL
    private static final String SQL_GET_PRODUCT =
            "insert into cart_relationship(account_id, product_size_id) " +
            "values (?,?);" +
            "update product_size set count = count - 1 where id = ?;";

    //language=SQL
    private static final String SQL_GIVE_PRODUCT_BACK =
            "update product_size set count = count + 1 where id = ?;" +
            "delete from cart_relationship where id = ?;";

    //language=SQL
    private static final String SQL_SELECT_SIZES_FOR_PROD = "select * from product_size where product_id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ACCOUNT_ID =
            "with w_cr(id, account_id, product_size_id, size, product_id) as (" +
                    "    select cart_relationship.id, account_id, product_size_id, size, product_id" +
                    "    from cart_relationship" +
                    "    join product_size on product_size_id = product_size.id" +
                    "    where account_id = ?)" +
                    "select w_cr.id, account_id, product_size_id, size," +
                    "       products.name, products.imageURL, products.price from w_cr " +
                    "join products on product_id = products.id;";



    private JdbcTemplate jdbcTemplate;

    public SizeRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<ProductForCart> productRowMapper = (row, rowNumber) -> ProductForCart.builder()
            .rel_id(row.getLong("id"))
            .account_id(row.getLong("account_id"))
            .productSize_id(row.getLong("product_size_id"))
            .size(row.getString("size"))
            .name(row.getString("name"))
            .imageURL(row.getString("imageurl"))
            .price(row.getInt("price"))
            .build();

    private RowMapper<ProductSize> productSizeRowMapper = (row, rowNumber) -> ProductSize.builder()
            .id(row.getLong("id"))
            .productId(row.getLong("product_id"))
            .count(row.getInt("count"))
            .size(row.getString("size"))
            .build();

    public void pickProduct(ProductForCartForm productForCartForm) {
        System.out.println(productForCartForm.getProductId());
        ProductSize productSize = jdbcTemplate.queryForObject(
                SQL_SELECT_BY_PROD_ID_AND_SIZE, productSizeRowMapper,
                productForCartForm.getProductId(),
                productForCartForm.getProductSize());

        ProductForCart entity = ProductForCart.builder()
                .account_id(productForCartForm.getAccountId())
                .productSize_id(productSize.getId()).build();

        jdbcTemplate.update(SQL_GET_PRODUCT, entity.getAccount_id(),
                entity.getProductSize_id(), entity.getProductSize_id());

    }

    @Override
    public List<ProductSize> getProductsSizes(Long id) {
        List<ProductSize> products = jdbcTemplate.query(SQL_SELECT_SIZES_FOR_PROD, productSizeRowMapper, id);
        return products;
    }

    public void throwFromCart(ProductForCart entity) {
        jdbcTemplate.update(SQL_GIVE_PRODUCT_BACK, entity.getProductSize_id(), entity.getRel_id());
    }

    @Override
    public List<ProductForCart> findByAccountId(Long id) {
        List<ProductForCart> productForCarts =
                jdbcTemplate.query(SQL_SELECT_BY_ACCOUNT_ID,
                        productRowMapper, id);
        return productForCarts;
    }


    @Override
    public void save(ProductSize entity) {
        jdbcTemplate.update(SQL_INSERT,
                entity.getProductId(), entity.getCount(), entity.getSize());
    }

    @Override
    public void update(ProductSize entity) {
        jdbcTemplate.update(SQL_UPDATE_COUNT_BY_PROD_ID,
                entity.getCount(), entity.getProductId(), entity.getSize());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }
}

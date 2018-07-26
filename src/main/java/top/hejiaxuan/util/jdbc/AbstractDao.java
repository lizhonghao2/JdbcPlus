package top.hejiaxuan.util.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import top.hejiaxuan.util.maker.Function;
import top.hejiaxuan.util.maker.condition.Where;
import top.hejiaxuan.util.maker.delete.Delete;
import top.hejiaxuan.util.maker.insert.Insert;
import top.hejiaxuan.util.maker.query.DefaultQuery;
import top.hejiaxuan.util.maker.query.Query;
import top.hejiaxuan.util.maker.update.Update;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * dao 的 基类
 */
public abstract class AbstractDao {

    /**
     * 数据源
     */
    protected DataSource dataSource;

    protected JdbcTemplate jdbcTemplate;

    protected Log logger = LogFactory.getLog(AbstractDao.class);

    /**
     * 根据条件查询
     *
     * @param query
     * @return
     */
    final public List selectBy(final Query query) {
        EntityTableRowMapper mapper = EntityMapperFactory.getMapper(query.getEntity());
        String sql = query.toSql();
        Object[] sqlValues = query.getSqlValues();
        if (logger.isDebugEnabled()) {
            logger.debug(sql);
            logger.debug(Arrays.toString(sqlValues));
        }
        return jdbcTemplate.query(sql, sqlValues, mapper);

    }

    /**
     * 根据条件删除数据
     *
     * @param delete
     * @return
     */
    final public Integer deleteBy(final Delete delete) {
        String sql = delete.toSql();
        Object[] sqlValues = delete.getSqlValues();
        if (logger.isDebugEnabled()) {
            logger.debug(sql);
            logger.debug(Arrays.toString(sqlValues));
        }
        return jdbcTemplate.update(sql, sqlValues);
    }

    /**
     * 添加一条数据
     *
     * @param insert
     * @return
     */
    final public Integer insertBy(final Insert insert) {
        String sql = insert.toSql();
        Object[] sqlValues = insert.getSqlValues();
        if (logger.isDebugEnabled()) {
            logger.debug(sql);
            logger.debug(Arrays.toString(sqlValues));
        }
        return jdbcTemplate.update(sql, sqlValues);
    }

    /**
     * 根据条件更新
     *
     * @param update
     * @return
     */
    final public Integer updateBy(final Update update) {
        String sql = update.toSql();
        Object[] sqlValues = update.getSqlValues();
        if (logger.isDebugEnabled()) {
            logger.debug(sql);
            logger.debug(Arrays.toString(sqlValues));
        }
        return jdbcTemplate.update(sql, sqlValues);
    }

    /**
     * 执行一条函数
     *
     * @param clz
     * @param where
     * @param function
     * @param <T>
     * @return
     */
    final public <T> List<T> function(final Class clz, final Where where, final Function<T> function) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.addSelection(false, function.getSql());
        query.newWhere(where);
        String sql = query.toSql();
        Object[] sqlValues = query.getSqlValues();
        if (logger.isDebugEnabled()) {
            logger.debug(sql);
            logger.debug(Arrays.toString(sqlValues));
        }
        return jdbcTemplate.query(sql, new FunctionRowMapper(function), sqlValues);
    }

    /* Getter and Setter */

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}


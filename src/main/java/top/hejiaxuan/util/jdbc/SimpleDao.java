package top.hejiaxuan.util.jdbc;

import top.hejiaxuan.util.jdbc.maker.condition.Where;
import top.hejiaxuan.util.jdbc.maker.delete.DefaultDelete;
import top.hejiaxuan.util.jdbc.maker.delete.Delete;
import top.hejiaxuan.util.jdbc.maker.insert.DefaultInsert;
import top.hejiaxuan.util.jdbc.maker.insert.Insert;
import top.hejiaxuan.util.jdbc.maker.query.DefaultQuery;
import top.hejiaxuan.util.jdbc.maker.query.Query;
import top.hejiaxuan.util.jdbc.maker.update.DefaultUpdate;
import top.hejiaxuan.util.jdbc.maker.update.Update;
import top.hejiaxuan.util.jdbc.util.EntityUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 简化对象的数据操作
 */
public class SimpleDao extends AbstractDao {

    /**
     * 查询所有
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> List<T> select(final Class<T> clz) {
        Query query = new DefaultQuery();
        query.target(clz);
        return selectBy(query);
    }

    /**
     * 根据id查找
     *
     * @param clz
     * @param id
     * @param <T>
     * @return
     */
    public <T> T selectById(final Class<T> clz, final Object id) {
        EntityTableRowMapper mapper = EntityMapperFactory.getMapper(clz);
        return selectOneBy(clz, mapper.getIdName(), id);
    }

    /**
     * 根据某一字段查找数据
     *
     * @param clz
     * @param columnName
     * @param columnValue
     * @param <T>
     * @return
     */
    public <T> List<T> selectBy(final Class<T> clz, final String columnName, final Object columnValue) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.where(query.equal(columnName, columnValue));
        return selectBy(query);
    }

    /**
     * 根据某字段查找数据
     *
     * @param clz
     * @param columnName1
     * @param columnValue1
     * @param columnName2
     * @param columnValue2
     * @param <T>
     * @return
     */
    public <T> List<T> selectBy(final Class<T> clz, final String columnName1, final Object columnValue1, final String columnName2, final Object columnValue2) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.where(
                query.equal(columnName1, columnValue1),
                query.equal(columnName2, columnValue2)
        );
        return selectBy(query);
    }

    /**
     * 根据某一字段查找一条数据
     *
     * @param clz
     * @param columnName
     * @param columnValue
     * @param <T>
     * @return
     */
    public <T> T selectOneBy(final Class<T> clz, final String columnName, final Object columnValue) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.where(query.equal(columnName, columnValue));
        return selectOneBy(query);
    }

    /**
     * 根据字段查找一条数据
     *
     * @param clz
     * @param columnName1
     * @param columnValue1
     * @param columnName2
     * @param columnValue2
     * @param <T>
     * @return
     */
    public <T> T selectOneBy(final Class<T> clz, final String columnName1, final Object columnValue1, final String columnName2, final Object columnValue2) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.where(
                query.equal(columnName1, columnValue1),
                query.equal(columnName2, columnValue2)
        );
        return selectOneBy(query);
    }

    /**
     * 根据条件查询
     *
     * @param query
     * @param <T>
     * @return
     */
    public <T> T selectOneBy(final Query query) {
        List<T> list = selectBy(query);
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据条件查询
     *
     * @param where
     * @param <T>
     * @return
     */
    public <T> List<T> selectByWhere(final Where where) {
        Query query = new DefaultQuery();
        query.target(where.getEntity());
        query.newWhere(where);
        return selectBy(query);
    }

    /**
     * 根据id删除数据
     *
     * @param clz
     * @param id
     * @return
     */
    public Integer deleteById(final Class clz, final Object id) {
        EntityTableRowMapper mapper = EntityMapperFactory.getMapper(clz);
        return deleteBy(clz, mapper.getIdName(), id);
    }

    /**
     * 根据一个字段删除数据
     *
     * @param clz
     * @param columnName
     * @param columnValue
     * @return
     */
    public Integer deleteBy(final Class clz, final String columnName, final Object columnValue) {
        Delete delete = new DefaultDelete();
        delete.target(clz);
        delete.where(delete.equal(columnName, columnValue));
        return deleteBy(delete);
    }

    /**
     * 根据字段删除数据
     *
     * @param clz
     * @param columnName1
     * @param columnValue1
     * @param columnName2
     * @param columnValue2
     * @return
     */
    public Integer deleteBy(final Class clz, final String columnName1, final Object columnValue1, final String columnName2, final Object columnValue2) {
        Delete delete = new DefaultDelete();
        delete.target(clz);
        delete.where(
                delete.equal(columnName1, columnValue1),
                delete.equal(columnName2, columnValue2)
        );
        return deleteBy(delete);
    }

    /**
     * 添加一条数据
     *
     * @param entity
     * @return
     */
    public Integer insert(final Object entity) {
        Insert insert = new DefaultInsert();
        insert.target(entity.getClass());
        insert.insert(entity);
        return insertBy(insert);
    }

    /**
     * 根据id更新数据
     *
     * @param entity
     * @return
     */
    public Integer updateById(final Object entity) {
        return updateById(entity, false);
    }

    /**
     * 根据id 更新数据
     *
     * @param entity
     * @param selective 是否忽略 null
     * @return
     */
    public Integer updateById(final Object entity, final boolean selective) {
        Class clz = entity.getClass();
        EntityTableRowMapper mapper = EntityMapperFactory.getMapper(clz);
        Field field = EntityUtils.idField(clz);
        Object id = EntityUtils.getValue(entity, field);
        Update update = new DefaultUpdate();
        update.target(clz);
        update.set(entity, selective);
        update.where(update.equal(mapper.getIdName(), id));
        return updateBy(update);
    }

    public SimpleDao() {
    }

}

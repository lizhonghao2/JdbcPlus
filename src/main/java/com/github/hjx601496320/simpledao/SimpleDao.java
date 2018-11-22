package com.github.hjx601496320.simpledao;

import org.springframework.util.Assert;
import com.github.hjx601496320.simpledao.jdbc.EntityMapperFactory;
import com.github.hjx601496320.simpledao.jdbc.EntityTableRowMapper;
import com.github.hjx601496320.simpledao.jdbc.util.EntityUtils;
import com.github.hjx601496320.simpledao.maker.Wheres;
import com.github.hjx601496320.simpledao.maker.delete.DefaultDelete;
import com.github.hjx601496320.simpledao.maker.delete.Delete;
import com.github.hjx601496320.simpledao.maker.insert.DefaultInsert;
import com.github.hjx601496320.simpledao.maker.insert.Insert;
import com.github.hjx601496320.simpledao.maker.query.DefaultQuery;
import com.github.hjx601496320.simpledao.maker.query.Query;
import com.github.hjx601496320.simpledao.maker.update.DefaultUpdate;
import com.github.hjx601496320.simpledao.maker.update.Update;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 简化对象的数据操作
 *
 * @author hjx
 */
public class SimpleDao extends AbstractDao {

    /**
     * 查询所有
     *
     * @param clz
     * @param <T>
     * @return
     */
    final public <T> List<T> select(final Class<T> clz) {
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
    final public <T> T selectById(final Class<T> clz, final Object id) {
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
    final public <T> List<T> selectBy(
            final Class<T> clz,
            final String columnName, final Object columnValue
    ) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.where(Wheres.equal(columnName, columnValue));
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
    final public <T> List<T> selectBy(
            final Class<T> clz,
            final String columnName1, final Object columnValue1,
            final String columnName2, final Object columnValue2
    ) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.where(
                Wheres.equal(columnName1, columnValue1),
                Wheres.equal(columnName2, columnValue2)
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
    final public <T> T selectOneBy(
            final Class<T> clz,
            final String columnName, final Object columnValue
    ) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.where(Wheres.equal(columnName, columnValue));
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
    final public <T> T selectOneBy(
            final Class<T> clz,
            final String columnName1, final Object columnValue1,
            final String columnName2, final Object columnValue2
    ) {
        Query query = new DefaultQuery();
        query.target(clz);
        query.where(
                Wheres.equal(columnName1, columnValue1),
                Wheres.equal(columnName2, columnValue2)
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
    final public <T> T selectOneBy(final Query query) {
        List<T> list = selectBy(query);
        Assert.isTrue(list.size() <= 1, "查询结果数量大于1条!");
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据id删除数据
     *
     * @param clz
     * @param id
     * @return
     */
    final public Integer deleteById(final Class clz, final Object id) {
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
    final public Integer deleteBy(final Class clz, final String columnName, final Object columnValue) {
        Delete delete = new DefaultDelete();
        delete.target(clz);
        delete.where(Wheres.equal(columnName, columnValue));
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
    final public Integer deleteBy(
            final Class clz,
            final String columnName1, final Object columnValue1,
            final String columnName2, final Object columnValue2
    ) {
        Delete delete = new DefaultDelete();
        delete.target(clz);
        delete.where(
                Wheres.equal(columnName1, columnValue1),
                Wheres.equal(columnName2, columnValue2)
        );
        return deleteBy(delete);
    }

    /**
     * 添加一条数据
     *
     * @param entity
     * @return
     */
    final public Integer insert(final Object entity) {
        Insert insert = new DefaultInsert();
        insert.target(entity.getClass());
        insert.insert(entity);
        return insertBy(insert);
    }

    /**
     * 批量插入
     *
     * @param clz
     * @param entities
     * @return
     */
    final public Integer insertBatch(Class clz, final List entities) {
        Insert insert = new DefaultInsert();
        insert.target(clz);
        for (int i = 0; i < entities.size(); i++) {
            Object entity = entities.get(i);
            insert.insert(entity);
        }
        return insertBy(insert);
    }

    /**
     * 根据id更新数据
     *
     * @param entity
     * @return
     */
    final public Integer updateById(final Object entity) {
        return updateById(entity, false);
    }

    /**
     * 根据id 更新数据
     * <p>
     *
     * @param entity
     * @param ignoreNull 是否忽略 null
     * @return
     */
    final public Integer updateById(final Object entity, final boolean ignoreNull) {
        Class clz = entity.getClass();
        EntityTableRowMapper mapper = EntityMapperFactory.getMapper(clz);
        Field field = EntityUtils.idField(clz);
        Object id = EntityUtils.getValue(entity, field);
        Update update = new DefaultUpdate();
        update.target(clz);
        update.set(entity, ignoreNull);
        update.where(Wheres.equal(mapper.getIdName(), id));
        return updateBy(update);
    }

    public SimpleDao() {
    }

}

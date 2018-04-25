package top.hejiaxuan.util.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.hejiaxuan.util.jdbc.maker.create.ColumnTypeByField;
import top.hejiaxuan.util.jdbc.maker.create.Create;
import top.hejiaxuan.util.jdbc.maker.create.DefaultCreate;
import top.hejiaxuan.util.jdbc.maker.drop.DefaultDrop;
import top.hejiaxuan.util.jdbc.maker.drop.Drop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableCreator {

    protected Log logger = LogFactory.getLog(TableCreator.class);

    /**
     * 是否重新建造表
     */
    private boolean reCreate;
    /**
     * 要创建table 的 class
     */
    private List<Class> tables;

    /**
     * 当前数据库中存在的table
     */
    private List<String> hadTables;

    /**
     * 根据数据类型获取数据库中的字段类型
     */
    private ColumnTypeByField columnTypeByField;

    /**
     * sql执行器
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建数据库中的table
     */
    public void init() {
        showTable();
        dropTable();
        createTable();
    }

    /**
     * 查看当前数据库中存在的table
     */
    private void showTable() {
        hadTables = new ArrayList<>();
        String showTableSql = "show tables;";
        logger.debug(showTableSql);
        List<Map<String, Object>> maps = jdbcTemplate.query(showTableSql, new ColumnMapRowMapper());
        for (Map<String, Object> map : maps) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String tableName = entry.getValue().toString();
                hadTables.add(tableName);
            }
        }
    }

    /**
     * 创建数据库表
     */
    private void createTable() {
        Create create = new DefaultCreate();
        create.setColumnTypeByField(columnTypeByField);
        for (Class table : tables) {
            EntityTableRowMapper mapper = EntityMapperFactory.getMapper(table);
            String tableName = mapper.getTableName();
            if (hadTables.indexOf(tableName) < 0) {
                create.target(table);
                String sql = create.toSql();
                logger.debug(sql);
                jdbcTemplate.execute(sql);
            }
        }
    }

    /**
     * 删除表
     */
    private void dropTable() {
        if (!reCreate) {
            return;
        }
        for (Class table : tables) {
            EntityTableRowMapper entityTableRowMapper = EntityMapperFactory.getMapper(table);
            String tableName = entityTableRowMapper.getTableName();
            if (hadTables.indexOf(tableName) < 0) {
                continue;
            }
            Drop drop = new DefaultDrop();
            drop.target(table);
            String sql = drop.toSql();
            logger.debug(sql);
            jdbcTemplate.execute(sql);
            hadTables.remove(tableName);
        }
    }

    public boolean isReCreate() {
        return reCreate;
    }

    public void setReCreate(boolean reCreate) {
        this.reCreate = reCreate;
    }

    public List<Class> getTables() {
        return tables;
    }

    public void setTables(List<Class> tables) {
        this.tables = tables;
    }

    public ColumnTypeByField getColumnTypeByField() {
        return columnTypeByField;
    }

    public void setColumnTypeByField(ColumnTypeByField columnTypeByField) {
        this.columnTypeByField = columnTypeByField;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

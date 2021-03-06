package com.fyz.orm.jdbc.dao;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fyz.orm.jdbc.annotatino.Column;
import com.fyz.orm.jdbc.annotatino.Ignore;
import com.fyz.orm.jdbc.annotatino.PK;
import com.fyz.orm.jdbc.annotatino.Table;
import com.fyz.orm.jdbc.constant.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.fyz.orm.jdbc.constant.Const.*;

/**
 * @program: spring-boot-demo
 * @param <P> Primary Key Type
 * @param <T> Entity Type
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/06/03 22:03
 */
@Slf4j
public class BaseMapper<T, P> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    private final Class<T> clazz;

    @SuppressWarnings(value = "unchecked")
    public BaseMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.clazz = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @Description: 新增
     * @param t
     * @param ignoreNull
     * @return: int
     * @Creator: fyz
     * @Date: 2020/6/3 23:19
     */
    public int insert(T t, boolean ignoreNull) {
        String table = getTableName();
        List<Field> filterField = getFields(t, ignoreNull);
        List<String> columnList = getColumns(filterField);

        String columns = StrUtil.join(SEPARATOR_COMMA, columnList);
        // 占位符
        String placeholder = StrUtil.repeatAndJoin("?", columnList.size(), SEPARATOR_COMMA);
        // 值集合
        Object[] values = filterField.stream().map(field -> ReflectUtil.getFieldValue(t, field)).toArray();
        // sql
        String sql = StrUtil.format("INSERT INTO {table} ({columns}) VALUES ({placeholder})",
                Dict.create().set("table", table).set("columns", columns).set("placeholder", placeholder));

        log.debug("【执行SQL】SQL：{}", sql);
        log.debug("【执行SQL】参数：{}", JSONUtil.toJsonStr(values));
        return jdbcTemplate.update(sql, values);
    }

    /**
     * @Description: 删除指定数据
     * @param id
     * @return: int
     * @Creator: fyz
     * @Date: 2020/6/4 0:15
     */
    public int delete(Long id) {
        String table = getTableName();
        String sql = StrUtil.format("DELETE FROM {table} WHERE id = ? ",
                Dict.create().set("table", table));

        log.debug("【执行SQL】SQL：{}", sql);
        log.debug("【执行SQL】参数：{}", JSONUtil.toJsonStr(id));
        return jdbcTemplate.update(sql, id);
    }

    /**
     * @Description: 更新
     * @param t
     * @param pk
     * @param ignoreNull
     * @return: int
     * @Creator: fyz
     * @Date: 2020/6/4 0:52
     */
    public int updateById(T t, P pk, Boolean ignoreNull) {
        String table = getTableName();
        List<Field> filterField = getFields(t, ignoreNull);
        List<String> columnList = getColumns(filterField);
        // 更新字段
        List<String> setColumns = columnList.stream().map(col -> col + " = ? ").collect(Collectors.toList());
        String set = StrUtil.join(SEPARATOR_COMMA, setColumns);
        // 值
        List<Object> valueList = filterField.stream().map(field -> ReflectUtil.getFieldValue(t, field)).collect(Collectors.toList());
        valueList.add(pk);
        Object[] values = valueList.toArray();
        // sql
        String sql = StrUtil.format("UPDATE {table} SET {set} WHERE id = ?",
                Dict.create().set("table", table).set("set", set));
        log.debug("【执行SQL】SQL：{}", sql);
        log.debug("【执行SQL】参数：{}", JSONUtil.toJsonStr(values));
        return jdbcTemplate.update(sql, values);
    }

    /**
     * @Description: 根据id查询
     * @param id
     * @return: T
     * @Creator: fyz
     * @Date: 2020/6/4 0:33
     */
    public T findOneById(P id) {
        String table = getTableName();
        String sql = StrUtil.format("SELECT * from {table} WHERE id = ?", Dict.create().set("table", table));
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<>(clazz);
        log.debug("【执行SQL】SQL：{}", sql);
        log.debug("【执行SQL】参数：{}", JSONUtil.toJsonStr(id));
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }

    public List<T> findByExample(T t) {
        String table = getTableName();
        List<Field> fields = getFields(t, true);
        List<String> columns = getColumns(fields);
        // 查询条件
        List<String> whereColumns = columns.stream().map(column -> " and " + column + " = ? ").collect(Collectors.toList());
        String where = StrUtil.join(" ", whereColumns);
        // 值
        Object[] values = fields.stream().map(field -> ReflectUtil.getFieldValue(t, field)).toArray();
        // sql
        String sql = StrUtil.format("SELECT * FROM {table} WHERE 1 = 1 {where}",
                Dict.create().set("table", table).set("where", StrUtil.isBlank(where) ? "" : where));
        log.debug("【执行SQL】SQL：{}", sql);
        log.debug("【执行SQL】参数：{}", JSONUtil.toJsonStr(values));
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, values);
        return result.stream().map(item -> BeanUtil.mapToBean(item, clazz, CopyOptions.create().setIgnoreError(true))).collect(Collectors.toList());
    }

    /**
     * @Description: 获取实体类关联表名
     * @param
     * @return: java.lang.String
     *
     * @Creator: fyz
     * @Date: 2020/6/3 22:10
     *
     */
    private String getTableName(T t) {
        Class<?> clz = Objects.isNull(t) ? clazz : t.getClass();
        Table annotation = clz.getAnnotation(Table.class);
        return Optional.ofNullable(annotation).map(table ->
                StrUtil.format("`{}`", table.name())
        ).orElse(
                StrUtil.format("`{}`", clz.getName().toLowerCase())
        );
    }

    private String getTableName() {
        return this.getTableName(null);
    }

    /**
     * @Description: 获取实体类字段
     * @param t  
     * @param ignoreNull
     * @return: java.util.List<java.lang.reflect.Field>
     *
     * @Creator: fyz
     * @Date: 2020/6/3 22:21
     *
     */
    private List<Field> getFields(T t, boolean ignoreNull) {
        // 反射获取所有字段
        Field[] fields = ReflectUtil.getFields(t.getClass());
        // 过滤忽略字段和自增字段
        Stream<Field> stream = CollUtil.toList(fields).stream().filter(field ->
                Objects.isNull(field.getAnnotation(Ignore.class))
                        || Objects.isNull(field.getAnnotation(PK.class)));
        // 是否忽略空值字段
        if (ignoreNull) {
            stream = stream.filter(field -> Objects.nonNull(ReflectUtil.getFieldValue(t, field)));
        }
        return stream.collect(Collectors.toList());
    }

    /**
     * @Description: 获取实体类字段对应列名
     * @param fields
     * @return: java.util.List<java.lang.String>
     *
     * @Creator: fyz
     * @Date: 2020/6/3 22:43
     *
     */
    private List<String> getColumns(List<Field> fields) {
        return fields.stream().map(field -> {
            Column annotation = field.getAnnotation(Column.class);
            String column = Objects.isNull(annotation) ? field.getName() : annotation.name();
            return StrUtil.format("`{}`", column);
        }).collect(Collectors.toList());
    }

    /**
     * @Description: 获取主键字段
     * @return: java.util.Optional<java.lang.reflect.Field>
     * @Creator: fyz
     * @Date: 2020/6/4 0:07
     */
    private Optional<Field> getPrimaryKey() {
        Field[] fields = ReflectUtil.getFields(clazz);
        return CollUtil.toList(fields).stream().filter(field -> Objects.nonNull(field.getAnnotation(PK.class))).findFirst();
    }
}
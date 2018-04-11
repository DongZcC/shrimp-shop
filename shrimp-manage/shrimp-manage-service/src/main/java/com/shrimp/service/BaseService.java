package com.shrimp.service;

import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shrimp.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 功能说明:  封装baseservice提供通用服务<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-24<br>
 */
public class BaseService<T extends BasePojo> {


    // spring 4 提供的泛型注入功能
    @Autowired
    protected Mapper<T> mapper;


    /**
     * 根据ID查询数据
     *
     * @param id
     * @return
     */
    public T queryById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    public List<T> queryAll() {
        return mapper.select(null);
    }


    /**
     * 根据条件查询一条数据，如果多条数据会抛出异常
     *
     * @param record
     * @return
     */
    public T queryOne(T record) {
        return mapper.selectOne(record);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param record
     * @return
     */
    public List<T> queryListByWhere(T record) {
        return mapper.select(record);
    }


    /**
     * 分页查询
     *
     * @param record
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<T> queryPageListByWhere(T record, Integer page, Integer rows) {
        PageHelper helper = new PageHelper();
        helper.startPage(page, rows);
        List<T> results = queryListByWhere(record);
        return new PageInfo<T>(results);
    }


    /**
     * 新增数据，返回成功条数
     *
     * @param record
     * @return
     */
    public Integer save(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return mapper.insert(record);
    }


    /**
     * 新增数据，使用不为null的字段，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer saveSelective(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return mapper.insertSelective(record);
    }

    /**
     * 修改数据，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer update(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    /**
     * 修改数据，使用不为null的字段，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer updateSelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据ID删除数据
     *
     * @param id
     * @return
     */
    public Integer deleteById(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除数据
     * @param clazz
     * @param property
     * @param values
     * @return
     */
    public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, values);
        return mapper.deleteByExample(example);
    }

    /**
     * 根据条件删除
     * @param record
     * @return
     */
    public Integer deleteByWhere(T record) {
        return mapper.delete(record);
    }
}

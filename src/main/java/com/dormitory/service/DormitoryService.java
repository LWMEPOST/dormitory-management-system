package com.dormitory.service;

import com.dormitory.dao.DormitoryDao;
import com.dormitory.model.Dormitory;

import java.util.List;

/**
 * 宿舍业务逻辑层
 */
public class DormitoryService {
    
    private DormitoryDao dormitoryDao = new DormitoryDao();
    
    /**
     * 查询所有宿舍
     * @return 宿舍列表
     */
    public List<Dormitory> findAll() {
        return dormitoryDao.findAll();
    }
    
    /**
     * 根据ID查询宿舍
     * @param id 宿舍ID
     * @return 宿舍对象
     */
    public Dormitory findById(Integer id) {
        return dormitoryDao.findById(id);
    }
    
    /**
     * 添加宿舍
     * @param dormitory 宿舍对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean add(Dormitory dormitory) {
        return dormitoryDao.add(dormitory) > 0;
    }
    
    /**
     * 更新宿舍
     * @param dormitory 宿舍对象
     * @return 更新成功返回true，否则返回false
     */
    public boolean update(Dormitory dormitory) {
        return dormitoryDao.update(dormitory) > 0;
    }
    
    /**
     * 删除宿舍
     * @param id 宿舍ID
     * @return 删除成功返回true，否则返回false
     */
    public boolean delete(Integer id) {
        return dormitoryDao.delete(id) > 0;
    }
    
    /**
     * 更新宿舍当前入住人数
     * @param dormitoryId 宿舍ID
     * @param increment 增加或减少的人数（正数表示增加，负数表示减少）
     * @return 更新成功返回true，否则返回false
     */
    public boolean updateCurrentOccupancy(Integer dormitoryId, int increment) {
        return dormitoryDao.updateCurrentOccupancy(dormitoryId, increment) > 0;
    }
}

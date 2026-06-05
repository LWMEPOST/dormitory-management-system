package com.dormitory.service;

import com.dormitory.dao.RepairDao;
import com.dormitory.model.Repair;

import java.util.List;

/**
 * 报修业务逻辑层
 */
public class RepairService {
    
    private RepairDao repairDao = new RepairDao();
    
    /**
     * 查询所有报修记录
     * @return 报修记录列表
     */
    public List<Repair> findAll() {
        return repairDao.findAll();
    }
    
    /**
     * 根据ID查询报修记录
     * @param id 报修记录ID
     * @return 报修记录对象
     */
    public Repair findById(Integer id) {
        return repairDao.findById(id);
    }
    
    /**
     * 添加报修记录
     * @param repair 报修记录对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean add(Repair repair) {
        return repairDao.add(repair) > 0;
    }
    
    /**
     * 更新报修记录
     * @param repair 报修记录对象
     * @return 更新成功返回true，否则返回false
     */
    public boolean update(Repair repair) {
        return repairDao.update(repair) > 0;
    }
}

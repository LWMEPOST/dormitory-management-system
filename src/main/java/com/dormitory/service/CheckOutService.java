package com.dormitory.service;

import com.dormitory.dao.CheckOutDao;
import com.dormitory.dao.DormitoryDao;
import com.dormitory.model.CheckOut;

import java.util.List;

/**
 * 退宿业务逻辑层
 */
public class CheckOutService {
    
    private CheckOutDao checkOutDao = new CheckOutDao();
    private DormitoryDao dormitoryDao = new DormitoryDao();
    
    /**
     * 查询所有退宿记录
     * @return 退宿记录列表
     */
    public List<CheckOut> findAll() {
        return checkOutDao.findAll();
    }
    
    /**
     * 根据ID查询退宿记录
     * @param id 退宿记录ID
     * @return 退宿记录对象
     */
    public CheckOut findById(Integer id) {
        return checkOutDao.findById(id);
    }
    
    /**
     * 添加退宿记录
     * @param checkOut 退宿记录对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean add(CheckOut checkOut) {
        // 添加退宿记录
        int result = checkOutDao.add(checkOut);
        if (result > 0) {
            // 更新宿舍当前入住人数
            dormitoryDao.updateCurrentOccupancy(checkOut.getDormitoryId(), -1);
            return true;
        }
        return false;
    }
}

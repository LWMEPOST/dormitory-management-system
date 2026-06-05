package com.dormitory.service;

import com.dormitory.dao.CheckInDao;
import com.dormitory.dao.DormitoryDao;
import com.dormitory.model.CheckIn;

import java.util.List;

/**
 * 入住业务逻辑层
 */
public class CheckInService {
    
    private CheckInDao checkInDao = new CheckInDao();
    private DormitoryDao dormitoryDao = new DormitoryDao();
    
    /**
     * 查询所有入住记录
     * @return 入住记录列表
     */
    public List<CheckIn> findAll() {
        return checkInDao.findAll();
    }
    
    /**
     * 根据ID查询入住记录
     * @param id 入住记录ID
     * @return 入住记录对象
     */
    public CheckIn findById(Integer id) {
        return checkInDao.findById(id);
    }
    
    /**
     * 添加入住记录
     * @param checkIn 入住记录对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean add(CheckIn checkIn) {
        // 添加入住记录
        int result = checkInDao.add(checkIn);
        if (result > 0) {
            // 更新宿舍当前入住人数
            dormitoryDao.updateCurrentOccupancy(checkIn.getDormitoryId(), 1);
            return true;
        }
        return false;
    }
    
    /**
     * 根据学生ID查询入住记录
     * @param studentId 学生ID
     * @return 入住记录对象
     */
    public CheckIn findByStudentId(Integer studentId) {
        return checkInDao.findByStudentId(studentId);
    }
}

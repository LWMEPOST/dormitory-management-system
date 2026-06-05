package com.dormitory.service;

import com.dormitory.dao.StudentDao;
import com.dormitory.model.Student;

import java.util.List;

/**
 * 学生业务逻辑层
 */
public class StudentService {
    
    private StudentDao studentDao = new StudentDao();
    
    /**
     * 查询所有学生
     * @return 学生列表
     */
    public List<Student> findAll() {
        return studentDao.findAll();
    }
    
    /**
     * 根据ID查询学生
     * @param id 学生ID
     * @return 学生对象
     */
    public Student findById(Integer id) {
        return studentDao.findById(id);
    }
    
    /**
     * 添加学生
     * @param student 学生对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean add(Student student) {
        return studentDao.add(student) > 0;
    }
    
    /**
     * 更新学生
     * @param student 学生对象
     * @return 更新成功返回true，否则返回false
     */
    public boolean update(Student student) {
        return studentDao.update(student) > 0;
    }
    
    /**
     * 删除学生
     * @param id 学生ID
     * @return 删除成功返回true，否则返回false
     */
    public boolean delete(Integer id) {
        return studentDao.delete(id) > 0;
    }
    
    /**
     * 根据学号查询学生
     * @param studentId 学号
     * @return 学生对象
     */
    public Student findByStudentId(String studentId) {
        return studentDao.findByStudentId(studentId);
    }
}

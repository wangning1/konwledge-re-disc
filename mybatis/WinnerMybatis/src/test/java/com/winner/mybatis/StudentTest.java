package com.winner.mybatis;

import com.winner.model.PhoneNumber;
import com.winner.model.Student;
import com.winner.mybatis.service.StudentService;
import org.apache.commons.logging.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangning on 2017/7/13.
 */
public class StudentTest {

    private static StudentService studentService;
    private final static Logger logger = LoggerFactory.getLogger(StudentTest.class);

    @Before
    public void beforeTest() {
        studentService = new StudentService();
        logger.info("--before test--");
    }

    @Test
    public void getAllStudent() {
        logger.info("--test--");
        List<Student> students = studentService.getAllStudent();
        Assert.assertNotNull(students);
        for(Student student : students){
            logger.info(""+student.toString());
        }
    }

    @Test
    public void getAllStudentByAnotherWay(){
        logger.info("--test getAll by another way--");
        List<Student> students = studentService.getAllStudentByAnotherWay();
        Assert.assertNotNull(students);
        for(Student student : students){
            logger.info(""+student.toString());
        }
    }

    @Test
    public void getStudent(){
        logger.info("--test get Student");
        Student student = studentService.getStudentById(4);
        Assert.assertNotNull(student);
        logger.info("" + student.toString());
    }


    @Test
    public void testAddStudent(){
        logger.info("--test add student");
        Student student = new Student();
        student.setName("wangning");
        student.setDob(new Date());
        PhoneNumber phoneNumber = new PhoneNumber("123","456","789");
        student.setPhone(phoneNumber);
        studentService.addStudent(student);
    }

    @Test
    public void testGetResultHandler(){
        logger.info("--test customer result handler --");
        Map map = studentService.getStudentIdAndName();
        Assert.assertNotNull(map);
        logger.info(" " + map.size());
    }

    @Test
    public void getStudentByAnnotion(){
        Student student = studentService.getStudnet(1);
        Assert.assertNotNull(student);
        logger.info(" " + student.toString());
    }


}

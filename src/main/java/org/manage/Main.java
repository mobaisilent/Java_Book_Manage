package org.manage;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.manage.entity.Book;
import org.manage.entity.Student;
import org.manage.mapper.BookMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
    try (SqlSession sqlSession = factory.openSession(true)) {
      BookMapper mapper = sqlSession.getMapper(BookMapper.class);
      // 通过动态代理的方式，创建了一个实现类
      System.out.println(mapper.addStudent(new Student(4, "张三", "男", 1)));
      // 太诡异了，为什么总是创建不了触发器？
      // 多少数据库是创建成功了
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
// factory -> sqlSession -> mapper
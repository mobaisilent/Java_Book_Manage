package org.manage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.manage.entity.Book;
import org.manage.entity.Student;

public interface BookMapper {
  @Insert("insert into student(sid,name,sex,grade) values (#{sid},#{name},#{sex},#{grade})")
  int addStudent(Student student);

  @Insert("insert into borrow(sid,bid) values (#{sid},#{bid})")
  int borrowBook(int sid, int bid);

  @Insert("insert into book(title,desc,price) values (#{title},#{desc},#{price})")
  int addBook(Book book);
}

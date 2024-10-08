package org.manage.sql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.manage.mapper.BookMapper;

import java.io.IOException;
import java.util.function.Consumer;

public class SqlUtil {
  private SqlUtil() {
  }

  private static SqlSessionFactory factory;
  // 私有的static对象

  static {
    try {
      factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
      // 注意后面读取xml的方式
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void doSqlWork(Consumer<BookMapper> consumer){
    try(SqlSession session=factory.openSession(true)){
        BookMapper mapper = session.getMapper(BookMapper.class);
        consumer.accept(mapper);
    }
  }
}
// 创建工具类 SqlUtil，用于获取 SqlSession 对象 显然易见，使用方式 SqlUtile.getSession() 即可获取 SqlSession 对象
package org.manage;

import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.manage.entity.Book;
import org.manage.entity.Student;
import org.manage.entity.Borrow; // 假设你有 Borrow 类
import org.manage.sql.SqlUtil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;

import static java.lang.System.exit;

@Log
public class Main {
  public static void main(String[] args) throws IOException {
    LogManager manager = LogManager.getLogManager();
    manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
    // 这里是借助了Lombok的日志系统
    log.info("程序开始运行");
    Runtime.getRuntime().addShutdownHook(new Thread(() -> log.info("程序结束")));

    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("==========图书管理系统==========");
      System.out.println("1.添加学生信息");
      System.out.println("2.添加书籍信息");
      System.out.println("3.添加借阅信息");
      System.out.println("4.查询学生信息");
      System.out.println("5.查询书籍信息");
      System.out.println("6.查询借阅信息");
      System.out.println("7.退出系统");
      System.out.print("请输入你的选择：");
      String input = scanner.nextLine();
      int check;
      try {
        check = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("请输入正确的数字！！！");
        continue;
      }
      // 处理数据输入，
      switch (check) {
        case 1:
          addStudent(scanner);
          break;
        case 2:
          addBook(scanner);
          break;
        case 3:
          addBorrow(scanner);
          break;
        case 4:
          getAllStudents();
          break;
        case 5:
          getAllBooks();
          break;
        case 6:
          getAllBorrows();
          break;
        case 7:
          log.info("程序主动退出");
          exit(0);
        default:
          System.out.println("无效选择，请重试。");
          break;
      }
    }
  }

  private static void addStudent(Scanner scanner) {
    System.out.print("请输入学生姓名：");
    String name = scanner.nextLine();
    System.out.print("请输入学生性别(boy/girl)：");
    String sex = scanner.nextLine();
    System.out.print("请输入学生年级：");
    String stringGrade = scanner.nextLine();
    int grade = Integer.parseInt(stringGrade);
    SqlUtil.doSqlWork(mapper -> {
      Student student = new Student(name, sex, grade);
//      int result = mapper.addStudent(new Student(name, sex, grade));
      int result = mapper.addStudent(student);
      if (result > 0) {
        log.info("添加学生信息成功" + student);
        System.out.println("添加学生信息成功");
      } else {
        log.info("添加学生信息失败" + student);
        System.out.println("添加学生信息失败");
      }
    });
  }

  private static void addBook(Scanner scanner) {
    System.out.print("请输入书籍名称：");
    String title = scanner.nextLine();
    System.out.print("请输入书籍描述：");
    String desc = scanner.nextLine();
    System.out.print("请输入书籍价格：");
    String stringPrice = scanner.nextLine();
    double price = Double.parseDouble(stringPrice);
    SqlUtil.doSqlWork(mapper -> {
      int result = mapper.addBook(new Book(title, desc, price));
      if (result > 0) {
        log.info("添加书籍信息成功" + new Book(title, desc, price));
        System.out.println("添加书籍信息成功");
      } else {
        log.info("添加书籍信息失败" + new Book(title, desc, price));
        System.out.println("添加书籍信息失败");
      }
    });
  }

  private static void addBorrow(Scanner scanner) {
    System.out.print("请输入学生id：");
    String stringSid = scanner.nextLine();
    int sid = Integer.parseInt(stringSid);
    System.out.print("请输入书籍id：");
    String stringBid = scanner.nextLine();
    int bid = Integer.parseInt(stringBid);
    // 这里根据输入的sid和bid进行检验即可getStudentBySid和getBookByBid
    SqlUtil.doSqlWork(mapper -> {
      if (mapper.getStudentBySid(sid) == null) {
        log.info("插入借阅信息但是学生id不存在");
        System.out.println("学生id不存在，请先检查学生信息");
        return;
      } else if (mapper.getBookByBid(bid) == null) {
        log.info("插入借阅信息但是书籍id不存在");
        System.out.println("书籍id不存在，请先检查书籍信息");
        return;
      } else {
        int result = mapper.addBorrow(sid, bid);
        if (result > 0) {
          log.info("添加借阅信息成功" + "Borrow{sid=" + sid + ", bid=" + bid + "}");
          System.out.println("添加借阅信息成功");
        } else {
          log.info("添加借阅信息失败" + "Borrow{sid=" + sid + ", bid=" + bid + "}");
          System.out.println("添加借阅信息失败");
        }
      }
    });
  }

  private static void getAllStudents() {
    SqlUtil.doSqlWork(mapper -> {
      List<Student> students = mapper.getAllStudents();
      if (students.isEmpty()) {
        log.info("没有找到学生信息。");
        System.out.println("没有找到学生信息。");
      } else {
        log.info("成功查询学生信息");
        System.out.println("学生信息：");
        for (Student student : students) {
          System.out.println(student);
        }
      }
    });
  }

  private static void getAllBooks() {
    SqlUtil.doSqlWork(mapper -> {
      List<Book> books = mapper.getAllBooks();
      if (books.isEmpty()) {
        log.info("没有找到书籍信息。");
        System.out.println("没有找到书籍信息。");
      } else {
        log.info("成功查询书籍信息");
        System.out.println("书籍信息：");
        for (Book book : books) {
          System.out.println(book);
        }
      }
    });
  }

  private static void getAllBorrows() {
    SqlUtil.doSqlWork(mapper -> {
      List<Borrow> borrows = mapper.getAllBorrows();
      if (borrows.isEmpty()) {
        log.info("没有找到借阅信息。");
        System.out.println("没有找到借阅信息。");
      } else {
        log.info("成功查询借阅信息");
        System.out.println("借阅信息：");
        for (Borrow borrow : borrows) {
          System.out.println(borrow);
        }
      }
    });
  }
}

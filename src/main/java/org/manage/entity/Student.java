package org.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
  int sid;

  public Student(String name, String sex, int grade) {
    this.name = name;
    this.sex = sex;
    this.grade = grade;
  }

  String name;
  String sex;
  int grade;
}
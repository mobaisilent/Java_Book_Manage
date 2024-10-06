package org.manage.entity;

import lombok.Data;

@Data
public class Student {
  final int sid;
  final String name;
  final String sex;
  final int grade;
}
// LomBok的初次使用记得将idea的注解开启
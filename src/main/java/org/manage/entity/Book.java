package org.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
  int bid;

  public Book(String title, String desc, double price) {
    this.title = title;
    this.desc = desc;
    this.price = price;
  }

  String title;
  String desc;
  double price;
}

package com.dudnyk;

import java.util.Date;

public class Employee {
  private String name;
  private float workHours;
  private Date date;

  public Employee(String name, float workHours, Date date) {
    this.name = name;
    this.workHours = workHours;
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public float getWorkHours() {
    return workHours;
  }

  public Date getDate() {
    return date;
  }
}

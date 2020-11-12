package com.gaoshang.lambda01;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {

    public static List<Employee> getEmployeeList(){
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001,"zhang3",15,6000.00));
        list.add(new Employee(1002,"zhang4",16,7000.00));
        list.add(new Employee(1003,"zhang5",17,8000.00));
        list.add(new Employee(1004,"zhang6",18,9000.00));
        list.add(new Employee(1005,"zhang7",19,10000.00));
        list.add(new Employee(1006,"zhang8",20,11000.00));
        list.add(new Employee(1007,"zhang9",21,12000.00));
        list.add(new Employee(1008,"zhang0",22,13000.00));

        return list;
    }
}

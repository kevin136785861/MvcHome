package demo.dao;

import demo.domain.Classes;

import java.util.List;

public interface ClassesDao {
    Classes getClass(int id);
    List<Classes> getClass1(int id);
}

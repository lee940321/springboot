package app.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import app.example.domain.Test;
@Mapper
public interface TestMapper {

	List<Test> findByName(@Param("name") String name);
}

package app.example.service;

import java.util.List;

import app.example.domain.Test;

public interface TestService {

	List<Test> queryTest(String name);
}

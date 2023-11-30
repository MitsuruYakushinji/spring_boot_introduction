package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	// Repositoryをフィールドに用意
	private final EmployeeRepository employeeRepository;

	// コンストラクタでRepositoryをインジェクションする
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		// ビジネスロジック用のメソッドを以降に定義
	}

	// 全件取得処理
	// メソッド名はRepositoryの処理や最終的な返り値がわかるような名前に
	public List<Employee> findAllEmployee() {
		// employeesテーブルのデータをemployeeオブジェクトにつめて全件取得する
		return this.employeeRepository.findAll();
	}

	// 主キーでの1件検索
	public Employee findEmployee(Integer employeeId) {
		// データの1件取得
		Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
		// OptionalからEntityクラスの取得を試みる
		Employee employee = optionalEmployee.get();
		return employee;
	}

	public List<Employee> findByName(String name) {
		return this.employeeRepository.findByName(name);
	}

	// 新規登録処理
	public Employee insert(String name, String department) {
		// 保存したいEntityクラスのインスタンスを作成
		Employee employee = new Employee();

		// 引数で受けたname, departmentをEmployeeオブジェクトにセット
		employee.setName(name);
		employee.setDepartment(department);

		// データベースに保存する
		return this.employeeRepository.save(employee);
	}

	// 更新処理
	public Employee update(Integer employeeId, String name, String department) {
		// 更新したいデータを取得する
		Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
		Employee employee = optionalEmployee.get();

		// Entityクラスのフィールドに更新内容をセット
		employee.setName(name);
		employee.setDepartment(department);

		// データベーに保存する
		return this.employeeRepository.save(employee);
	}
	
	public void delete(Integer Id) {
		// データを1件削除する
		this.employeeRepository.deleteById(Id);
	}
	
}








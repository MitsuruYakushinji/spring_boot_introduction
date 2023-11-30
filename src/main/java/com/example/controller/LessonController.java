package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//ModelAttributeクラスのインポート
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.form.SampleForm;

@Controller
@RequestMapping("/lesson")
public class LessonController {

	//	// http://localhost:8080/lesson/ にリクエストするとindex()が実行される
	//	@GetMapping("/sample")
	//	// HTMLをレスポンスにする場合、@ResponseBodyを必ず削除します
	//	//@ResponseBody
	//	public String sample() {
	//		return "index";
	//	}

	@GetMapping("/sample")
	// Model型の値を必ず借り引数に持つ
	public String sample(Model model) {
		// 変数定義
		String text = "Hello Spring Boot!!";
		// messageという属性名、Hello Spring Boot!!という値を追加します
		model.addAttribute("message", text);
		return "index";
	}

	// http://localhost:8080/lesson/test にリクエストするとtest()が実行される
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "Good Evening!";
	}

	// 共通のURLはhttp://localhost/lessonとします
	// 引数をnumとします
	@GetMapping("/test/{num}")
	// パラメータを受け取る
	public String test1(@PathVariable Integer num) {
		return " " + num + " ";
	}

	//パスにはnumを指定
	@GetMapping("/find/{num}")
	// 引数名はnumberとしましたが name = "num"としたことでパスの値を受け取れます
	public String test2(@PathVariable(name = "num") Integer number) {
		return " " + number + " ";
	}

	@GetMapping("/request_test")
	// 返り値をレスポンスとして扱う
	@ResponseBody
	// name... 名前, bloodType... 血液型
	// パラメータ名と仮引数名が同じな場合、@RequestParam String nameのようにパラメータ名の指定を省略できます
	public String getTest(@RequestParam("name") String name, @RequestParam("bloodType") String bloodType) {
		return "名前: " + name + "<br>血液型: " + bloodType;
	}

	@PostMapping("/request_text")
	@ResponseBody
	public String postTest(SampleForm sampleForm) {
		return "名前: " + sampleForm.getName() + "<br>血液型: " + sampleForm.getBloodType();
	}

//	@GetMapping("/form_test")
//	public String formTest(SampleForm sampleForm, Model model) {
//		model.addAttribute("sampleForm", sampleForm);
//		return "lesson/form_test";
//	}

	@GetMapping("/form_test")
	// SampleFormをModel属性にセットするだけであればModelクラスは不要になる
	public String formTest(@ModelAttribute SampleForm sampleForm) {
		return "lesson/form_test";
	}
}

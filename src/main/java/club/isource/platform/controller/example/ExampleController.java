package club.isource.platform.controller.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.isource.platform.service.inf.UserService;
import club.isource.platform.vo.PageInfoVo;


/** 
	* @author  高新国
	* @date 创建时间：2018年4月9日 上午11:42:39	
	* @version 1.0
**/
@RestController("/example")
public class ExampleController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/test/{userid}")
	UserVo selectUserByKey(@PathVariable String userid) {
		return userService.selectUserByKey(userid);
	}/**
	 * 
	 */
	public ExampleController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/test")
	PageInfoVo<UserVo> selectAll() {
		return userService.selectAll();
	}
	
	
}

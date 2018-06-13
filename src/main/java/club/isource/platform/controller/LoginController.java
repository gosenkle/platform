package club.isource.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import club.isource.platform.dao.po.User;
import club.isource.platform.service.inf.UacService;
import club.isource.platform.vo.UserTokenVo;

@RestController
@RequestMapping(value="uac")
public class LoginController {

	@Autowired
	private UacService uacService;
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public UserTokenVo login(@RequestBody User req) {
		UserTokenVo ret = new UserTokenVo();
		String token =  uacService.login(req.getUsername(), req.getPassword());
		ret.setCode("1");
		ret.setToken(token);
		return ret;
	}
}

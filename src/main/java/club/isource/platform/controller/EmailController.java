package club.isource.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import club.isource.platform.service.inf.MailService;
import club.isource.platform.vo.BaseVo;
import club.isource.platform.vo.MailVo;

@RestController
@RequestMapping(value="email")
public class EmailController {

	@Autowired
	private MailService mailService;
	
	@RequestMapping(value="send", method=RequestMethod.POST)
	public BaseVo<Void> sendEmail(@RequestBody MailVo req){
		BaseVo<Void> ret = new BaseVo<Void>();
		int code = mailService.sendMail(req.getAddr(), req.getContent());
		ret.setCode(code);
		return ret;
	}
}

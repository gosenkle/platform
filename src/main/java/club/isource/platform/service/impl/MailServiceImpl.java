package club.isource.platform.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import club.isource.platform.service.inf.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromMail;

	@Override
	public int sendMail(String addr, String content) {
		logger.info("向地址"+addr+"发送邮件！");
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
	        // 发送目的地
			msg.setTo(addr);
			// 发送内容
	        msg.setText(content);
	        // 发送者
	        msg.setFrom(fromMail);
	        javaMailSender.send(msg);
			return 1;
		} catch (Exception e) {
			logger.error("发送失败：" + e.getCause());
			return 0;
		}
		
	}

}

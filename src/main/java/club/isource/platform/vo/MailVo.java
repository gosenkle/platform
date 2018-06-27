package club.isource.platform.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel("邮件发送模型")
public class MailVo {
	@ApiModelProperty("邮件目的地地址")
	private String addr;
	
	@ApiModelProperty("邮件内容")
	private String content;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MailVo [addr=" + addr + ", content=" + content + "]";
	}
	
}

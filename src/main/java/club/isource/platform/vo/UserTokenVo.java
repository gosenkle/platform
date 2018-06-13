package club.isource.platform.vo;

import club.isource.platform.dao.po.User;
import io.swagger.annotations.ApiModel;

@ApiModel(value="认证通过的token信息模型")
public class UserTokenVo extends BaseVo<User>{
	
	private String username;
	
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

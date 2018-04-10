package club.isource.platform.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** 
	* @author  高新国
	* @date 创建时间：2018年4月9日 下午3:05:51	
	* @version 1.0
**/
@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseVo {
	
	private String code = "1";
	
	private String errMsg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "BaseVo [code=" + code + ", errMsg=" + errMsg + "]";
	}
	
}

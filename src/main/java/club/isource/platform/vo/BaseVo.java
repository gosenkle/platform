package club.isource.platform.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
	* @author  高新国
	* @date 创建时间：2018年4月9日 下午3:05:51	
	* @version 1.0
**/
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel
public class BaseVo<T> {
	@ApiModelProperty(value="是否成功")
	private int code = 0;
	
	@ApiModelProperty(value="错误信息")
	private String errMsg;
	
	@ApiModelProperty(value="单行数据")
	private T data;
	
	@ApiModelProperty(value="多行数据")
	protected List<T> list;
	
	@ApiModelProperty(value="变更行数")
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "BaseVo [code=" + code + ", errMsg=" + errMsg + ", data=" + data + ", list=" + list + ", count=" + count
				+ "]";
	}

}

package club.isource.platform.service.inf;

import club.isource.platform.controller.example.UserVo;
import club.isource.platform.vo.PageInfoVo;

/** 
	* @author  高新国
	* @date 创建时间：2018年4月9日 下午2:59:11	
	* @version 1.0
**/


public interface UserService {
	UserVo selectUserByKey(String userid);
	
	PageInfoVo<UserVo> selectAll();
}

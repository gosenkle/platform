package club.isource.platform.dao.mapper;

import java.util.List;

import club.isource.platform.dao.po.UserPo;

/** 
	* @author  高新国
	* @date 创建时间：2018年4月9日 下午2:46:07	
	* @version 1.0
**/
public interface UserInf {
	UserPo selectUserByKey(String userid);
	
	List<UserPo> selectAll();
}

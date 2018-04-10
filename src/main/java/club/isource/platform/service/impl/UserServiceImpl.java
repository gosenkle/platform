package club.isource.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import club.isource.platform.controller.example.UserVo;
import club.isource.platform.dao.mapper.UserInf;
import club.isource.platform.dao.po.UserPo;
import club.isource.platform.service.inf.UserService;
import club.isource.platform.vo.PageInfoVo;


/** 
	* @author  高新国
	* @date 创建时间：2018年4月9日 下午3:00:56	
	* @version 1.0
**/
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserInf userinf;
	
	@Override
	public UserVo selectUserByKey(String userid) {
		UserVo ret = new UserVo();
		UserPo po = userinf.selectUserByKey(userid);
		BeanUtils.copyProperties(po, ret);
		ret.setCode("1");
		return ret;
	}

	@Override
	public PageInfoVo<UserVo> selectAll() {
		PageInfoVo<UserVo> ret = new PageInfoVo<UserVo>();
		List<UserPo> pos = userinf.selectAll();
		// 获取分页结构
		PageInfo<UserPo> poLists = new PageInfo<UserPo>(pos);
		// 转换为视图展示对象
		BeanUtils.copyProperties(poLists, ret);
		List<UserVo> vos = new ArrayList<UserVo>();
		for (UserPo userPo : pos) {
			UserVo vo = new UserVo();
			BeanUtils.copyProperties(userPo, vo);
			vos.add(vo);
		}
		ret.setList(vos);
		return ret;
	}
}

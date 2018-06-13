package club.isource.platform.service.impl;

import club.isource.platform.dao.mapper.UserMapper;
import club.isource.platform.dao.po.User;
import club.isource.platform.dao.po.UserExample;
import club.isource.platform.service.inf.UserService;
import club.isource.platform.vo.BaseVo;
import club.isource.platform.vo.PageInfoVo;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public club.isource.platform.vo.PageInfoVo<User> selectAll() {
        PageInfoVo<User> ret = new PageInfoVo<User>();
        List<User> lists = userMapper.selectByExample(null);
        PageInfo<User> pageInfo = new PageInfo<User>(lists);
		BeanUtils.copyProperties(pageInfo, ret);
		ret.setList(pageInfo.getList());
		return ret;
    }

    @Override
    public club.isource.platform.vo.BaseVo<User> selectByKey(Integer id) {
        BaseVo<User> ret = new BaseVo<User>();
        User data = userMapper.selectByPrimaryKey(id);
        ret.setData(data);
        return ret;
    }

    @Override
    @Transactional
    public club.isource.platform.vo.BaseVo<User> deleteByKey(Integer id) {
        BaseVo<User> ret = new BaseVo<User>();
        int count = userMapper.deleteByPrimaryKey(id);
        ret.setCount(count);
        return ret;
    }

    @Override
    @Transactional
    public club.isource.platform.vo.BaseVo<User> add(User req) {
        BaseVo<User> ret = new BaseVo<User>();
        int count = userMapper.insertSelective(req);
        ret.setCount(count);
        ret.setData(req);
        return ret;
    }

    @Override
    @Transactional
    public club.isource.platform.vo.BaseVo<User> update(User req) {
        BaseVo<User> ret = new BaseVo<User>();
        int count = userMapper.updateByPrimaryKeySelective(req);
        ret.setCount(count);
        ret.setData(req);
        return ret;
    }

    @Override
    public club.isource.platform.vo.PageInfoVo<User> selectByCondtion(User req) {
        PageInfoVo<User> ret = new PageInfoVo<User>();
        UserExample example = new UserExample();
		List<User> lists = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<User>(lists);
		BeanUtils.copyProperties(pageInfo, ret);
		ret.setList(pageInfo.getList());
		return ret;
    }
}
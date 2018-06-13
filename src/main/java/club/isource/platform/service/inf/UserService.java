package club.isource.platform.service.inf;

import club.isource.platform.dao.po.User;
import club.isource.platform.vo.BaseVo;
import club.isource.platform.vo.PageInfoVo;

public interface UserService {
    club.isource.platform.vo.PageInfoVo<User> selectAll();

    club.isource.platform.vo.BaseVo<User> selectByKey(Integer id);

    club.isource.platform.vo.BaseVo<User> deleteByKey(Integer id);

    club.isource.platform.vo.BaseVo<User> add(User req);

    club.isource.platform.vo.BaseVo<User> update(User req);

    club.isource.platform.vo.PageInfoVo<User> selectByCondtion(User req);
}
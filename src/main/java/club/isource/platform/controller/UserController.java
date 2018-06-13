package club.isource.platform.controller;

import club.isource.platform.dao.po.User;
import club.isource.platform.service.inf.UserService;
import club.isource.platform.vo.BaseVo;
import club.isource.platform.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.statement.truncate.Truncate;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="用户模块操作接口", consumes="application/json", 
	produces="application/json", protocols="http, https, ws, wss",
	description="用于用户接口新增、修改、删除以及查询！")
@RestController
@RequestMapping(value="users")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value="查询用户所有列表", tags="v1.0", httpMethod="GET", response=PageInfoVo.class)
    @RequestMapping(method=RequestMethod.GET)
    public club.isource.platform.vo.PageInfoVo<User> selectAll() {
        return userService.selectAll();
    }

    
    @RequestMapping(value="add", method=RequestMethod.POST)
    public club.isource.platform.vo.BaseVo<User> add(@RequestBody User req) {
        return userService.add(req);
    }

    @RequestMapping(method=RequestMethod.POST)
    public club.isource.platform.vo.PageInfoVo<User> selectByCondition(@RequestBody User req) {
        return userService.selectByCondtion(req);
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
    public club.isource.platform.vo.BaseVo<User> delete(@PathVariable(value = "id") Integer id) {
        return userService.deleteByKey(id);
    }

    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public club.isource.platform.vo.BaseVo<User> selectByKey(@PathVariable(value = "id") Integer id) {
        return userService.selectByKey(id);
    }

    @RequestMapping(value="update", method=RequestMethod.PUT)
    public club.isource.platform.vo.BaseVo<User> update(@RequestBody User req) {
        return userService.update(req);
    }
}
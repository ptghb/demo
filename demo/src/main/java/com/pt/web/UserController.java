package com.pt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pt.service.UserService;
import com.pt.vo.ResultInfo;
import com.pt.vo.UserEntity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	@ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ResultInfo<List<UserEntity>> getUsers() throws Exception {
		List<UserEntity> userList = userSerivce.query();
		ResultInfo<List<UserEntity>> result = new ResultInfo<List<UserEntity>>(userList);
        return result;
    }
	
	@ApiOperation(value="获取用户列表（分页）", notes="分页查询")
    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResultInfo<List<UserEntity>> getUsersByPage(@RequestParam Integer pageNo,@RequestParam Integer pageSize) throws Exception {
		List<UserEntity> userList = userSerivce.query(pageNo,pageSize);
		ResultInfo<List<UserEntity>> result = new ResultInfo<List<UserEntity>>(userList);
        return result;
    }
    
	@ApiOperation(value="获取用户", notes="根据url的id来获取用户详细信息")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResultInfo<UserEntity> getUser(@PathVariable Long id) throws Exception {
		UserEntity user = userSerivce.query(id);
        ResultInfo<UserEntity> result = new ResultInfo<UserEntity>(user);
        return result;
    }

	@ApiOperation(value="添加用户", notes="添加用户数据")
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public ResultInfo<UserEntity> addUser(@ModelAttribute UserEntity user) throws Exception { 
        // 处理"/users/"的POST请求，用来创建User 
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数 
		user = userSerivce.save(user);
        ResultInfo<UserEntity> result = new ResultInfo<UserEntity>(user);
        return result;
    } 
	
	@ApiOperation(value="更新用户", notes="更新用户数据")
    @RequestMapping(value="/upd", method=RequestMethod.POST) 
    public ResultInfo<UserEntity> updUser(@ModelAttribute UserEntity user) throws Exception {
		user = userSerivce.save(user);
        ResultInfo<UserEntity> result = new ResultInfo<UserEntity>(user);
        return result;
    } 
}

package com.pt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pt.service.UserRoleService;
import com.pt.vo.ResultInfo;
import com.pt.vo.UserRoleEntity;

import io.swagger.annotations.ApiOperation;

/**
 * 分配权限
 * @author gehb
 *
 */
@RestController
@RequestMapping(value="/userroles")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userroleSerivce;

	@ApiOperation(value="添加用户权限", notes="为用户添加权限")
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public ResultInfo<UserRoleEntity> addRole(@ModelAttribute UserRoleEntity userrole) throws Exception { 
		userrole = userroleSerivce.save(userrole);
        ResultInfo<UserRoleEntity> result = new ResultInfo<UserRoleEntity>(userrole);
        return result;
    } 
	
	@ApiOperation(value="删除用户权限", notes="为用户删除权限")
    @RequestMapping(value="/del", method=RequestMethod.POST) 
    public ResultInfo<String> updUser(@ModelAttribute UserRoleEntity userrole) throws Exception {
		userroleSerivce.delete(userrole);
        ResultInfo<String> result = new ResultInfo<String>();
        return result;
    } 
}

package com.pt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pt.service.RoleService;
import com.pt.vo.ResultInfo;
import com.pt.vo.RoleEntity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleSerivce;
	
	@ApiOperation(value="获取权限列表", notes="")
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ResultInfo<List<RoleEntity>> getRoles() throws Exception {
		List<RoleEntity> roleList = roleSerivce.query();
		ResultInfo<List<RoleEntity>> result = new ResultInfo<List<RoleEntity>>(roleList);
        return result;
    }
	
	@ApiOperation(value="获取权限列表（分页）", notes="分页查询")
    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResultInfo<List<RoleEntity>> getRolesByPage(@RequestParam Integer pageNo,@RequestParam Integer pageSize) throws Exception {
		List<RoleEntity> roleList = roleSerivce.query(pageNo,pageSize);
		ResultInfo<List<RoleEntity>> result = new ResultInfo<List<RoleEntity>>(roleList);
        return result;
    }
    
	@ApiOperation(value="获取权限", notes="根据url的id来获取权限详细信息")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResultInfo<RoleEntity> getRole(@PathVariable Long id) throws Exception {
		RoleEntity role = roleSerivce.query(id);
        ResultInfo<RoleEntity> result = new ResultInfo<RoleEntity>(role);
        return result;
    }

	@ApiOperation(value="添加权限", notes="添加权限数据")
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public ResultInfo<RoleEntity> addRole(@ModelAttribute RoleEntity role) throws Exception { 
        // 处理"/users/"的POST请求，用来创建User 
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数 
		role = roleSerivce.save(role);
        ResultInfo<RoleEntity> result = new ResultInfo<RoleEntity>(role);
        return result;
    } 
	
	@ApiOperation(value="更新权限", notes="更新权限数据")
    @RequestMapping(value="/upd", method=RequestMethod.POST) 
    public ResultInfo<RoleEntity> updUser(@ModelAttribute RoleEntity role) throws Exception {
		role = roleSerivce.save(role);
        ResultInfo<RoleEntity> result = new ResultInfo<RoleEntity>(role);
        return result;
    } 
}

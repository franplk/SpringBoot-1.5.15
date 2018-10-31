package com.plk.sbdemo.admin.web.controller.user;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.admin.domain.user.User;
import com.plk.sbdemo.admin.service.user.UserService;
import com.plk.sbdemo.admin.utils.ExcelUtil;
import com.plk.sbdemo.admin.web.ApiResponse;
import com.plk.sbdemo.admin.web.exception.excel.ExcelExportException;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String userPage(Model model) {
		return "user/list";
	}

	@ResponseBody
	@RequestMapping("/list")
	public ApiResponse userList() {
		List<User> userList = userService.userList();
		return ApiResponse.success(userList);
	}
	
	@GetMapping("/detail/{userid}")
	public String userPage(Model model, @PathVariable Long userid) {
		User user = userService.findUserById(userid);
		
		model.addAttribute("user", user);
		model.addAttribute("roleList", user.getRoleList());
		
		return "user/detail";
	}
	
	@ResponseBody
	@PostMapping("/delete/{userid}")
	public ApiResponse delete(@PathVariable Long userid) {
		userService.deleteUser(userid);
		return ApiResponse.success();
	}
	
	@ResponseBody
	@PostMapping("/freeze/{userid}")
	public ApiResponse freeze(@PathVariable Long userid) {
		userService.freezeUser(userid, true);
		return ApiResponse.success();
	}
	
	@ResponseBody
	@PostMapping("/unfreeze/{userid}")
	public ApiResponse unFreeze(@PathVariable Long userid) {
		userService.freezeUser(userid, false);
		return ApiResponse.success();
	}
	
	@GetMapping("/download")
	public void export(HttpServletResponse response) {
		List<User> userList = userService.userList();
		Workbook workbook = null;
		try {
			List<String> excludeFields = Arrays.asList(new String[]{"serialVersionUID", "roleList"});
			workbook = ExcelUtil.exportExcel(User.class, userList, excludeFields);
			response.setHeader("content-Type", "application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename='export.xls'");
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelExportException("下载失败");
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (Exception e) {}
		}
	}
}

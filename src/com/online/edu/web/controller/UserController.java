package com.online.edu.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.online.edu.entity.User;
import com.online.edu.service.CourseService;
import com.online.edu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	CourseService courseService;

	@RequestMapping("/registForm")
	public String registForm() {
		return "fore/user-regist";
	}
	
	@RequestMapping("/regist")
	public String regist(Integer type, String loginId, String loginPsw,
			Boolean sex, String name, String email, String introduction,
			MultipartFile userPhoto, HttpServletRequest request, Model model)
			throws IllegalStateException, IOException {

		User user = new User();
		user.setLoginId(loginId);
		user.setLoginPsw(loginPsw);
		user.setType(type);
		user.setEmail(email);
		user.setName(name);
		user.setSex(sex);
		user.setIntroduction(introduction);
		if (!userPhoto.isEmpty()) {

			String fileExt = userPhoto.getOriginalFilename().substring(
					userPhoto.getOriginalFilename().lastIndexOf(".") + 1);
			if (!fileExt.equals("jpg") && !fileExt.equals("png")
					&& !fileExt.equals("gif") && !fileExt.equals("bmp")) {
				model.addAttribute("errorMsg", "文件格式不正确");
				return "forward:/user/registForm";
			}

			if (userPhoto.getSize() > 2 * 1024 * 1024) {
				model.addAttribute("errorMsg", "文件大小只能在2M以内");
				return "forward:/user/registForm";
			}
			String saveName = new SimpleDateFormat("yyyyMMddHHssSSS")
					.format(new Date()) + "." + fileExt;
			String realPath = request.getSession().getServletContext()
					.getRealPath("resource/photo/user/");
			user.setPhoto(saveName);
			File saveFile = new File(realPath, saveName);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			userPhoto.transferTo(saveFile);
		}
		Boolean code = userService.registUser(user);
		if (!code) {
			model.addAttribute("errorMsg", "登录账号重复");
			return "forward:/user/registForm";
		}
		return "redirect:/user/loginForm";
	}

	@RequestMapping("/checkLoginId")
	public void checkLoginIdRepeat(Writer writer, String loginId) {
		PrintWriter out = new PrintWriter(writer);
		User user = userService.getUserByLoginId(loginId);
		if (loginId == null || "".equals(loginId) || loginId.length() < 6
				|| loginId.length() > 20) {
			out.print("{'fail':'用户名格式错误'}");
		} else if (user != null) {
			out.print("{'fail':'用户名重复'}");
		} else {
			out.print("{'success':'用户名正确'}");
		}
		out.flush();
		out.close();
	}

	@RequestMapping("/loginForm")
	public String loginForm(Model model) {
		if (!model.containsAttribute("loginUser")) {
			model.addAttribute("loginUser", new User());
		}
		return "fore/user-login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("currUser");
		return "redirect:/home/index";
	}

	@RequestMapping("/login")
	public String login(@Valid @ModelAttribute("loginUser") User loginUser,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession session) {
		System.out.println(loginUser.getType());
		if (result.hasErrors()) {
			System.out.println(result.getFieldErrors());
			return "forward:/user/loginForm";
		}
		System.out.println("controller User:"+loginUser);
		int code = userService.loginCheck(loginUser.getLoginId(),
				loginUser.getLoginPsw(), loginUser.getType());
		System.out.println("code*********:"+code);
		if (code == 5) {
			User currUser = userService
					.getUserByLoginId(loginUser.getLoginId());
			session.setAttribute("currUser", currUser);
			return "redirect:/home/index";
		}
		String errorMsg = null;
		if (code == 1) {
			errorMsg = "登录账号不存在";
		} else if (code == 2) {
			errorMsg = "登录密码不正确";
		} else if (code == 3) {
			errorMsg = "登录账号被禁用";
		} else if (code == 4) {
			errorMsg = "登录账号正在审核中，请联系客服";
		}
		model.addAttribute("errorMsg", errorMsg);
		return "forward:/user/loginForm";
	}

	@RequestMapping("/personalCenter")
	public String personalCenter(Integer pageNO, HttpSession session) {
		return "fore/personal-center";
	}

	@RequestMapping("/modifyPasswordForm")
	public String modifyPasswordForm() {
		return "fore/modify-psw";
	}

	@RequestMapping("/modifyPassword")
	public String modifyPassword(
			@RequestParam("original-psw") String originalPsw,
			@RequestParam("new-psw") String newPsw, HttpSession session,
			Model model) {

		User temp = (User) session.getAttribute("currUser");

		if (!temp.getLoginPsw().equals(originalPsw)) {
			model.addAttribute("errorMsg", "原始密码不正确，请重新输入");
			return "forward:/user/modifyPasswordForm";
		}

		temp.setLoginPsw(newPsw);
		userService.modifyUserInfoById(temp);
		session.setAttribute("currUser", null);
		return "redirect:/user/loginForm";
	}

	@RequestMapping("/modifyUserInfo")
	public void modifyUserInfo(User user, HttpSession session, Writer writer) {
		PrintWriter out = new PrintWriter(writer);
		User temp = (User) session.getAttribute("currUser");
		temp.setName(user.getName());
		temp.setEmail(user.getEmail());
		temp.setSex(user.getSex());
		temp.setIntroduction(user.getIntroduction());
		userService.modifyUserInfoById(temp);
		out.print(1);
		out.flush();
		out.close();
	}

	@RequestMapping("/modifyUserPhoto")
	public String modifyUserPhoto(HttpSession session, MultipartFile newPhoto,
			Model model) throws IllegalStateException, IOException {
		User user = (User) session.getAttribute("currUser");
		if (newPhoto.isEmpty()) {
			model.addAttribute("modiPhotoError", "请选择需要上传的文件!");
			return "forward:/user/personalCenter";
		}
		String fileExt = newPhoto.getOriginalFilename().substring(
				newPhoto.getOriginalFilename().lastIndexOf(".") + 1);
		if (!fileExt.equals("jpg") && !fileExt.equals("png")
				&& !fileExt.equals("gif") && !fileExt.equals("bmp")) {
			model.addAttribute("modiPhotoError", "文件格式不正确");
			return "forward:/user/personalCenter";
		}
		if (newPhoto.getSize() > 2 * 1024 * 1024) {
			model.addAttribute("modiPhotoError", "文件大小只能在2M以内");
			return "forward:/user/personalCenter";
		}
		String saveName = user.getPhoto();
		String oldExt = saveName.substring(saveName.lastIndexOf(".") + 1);
		/* 判断如果文件后缀名改变或原来的名字为default.jpg那么则要另外存图片 */
		if (!oldExt.equals(fileExt) || saveName.equals("default.jpg")) {
			saveName = new SimpleDateFormat("yyyyMMddHHssSSS")
					.format(new Date()) + "." + fileExt;
		}

		String realPath = session.getServletContext().getRealPath(
				"resource/photo/user/");
		user.setPhoto(saveName);
		userService.modifyUserInfoById(user);
		File saveFile = new File(realPath, saveName);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		newPhoto.transferTo(saveFile);
		return "forward:/user/personalCenter";
	}
}

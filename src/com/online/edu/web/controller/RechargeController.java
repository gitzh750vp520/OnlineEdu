package com.online.edu.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.edu.entity.Deal;
import com.online.edu.entity.User;
import com.online.edu.service.DealService;

@Controller
@RequestMapping("/recharge")
public class RechargeController extends BaseController{
	@Autowired
	private DealService dealService;
	@RequestMapping("/rechargeForm")
	public String rechargeForm(){
		return "fore/recharge";
	}
	@RequestMapping("/comfirm")
	public String recharge(HttpServletRequest request,Integer money,Model model){
		String uri = request.getRequestURI();
		System.out.println(uri);
		User user= (User) request.getSession().getAttribute("currUser");
		if(money==null){
			model.addAttribute("errorMsg", "必须指定金额参数");
			return "forward:/recharge/comfirm";
		}
		Deal deal = new Deal();
		deal.setGold(money);
		deal.setUser(user);
		deal.setType(false);
		dealService.addNewDeal(deal);
		return "redirect:/user/personalCenter";
	}
}

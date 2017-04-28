package com.online.edu.web.controller;

import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.edu.entity.Deal;
import com.online.edu.entity.User;
import com.online.edu.service.DealService;
import com.online.edu.web.tool.Paging;

@Controller
@RequestMapping("/deal")
public class DealController extends BaseController{
	@Autowired
	private DealService dealService;
	@RequestMapping("/history")
	public String dealForm(){
		return "fore/deal-list";
	}
	
	@RequestMapping("/showDeals")
	public void  showDeals(HttpSession session,String pageNOStr,Writer writer) throws JSONException, ParseException{
		PrintWriter out = new PrintWriter(writer);
		User user = (User) session.getAttribute("currUser");
		int pageSize =6;
		Integer count = dealService.getDealsByUserIdCount(user.getId());
		Paging paging = new Paging(count, pageNOStr, pageSize);
		List<Deal> deals = dealService.getDealsByUserId(paging.getPageNo(), pageSize, user.getId());
		Map<Integer, String> dateMap = new HashMap<Integer, String>();
		for (Deal deal : deals) {
			 dateMap.put(deal.getId(), new SimpleDateFormat("yyyy年MM月dd日  HH:mm.ss").format(deal.getTime()));
		}
		JSONObject data = new JSONObject();
		JSONArray array = new JSONArray(deals,false);
		JSONObject pagingData = new JSONObject(paging);
		data.put("paging", pagingData);
		data.put("deals", array);
		data.put("dealDates", new JSONObject(dateMap));
		out.print(data);
		out.flush();
		out.close();
	}
}

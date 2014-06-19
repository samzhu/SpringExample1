package com.mitake.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mitake.component.*;
import com.mitake.entity.User;
import com.mitake.service.*;

/**
 * 看板控制器
 * @author SAM
 *
 */
@Controller
@RequestMapping("rest")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/BOK508CService",method={RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> create(
			@Valid @RequestBody final Bok508rq bok508rq,//@Valid 將對此物件做基於註解形式的資料驗證
			BindingResult bindingResult//驗證結果
			){
		Map<String, Object> map = new HashMap<String, Object>();
		CommonHeader commonHeader = bok508rq.getRqheader();
		Bok508CRQBody bok508crqbody = bok508rq.getBok508crqbody();
		Bok508CRSBody bok508crsbody = new Bok508CRSBody();
		try {
			//資料驗證是否有錯
			if (bindingResult.hasErrors()) {
				commonHeader.setReturnCode("9999");
				commonHeader.setReturnMsg("資料驗證錯誤");
			}else{
				userService.create(bok508crqbody);
				commonHeader.setReturnCode("0000");
				commonHeader.setReturnMsg("交易成功");
			}
			map.put("rsheader", commonHeader);
			map.put("bok508crsbody", bok508crsbody);
			return map;
		} catch (Exception e) {
			commonHeader.setReturnCode("xxxx");
			commonHeader.setReturnMsg("無法預期錯誤");
			map.put("rsheader", commonHeader);
			map.put("bok508crsbody", bok508crsbody);
			return map;
		}
	}
}

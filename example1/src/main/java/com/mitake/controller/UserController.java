package com.mitake.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import joow.comm.HeaderLocal;
import joow.component.BoardCreate;
import joow.component.RqJson;
import joow.component.UserLogin;
import joow.customeditor.UserbasicEditor;
import joow.entity.Board;
import joow.entity.Userbasic;
import joow.entity.Userdetail;
import joow.hibernate.PageSeperator;
import joow.service.BoardService;
import joow.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 看板控制器
 * @author SAM
 *
 */
@Controller
@RequestMapping("board")
public class UserController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	
	/**
	 * 新增
	 * @param boardadd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/",method={RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> create(@RequestBody final BoardCreate boardcreate){
		//log.debug(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
		Map<String, Object> map = null;
		Board board = boardcreate.getBoard();
		HeaderLocal.set(boardcreate.getRqheader());
		try {
			boardService.create(board);
			map = new HashMap<String, Object>();
			map.put("prc", "1");
			map.put("msg", "新增成功");
			map.put("boardid", board.getBoardid());
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("", e);
			map = new HashMap<String, Object>();
			map.put("prc", "0");
			map.put("msg", "新增失敗("+e.getMessage()+")");
			return map;
		}
	}
	
	/**
	 * 取出單一筆文章
	 * @param boardid
	 * @param rqjson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{boardid}",method={RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> get(
			@PathVariable("boardid") Long boardid,
			@RequestBody final RqJson rqjson){
		//log.debug(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
		Map<String, Object> map = null;
		HeaderLocal.set(rqjson.getRqheader());
		Board board = null;
		try {
			board = boardService.find(boardid);
			map = new HashMap<String, Object>();
			map.put("prc", "1");
			map.put("msg", "新增成功");
			map.put("board", board);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("", e);
			map = new HashMap<String, Object>();
			map.put("prc", "0");
			map.put("msg", "讀取失敗("+e.getMessage()+")");
			return map;
		}
	}
	
	/**
	 * 尋找文章
	 * @param rqjson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/",method={RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> find(
			@RequestBody final RqJson rqjson){
		//log.debug(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
		Map<String, Object> map = null;
		HeaderLocal.set(rqjson.getRqheader());
		List<Board> boardlist = null;
		try {
			//board = boardService.find(boardid);
			map = new HashMap<String, Object>();
			map.put("prc", "1");
			map.put("msg", "讀取成功");
			map.put("boardlist", boardlist);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("", e);
			map = new HashMap<String, Object>();
			map.put("prc", "0");
			map.put("msg", "讀取失敗("+e.getMessage()+")");
			return map;
		}
	}
	
	/**
	 * 取出最新文章編號
	 * @param pagerows
	 * @param currentpage
	 * @return
	 */
	@ResponseBody
	@SuppressWarnings("finally")
	@RequestMapping(value="latest",method={RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public Map latest(
			@RequestParam(value="pagerows",required=false)Integer pagerows,
			@RequestParam(value="currentpage",required=false)Integer currentpage
			){
		//log.debug(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PageSeperator pageseperator = new PageSeperator();
			//pageseperator.setParams(params);
			pageseperator.setCurrentpage(currentpage);
			pageseperator.setPagerows(pagerows);
			//pageseperator.setSort(sort);
			//pageseperator.setOrder(order);
			//muserservice.query(pageseperator);
			map.put("prc", "1");
			map.put("msg", "搜尋成功");
			map.put("totalpages", pageseperator.getTotalpages());
			map.put("totalcount", pageseperator.getTotalcount());
			map.put("currentpage", pageseperator.getCurrentpage());
			map.put("pagerows", pageseperator.getPagerows());
			map.put("resultlist", pageseperator.getResultlist());
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("", e);
			map = new HashMap<String, Object>();
			map.put("prc", "0");
			map.put("msg", "註冊失敗("+e.getMessage()+")");
		}finally{
			return map;
		}
	}
	
	
}

package com.winter.app.regions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/regions/*")
public class RegionController {
	private RegionDAO regionDAO;
	
	public RegionController() {
		this.regionDAO = new RegionDAO();
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception{
	 String	id = request.getParameter("region_id");
	 String name = request.getParameter("region_name");
	 	
	 RegionDTO regionDTO = new  RegionDTO();
	 regionDTO.setRegion_id(Integer.parseInt(id));
	 regionDTO.setRegion_name(name);
	 int result = this.regionDAO.add(regionDTO);
	 
	 String msg ="등록 실패";
	 result=1;
	 if (result>0) {
		 msg= "등록 성공";
	 }else {
		
	 }
	 
		request.setAttribute("msg",msg);
		request.setAttribute("path", "./list");
		return "commons/result";
	}
	
	@RequestMapping(value="add",method= RequestMethod.POST )
	public String add() throws Exception{
		
		
		
		//WEB-INF//views/.jsp
		return "regions/add";
	}
	
	@RequestMapping(value="detail",method = RequestMethod.GET)
	public String detail(HttpServletRequest request)throws Exception{
		
		RegionDTO regionDTO = new RegionDTO();
		String id = request.getParameter("region_id");
		regionDTO.setRegion_id(Integer.parseInt(id));
		regionDTO = regionDAO.getDetail(regionDTO);
		
		request.setAttribute("dto", regionDTO);
		
		return "regions/detail";
	}
	
	@RequestMapping(value="list",method = RequestMethod.GET)
	public String list(HttpServletRequest request) throws Exception {
		System.out.println("Regions_List");
		
		List<RegionDTO> ar = regionDAO.getList();
		request.setAttribute("list", ar);    // 리퀘스트에 담아 보낸다  내장객체
		return "regions/list";
	}
	
	
	
	
	
	
	
}

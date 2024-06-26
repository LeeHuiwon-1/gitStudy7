package movie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.model.MovieDao;

@Controller
public class MovieDeleteController {

	private final String command = "/delete.mv";
	private final String gotoPage = "redirect:/list.mv";
	
	@Autowired
	MovieDao movieDao;
	
	@RequestMapping(command)
	public ModelAndView delete(
			@RequestParam("num") int num,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("whatColumn") String whatColumn,
//			@RequestParam String whatColumn,	// 굳이 value를 안써도 같은 이름으로 저절로 찾아줌.
			@RequestParam("keyword") String keyword		
			) {
		System.out.println("whatColumn : " + whatColumn);
		
		ModelAndView mav = new ModelAndView();
		
		movieDao.deleteMovie(num);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		
		mav.setViewName(gotoPage);
		
/* 
		 mav.addObject("pageNumber", pageNumber);
		 mav.setViewName(gotoPage);
		 return mav;
		 	
		gotoPage = "redirect:/list.mv"
		흠,, 
		아마도?
			
		return "redirect:/list.mv?pageNumber=pageNumber"
*/
		
		return mav;
	}
}

package movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.model.MovieBean;
import movie.model.MovieDao;
import utility.Paging;


@Controller
public class MovieListController {
	
	// http://localhost:8080/ex/list.mv
	private final String command = "/list.mv";
	private final String gotoPage = "movieList";
	
	@Autowired
	private MovieDao movieDao;


 	@RequestMapping(command)
	public ModelAndView list(
							@RequestParam(value="whatColumn", required = false) String whatColumn,
							@RequestParam(value="keyword", required = false) String keyword,
							@RequestParam(value="pageNumber", required = false) String pageNumber,
							HttpServletRequest request
							) {
 		
 		Map map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		
		int totalCount = movieDao.getTotalCount(map);
		String url = request.getContextPath() + this.command;
		
		Paging pageInfo = new Paging(pageNumber, null, totalCount,url, whatColumn, keyword);
		
		List<MovieBean> movieLists = movieDao.getMovieList(map,pageInfo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("movieLists", movieLists);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName(gotoPage);
		return mav;
	}
}

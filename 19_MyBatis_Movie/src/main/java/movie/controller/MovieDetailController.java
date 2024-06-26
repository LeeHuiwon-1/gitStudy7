package movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import movie.model.MovieBean;
import movie.model.MovieDao;


@Controller
public class MovieDetailController {

	private final String command = "/detail.mv";
	private final String getPage = "movieDetail";

	@Autowired
	@Qualifier("myMovieDao")
	MovieDao movieDao;
	
	@RequestMapping(command)
	public String detail(
						@RequestParam("num") int num,
						@RequestParam("pageNumber") int pageNumber,
						@RequestParam("whatColumn") String whatColumn,
						@RequestParam("keyword") String keyword,
						Model model) {
		
		MovieBean movie = movieDao.detailMovie(num);
		model.addAttribute("movie", movie);
		model.addAttribute("pageNumber", pageNumber);
//		model.addAttribute("whatColumn", whatColumn);
//		model.addAttribute("keyword", keyword);
		
/*
 		[MovieDetail.jsp]에서
 		속성설정 한 것 - pageNumber=${pageNumber} OR pageNumber=${requestScope.pageNumber}
 		속성설정 하지 않은 것 - pageNumber=${param.pageNumber}
*/			
		return getPage;
	}
}

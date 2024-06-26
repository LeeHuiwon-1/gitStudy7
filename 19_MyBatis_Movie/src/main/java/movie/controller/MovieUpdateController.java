package movie.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.model.MovieBean;
import movie.model.MovieDao;


@Controller
public class MovieUpdateController {

	private final String command = "/update.mv";
	private final String getPage = "movieUpdateForm";
	private final String gotoPage = "redirect:/list.mv";

	@Autowired
	MovieDao movieDao;

	@RequestMapping(value=command, method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam(value="num", required = true) int num, 
			@RequestParam(value="pageNumber", required = false) String pageNumber,
			@RequestParam(value="whatColumn", required = false) String whatColumn,
			@RequestParam(value="keyword", required = false) String keyword) {

		MovieBean movie = movieDao.detailMovie(num);
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movie);
		mav.setViewName(getPage);
		return mav;
	}

	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView update(
							@ModelAttribute("movie") @Valid MovieBean movie, BindingResult result,
							@RequestParam("pageNumber") int pageNumber,
							@RequestParam("whatColumn") String whatColumn,
							@RequestParam("keyword") String keyword
							) {

		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		movieDao.updateMovie(movie);
		
		mav.setViewName(gotoPage +"?pageNumber=" + pageNumber + "&whatColumn=" + whatColumn + "&keyword=" + keyword);
		return mav;
	}
}

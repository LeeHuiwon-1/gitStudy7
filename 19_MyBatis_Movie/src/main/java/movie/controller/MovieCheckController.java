package movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import movie.model.MovieDao;

@Controller
public class MovieCheckController {

	@Autowired
	MovieDao movieDao;

	private final String command = "/title_check_proc.mv";

	@RequestMapping(command)
	@ResponseBody		//나는 단지 문자열 리턴이지, .jsp 붙이지 않겠다. 라는 의미이다.
	public String check(@RequestParam(
					value="inputTitle",required = true) String inputTitle
									) {
		
		System.out.println("controller inputTitle : " + inputTitle);

		int cnt = movieDao.searchTitle(inputTitle);

		System.out.println("해당 id 갯수 : " + cnt);

		if(cnt == 0) {
			return "YES";
		}
		else {
			return "NO";
		}
	}
}

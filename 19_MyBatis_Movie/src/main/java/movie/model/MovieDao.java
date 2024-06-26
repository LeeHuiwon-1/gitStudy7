package movie.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import utility.Paging;


//@Service("myMovieDao")
//@Service, @Component 둘 다 똑같은 의미이다.

@Component("myMovieDao")
public class MovieDao {

	private String namespace = "movie.MovieBean";

	@Autowired  // travel.xml에서 생성한 객체를 주입
	SqlSessionTemplate sqlSessionTemplate;
	//	root-context 파일에 있다.


	public MovieDao() {
		System.out.println("MovieDao() 생성자 ");
	}


//	public List<MovieBean> getMovieList() {
	public List<MovieBean> getMovieList(Map<String, String> map, Paging pageInfo) {

		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		List<MovieBean> lists = new ArrayList<MovieBean>();

//		lists = sqlSessionTemplate.selectList(namespace + ".getMovieList");
		lists = sqlSessionTemplate.selectList(namespace + ".getMovieList", map, rowBounds);

		return lists;
	}


	public int getTotalCount(Map map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace + ".getTotalCount", map);
		System.out.println("getTotalCount cnt : " + cnt);
		return cnt;
	}


	public int insertMovie(MovieBean movie) {
		int cnt = -1;
		try {
			
			cnt = sqlSessionTemplate.insert(namespace + ".insertMovie", movie);
		
		}
		catch(DataAccessException e) {
			System.out.println("insert 에러발생");
			System.out.println(e.getMessage());
			System.out.println("insert cnt : " + cnt);
		}
		return cnt;
	}
	
	public int searchTitle(String inputTitle) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace + ".searchTitle", inputTitle);
		return cnt;
	}


	public MovieBean detailMovie(int num) {
		MovieBean movie = null;
		movie = sqlSessionTemplate.selectOne(namespace + ".detailMovie", num);
		return movie;
	}


	public int deleteMovie(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete(namespace + ".deleteMovie", num);
		System.out.println("delete cnt : " + cnt);
		return cnt;
	}


	public int updateMovie(MovieBean movie) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace + ".updateMovie", movie);
		System.out.println("updateMovie cnt:" + cnt);
		return cnt;
	}
	
	
}

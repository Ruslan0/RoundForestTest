package by.roundforest.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import by.roundforest.dao.impl.ReviewsDaoImpl;
import by.roundforest.dto.ViewBean;
import by.roundforest.dto.WordBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Beans.xml")
public class ReviewsDaoImplTest {

	@Autowired
	private ReviewsDao dao;
	
/*	@Before
	public void setUp(){
		dao = new ReviewsDaoImpl();
	}
*/	
	@Test
	public void testGetReviews() {
	    List<ViewBean> views = dao.getActiveUsers(1000);
        assertNotNull(views);
	    views.forEach((k) -> System.out.println(k.getProfileName()));

	    views = dao.getCommentedFoodItems(1000);
        assertNotNull(views);
	    views.forEach((k) -> System.out.println(k.getProductId()));
	    
	    List<WordBean> wordsSort = dao.getMostUsedWords(1000);
        assertNotNull(wordsSort);
	    wordsSort.forEach((k) -> System.out.println(k.getWord() + " " + k.getWeight()));
	}

	@Test
	public void testGetActiveUsers() {
	    List<ViewBean> views = dao.getActiveUsers(1000);
        assertNotNull(views);
	    views.forEach((k) -> System.out.println(k.getProfileName()));
	}

	@Test
	public void testGetCommentedFoodItems() {
		List<ViewBean> views = dao.getCommentedFoodItems(1000);
        assertNotNull(views);
	    views.forEach((k) -> System.out.println(k.getProductId()));
	}

	@Test
	public void testGetMostUsedWords() {
	    List<WordBean> wordsSort = dao.getMostUsedWords(1000);
        assertNotNull(wordsSort);
	    wordsSort.forEach((k) -> System.out.println(k.getWord() + " " + k.getWeight()));
	}

}

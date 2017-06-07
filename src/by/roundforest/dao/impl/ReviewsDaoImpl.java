package by.roundforest.dao.impl;

import by.roundforest.dao.ReviewsDao;
import by.roundforest.dto.ViewBean;
import by.roundforest.dto.WordBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;



@Component
public class ReviewsDaoImpl implements ReviewsDao {

  private final String sqlActiveUsers =  "select UserId, profileName, count(*) count from Reviews group by UserId order by 3 desc limit ?";
  private final String sqlCommentedFoodItems = "select ProductId, count(*) count from Reviews group by ProductId order by 2 desc limit ?";
  private final String sqlAll = "select Text from Reviews";
  private static JdbcTemplate jdbcTemplate = (JdbcTemplate)
      new ClassPathXmlApplicationContext("Beans.xml").getBean("jdbcTemplate");
    
  /**
   *  Finding count most active users (profile names)
   */  
  @Override
  public List<ViewBean> getActiveUsers(int count) {
    return jdbcTemplate.query(sqlActiveUsers,
       new Object[] {count},
       new BeanPropertyRowMapper<ViewBean>(ViewBean.class));
  }

  /**
   *  Finding count most commented food items (item ids).
   */  
  @Override
  public List<ViewBean> getCommentedFoodItems(int count) {
    return jdbcTemplate.query(sqlCommentedFoodItems,
      new Object[] {count},
      new BeanPropertyRowMapper<ViewBean>(ViewBean.class));
  }
  
  /**
   *  Finding count most used words in the reviews.
   */  
  @Override
  public List<WordBean> getMostUsedWords(int count) {

    Comparator<WordBean> comp = (WordBean o1, WordBean o2) -> (o1.compareToWord(o2));
    Comparator<WordBean> compWeight = (WordBean o1, WordBean o2) -> (o1.compareToWeight(o2));

    TreeSet<WordBean> wordsSet = 
        jdbcTemplate.query(sqlAll, new ResultSetExtractor<TreeSet<WordBean>>(){
          @Override
          public TreeSet<WordBean> extractData(ResultSet rs) throws SQLException, 
              DataAccessException {
            TreeSet<WordBean> wordBeanSet = new TreeSet<WordBean>(comp);
            while (rs.next()) {
              String rowDeatil = rs.getString(1);
                List<String> words =  Arrays.asList(rowDeatil.replaceAll("[^a-zA-Z]", " ").toLowerCase().split(" "));
                words.forEach(item -> wordBeanSet.add(new WordBean(item)));
            } 
            return wordBeanSet;
          }
        });
    List<WordBean> wordsSort = new ArrayList(Arrays.asList(wordsSet.stream().toArray()));
    Collections.sort(wordsSort, compWeight);

    return wordsSort.subList(0, count);
  }
}
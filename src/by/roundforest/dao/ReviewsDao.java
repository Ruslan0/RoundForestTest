package by.roundforest.dao;

import java.util.List;

import by.roundforest.dto.ViewBean;
import by.roundforest.dto.WordBean;

public interface ReviewsDao {
  
  List<ViewBean> getActiveUsers(int count);
  
  List<ViewBean> getCommentedFoodItems(int count);
  
  List<WordBean> getMostUsedWords(int count);
  
}
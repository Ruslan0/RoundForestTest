package by.roundforest.service;

import java.util.List;

import by.roundforest.dto.ViewBean;
import by.roundforest.dto.WordBean;

public interface Reviews {
	 List<ViewBean> getActiveUsers(int count);
	 List<ViewBean> getCommentedFoodItems(int count);
	 List <WordBean> getMostUsedWords(int count);
}
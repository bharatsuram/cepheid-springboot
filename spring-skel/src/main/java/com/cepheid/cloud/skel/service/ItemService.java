package com.cepheid.cloud.skel.service;

import java.util.List;

import com.cepheid.cloud.skel.dto.ItemDTO;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.State;

public interface ItemService {

	public void insertItem(ItemDTO itemDTO);

	public void deleteItem(Long id);

	public List<Item> fetchItems();

	public List<Item> fetchItemsByName(String Name);

	public List<Item> fetchItemsByState(State state);

	void updateItem(String name, Long id);

}

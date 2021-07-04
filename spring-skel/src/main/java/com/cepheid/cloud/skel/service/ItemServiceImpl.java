package com.cepheid.cloud.skel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepheid.cloud.skel.dto.ItemDTO;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.State;
import com.cepheid.cloud.skel.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepository;

	@Override
	public void insertItem(ItemDTO itemDTO) {
		// TODO Auto-generated method stub
		itemRepository.saveAndFlush(ItemDTO.prepareItem(itemDTO));
	}

	@Override
	public void updateItem(String name, Long id) {
		// TODO Auto-generated method stub
		Optional<Item> optionalItem = itemRepository.findById(id);
		if (optionalItem.isPresent()) {
			optionalItem.get().setName(name);
			itemRepository.save(optionalItem.get());
		}

	}

	@Override
	public void deleteItem(Long id) {
		// TODO Auto-generated method stub
		itemRepository.deleteById(id);
	}

	@Override
	public List<Item> fetchItems() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}

	@Override
	public List<Item> fetchItemsByName(String name) {
		// TODO Auto-generated method stub
		return itemRepository.findByName(name);
	}

	@Override
	public List<Item> fetchItemsByState(State state) {
		// TODO Auto-generated method stub
		return itemRepository.findByState(state);
	}

}

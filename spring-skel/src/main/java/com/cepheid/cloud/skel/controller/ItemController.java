package com.cepheid.cloud.skel.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cepheid.cloud.skel.exceptions.NoSuchItemException;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.State;
import com.cepheid.cloud.skel.repository.ItemRepository;

import io.swagger.annotations.Api;

// curl http:/localhost:9443/app/api/1.0/items

@Component
@Path("/api/1.0/items")
@Api()
public class ItemController {

	private final ItemRepository mItemRepository;

	@Autowired
	public ItemController(ItemRepository itemRepository) {
		mItemRepository = itemRepository;
	}

	@GetMapping(produces = "application/json")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<Item> getItems() {
		return mItemRepository.findAll();
	}

	@GetMapping(value = "/{name}", produces = "application/json")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<Item> getItemsByName(@PathVariable String name) {
		return mItemRepository.findByName(name);
	}

	@GetMapping(value = "/{state}", produces = "application/json")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<Item> getItemsByState(@PathVariable State state) {
		return mItemRepository.findByState(state);
	}

	@PutMapping(value = "/{id}{name}")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<Item> updateItem(@PathVariable String name, @PathVariable Long id)
			throws NoSuchItemException {
		// TODO Auto-generated method stub
		Optional<Item> optionalItem = mItemRepository.findById(id);
		if (!optionalItem.isPresent()) {
			throw new NoSuchItemException("Item id" + id + "Not found");
		}
		optionalItem.get().setName(name);
		mItemRepository.save(optionalItem.get());
		return ResponseEntity.ok().build();

	}

	@DeleteMapping("/{id}")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<Item> delete(@PathVariable Long id) throws NoSuchItemException {
		Optional<Item> optionalBook = mItemRepository.findById(id);
		if (!optionalBook.isPresent()) {
			throw new NoSuchItemException("Item id" + id + "Not found");
		}

		mItemRepository.delete(optionalBook.get());

		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes = "application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<Item> create(@RequestBody Item item) {

		Item newItem = mItemRepository.save(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newItem.getId())
				.toUri();

		return ResponseEntity.created(location).body(newItem);
	}

}

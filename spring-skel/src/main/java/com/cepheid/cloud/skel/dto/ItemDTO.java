package com.cepheid.cloud.skel.dto;

import java.util.ArrayList;
import java.util.List;

import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.State;

public class ItemDTO {

	private Long id;
	private String name;
	private State state;
	private int number;

	private List<DescriptionDTO> allDescriptions;

	public ItemDTO() {

	}

	public ItemDTO(Long id, String name, State state, int number) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<DescriptionDTO> getAllDescriptions() {
		return allDescriptions;
	}

	public void setAllDescriptions(List<DescriptionDTO> allDescriptions) {
		this.allDescriptions = allDescriptions;
	}

	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", name=" + name + ", state=" + state + ", number=" + number + ", allDescriptions="
				+ allDescriptions + "]";
	}

	public static Item prepareItem(ItemDTO itemDTO) {
		Item item = new Item(itemDTO.getName(), itemDTO.getState(), itemDTO.getNumber());
		item.setId(item.getId());
		List<Description> allDesc = new ArrayList<>();
		for (DescriptionDTO d : itemDTO.getAllDescriptions()) {
			Description desc = new Description(d.getTitle(), d.getDescription(), item);
			desc.setId(d.getId());
			allDesc.add(desc);
		}
		item.setAllDescriptions(allDesc);
		return item;
	}

}

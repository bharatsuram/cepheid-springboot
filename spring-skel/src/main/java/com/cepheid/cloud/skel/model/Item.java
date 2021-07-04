package com.cepheid.cloud.skel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.cepheid.cloud.skel.dto.DescriptionDTO;
import com.cepheid.cloud.skel.dto.ItemDTO;

@Entity
public class Item extends AbstractEntity {

	private String name;
	private State state;
	private int number;

	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Description> allDescriptions;

	public Item() {

	}

	public Item(String name, State state, int number) {
		this.name = name;
		this.state = state;
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Description> getAllDescriptions() {
		return allDescriptions;
	}

	public void setAllDescriptions(List<Description> allDescriptions) {
		this.allDescriptions = allDescriptions;
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

	@Override
	public String toString() {
		return "Item [name=" + name + ", state=" + state + ", number=" + number + ", allDescriptions=" + allDescriptions
				+ ", mId=" + mId + "]";
	}

	public static ItemDTO prepareItemDTO(Item item) {
		ItemDTO itemDTO = new ItemDTO(item.getId(), item.getName(), item.getState(), item.getNumber());
		List<DescriptionDTO> allDesc = new ArrayList<>();
		for (Description d : item.getAllDescriptions()) {
			DescriptionDTO desc = new DescriptionDTO(d.getId(), d.getTitle(), d.getDescription(), itemDTO);
			allDesc.add(desc);
		}
		itemDTO.setAllDescriptions(allDesc);
		return itemDTO;
	}

}

package com.cepheid.cloud.skel.dto;

import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Item;

public class DescriptionDTO {

	private Long id;
	private String title;
	private String description;
	private ItemDTO itemDTO;

	public DescriptionDTO() {

	}

	public DescriptionDTO(Long id, String title, String description, ItemDTO itemDTO) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.itemDTO = itemDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemDTO getItem() {
		return itemDTO;
	}

	public void setItem(ItemDTO item) {
		this.itemDTO = item;
	}

	public static Description prepareDescription(DescriptionDTO descriptionDTO) {
		Item item = ItemDTO.prepareItem(descriptionDTO.getItem());
		Description description = new Description(descriptionDTO.getTitle(), descriptionDTO.getDescription(), item);
		description.setId(descriptionDTO.getId());
		return description;
	}
}

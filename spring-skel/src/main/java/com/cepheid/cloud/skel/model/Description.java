package com.cepheid.cloud.skel.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cepheid.cloud.skel.dto.DescriptionDTO;
import com.cepheid.cloud.skel.dto.ItemDTO;

@Entity
public class Description extends AbstractEntity {

	private String title;
	private String description;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "item_mid", nullable = false)
	private Item item;

	public Description() {

	}

	public Description(String title, String description, Item item) {
		this.title = title;
		this.description = description;
		this.item = item;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Description [title=" + title + ", description=" + description + ", item=" + item + ", mId=" + mId + "]";
	}

	public static DescriptionDTO prepareDescriptionDTO(Description description) {
		ItemDTO itemDTO = Item.prepareItemDTO(description.getItem());
		DescriptionDTO descriptionDTO = new DescriptionDTO(description.getId(), description.getTitle(),
				description.getDescription(), itemDTO);
		return descriptionDTO;
	}

}

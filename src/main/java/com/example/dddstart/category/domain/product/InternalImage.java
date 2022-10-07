package com.example.dddstart.category.domain.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("II")
@Entity
public class InternalImage extends Image {

	protected InternalImage() {
	}

	public InternalImage(String path) {
		super(path);
	}

	@Override
	public String getUrl() {
		return "/images/original/" + getPath();
	}

	@Override
	public boolean hasThumbnail() {
		return true;
	}

	@Override
	public String getThumbnailUrl() {
		return "/images/thumbnail/" + getPath();
	}

}

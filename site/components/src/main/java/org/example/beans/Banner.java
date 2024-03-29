package org.example.beans;
/*
 * Copyright 2014-2019 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

@HippoEssentialsGenerated(internalName = "pkgext:bannerdocument")
@Node(jcrType = "pkgext:bannerdocument")
public class Banner extends BaseDocument {
	@HippoEssentialsGenerated(internalName = "pkgext:title")
	public String getTitle() {
		return getSingleProperty("pkgext:title");
	}

	@HippoEssentialsGenerated(internalName = "pkgext:content")
	public HippoHtml getContent() {
		return getHippoHtml("pkgext:content");
	}

	@HippoEssentialsGenerated(internalName = "pkgext:image")
	public HippoGalleryImageSet getImage() {
		return getLinkedBean("pkgext:image", HippoGalleryImageSet.class);
	}

	@HippoEssentialsGenerated(internalName = "pkgext:link")
	public HippoBean getLink() {
		return getLinkedBean("pkgext:link", HippoBean.class);
	}

    @HippoEssentialsGenerated(internalName = "pkgext:giphyimagepicker")
    public String[] getGiphyImages() {
	    return getBean("pkgext:giphyimagepicker", Giphy.class).getImages();
    }
}

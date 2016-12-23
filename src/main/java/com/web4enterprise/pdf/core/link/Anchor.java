/*
 * Copyright 2016 web4enterprise.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.web4enterprise.pdf.core.link;

import com.web4enterprise.pdf.core.Renderable;

/**
 * This interface is used internally.
 * Do not use. 
 * 
 * @author Régis Ramillien
 */
public interface Anchor {
	/**
	 * Add an anchor to a Renderable object.
	 * 
	 * @param destination The Renderable object to point link to.
	 */
	void setLink(Renderable destination);
	
	/**
	 * Get the link to renderable object.
	 * 
	 * @return the linked renderable.
	 */
	Renderable getLink();
}

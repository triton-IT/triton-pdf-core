/*
 * Copyright 2017 web4enterprise.
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
package com.web4enterprise.pdf.core.document;

/**
 * Represent a metadata of a document.
 * 
 * @author Régis Ramillien
 */
public class MetaData {
	/**
	 * The key of metadata.
	 */
	protected String key;
	/**
	 * The value of metadata.
	 */
	protected String value;
	
	/**
	 * Constructs a metadata with non null key and value.
	 * 
	 * @param key The key of metadata.
	 * @param value The value of metadata.
	 */
	public MetaData( String key, String value) {
		this.key = key;
		this.value = value;
	}
}

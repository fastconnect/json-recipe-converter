/*******************************************************************************
 * Copyright (c) 2013 GigaSpaces Technologies Ltd. All rights reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 ******************************************************************************/
package org.cloudifysource.cloudformation.converter.api;

import java.io.File;
import java.io.IOException;

/**
 * Abstract class for Cloudify template generation.
 * 
 * @author victor
 * @since 2.7.0
 */
public abstract class GroovyTemplateGenerator {

	private static final String TAB = "    ";

	protected StringBuilder builder = new StringBuilder();

	private int tabCounter = 0;

	/**
	 * Generate the template content.
	 * 
	 * @return The content of the template to be generated.
	 * @throws IOException
	 *             If an error occurs.
	 */
	public String generate() throws IOException {
		doGenerate();
		return builder.toString();
	}

	/**
	 * Method to be override to generate the template content.
	 * 
	 * @throws IOException
	 *             If an error occurs.
	 */
	abstract void doGenerate() throws IOException;

	abstract File generateFiles(File destinationFolder) throws IOException;

	/**
	 * Add a line with open brace.<br/>
	 * The line is of type:
	 * 
	 * <pre>
	 *     key {
	 * </pre>
	 * 
	 * @param key
	 *            a keyname
	 */
	protected void openBrace(final String key) {
		appendTab().append(key).append(" ").append("{\n");
		tabCounter++;
	}

	/**
	 * Add a line with a close brace.
	 */
	protected void closeBrace() {
		tabCounter--;
		appendTab().append("}\n");
	}

	/**
	 * Add a line with open array.<br/>
	 * The line is of type:
	 * 
	 * <pre>
	 *     key [
	 * </pre>
	 * 
	 * @param key
	 *            a keyname
	 */
	protected void openArray(final String key) {
		appendTab().append(key).append(" ").append("([\n");
		tabCounter++;
	}

	/**
	 * Add a line with a close array.
	 */
	protected void closeArray() {
		tabCounter--;
		appendTab().append("])\n");
	}

	/**
	 * Append tab.
	 * 
	 * @return The StringBuilder with the correct number of tabs.
	 */
	protected StringBuilder appendTab() {
		for (int i = 0; i < tabCounter; i++) {
			builder.append(TAB);
		}
		return builder;
	}
}

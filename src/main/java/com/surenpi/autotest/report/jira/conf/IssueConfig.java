/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.surenpi.autotest.report.jira.conf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author suren
 * @date 2017年6月28日 上午8:17:42
 */
public class IssueConfig
{
	private String assignee;
	private String priority;
	private String type;
	private List<String> labels;
	/**
	 * @param assignee
	 * @param labels
	 * @param priority
	 * @param type
	 */
	public IssueConfig(String assignee, String labelsText, String priority,
			String type)
	{
		this.assignee = assignee;
		setPriority(priority);
		
		setType(type);

		if(labelsText!= null)
		{
			this.labels = Arrays.asList(labelsText.split(" "));
		}
		else
		{
			this.labels = new ArrayList<String>(1);
			this.labels.add("phoenix_autotest");
		}
	}
	/**
	 * @return the assignee
	 */
	public String getAssignee()
	{
		return assignee;
	}
	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(String assignee)
	{
		this.assignee = assignee;
	}
	/**
	 * @return the labels
	 */
	public List<String> getLabels()
	{
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<String> labels)
	{
		this.labels = labels;
	}
	/**
	 * @return the priority
	 */
	public String getPriority()
	{
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority)
	{
		if(priority == null)
		{
			priority = "Low";
		}
		else
		{
			this.priority = priority;
		}
	}
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		if(type == null)
		{
			this.type = "任务";
		}
		else
		{
			this.type = type;
		}
	}
}

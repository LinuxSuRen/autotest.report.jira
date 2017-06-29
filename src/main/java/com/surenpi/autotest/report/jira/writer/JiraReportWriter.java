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

package com.surenpi.autotest.report.jira.writer;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.surenpi.autotest.report.RecordReportWriter;
import com.surenpi.autotest.report.jira.conf.IssueConfig;
import com.surenpi.autotest.report.record.ExceptionRecord;
import com.surenpi.autotest.report.record.NormalRecord;
import com.surenpi.autotest.report.record.ProjectRecord;

import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

/**
 * @author suren
 * @date 2017年6月27日 下午3:50:26
 */
public class JiraReportWriter implements RecordReportWriter
{
	@Autowired
	private JiraClient jiraClient;
	private String project;
	
	private IssueConfig issueConfig;
	
	public JiraReportWriter(IssueConfig issueConfig)
	{
		this.issueConfig = issueConfig;
	}

	public void write(ExceptionRecord exceptionRecord)
	{
		String description = exceptionRecord.getStackTraceText();
		String summary = exceptionRecord.getNormalRecord().getModuleName()
				+ "--" + exceptionRecord.getNormalRecord().getMethodName();
		
		try
		{
			Issue iessue = jiraClient.createIssue(project, issueConfig.getType())
					 .field(Field.DESCRIPTION, description)
					 .field(Field.SUMMARY, summary)
					 .field(Field.LABELS, issueConfig.getLabels())
					 .field(Field.ASSIGNEE, issueConfig.getAssignee())
					 .field(Field.PRIORITY, issueConfig.getPriority())	
					 .execute();
			
			List<File> attachFileList = exceptionRecord.getAttachFileList();
			if(attachFileList != null)
			{
				attachFileList.forEach((file) -> {
					try
					{
						iessue.addAttachment(file);
					}
					catch (JiraException e)
					{
						e.printStackTrace();
					}
				});
			}
		}
		catch (JiraException e)
		{
			e.printStackTrace();
		}
	}

	public void write(NormalRecord  normalRecord)
	{
	}

	public void write(ProjectRecord projectRecord)
	{
		project = projectRecord.getName();
	}

}

/*
 *
 *  * Copyright 2002-2007 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.surenpi.autotest.report.jira.writer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.surenpi.autotest.report.RecordReportWriter;
import com.surenpi.autotest.report.jira.conf.IssueConfig;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;

/**
 * @author suren
 */
@Configuration
@PropertySource("classpath:report.jira.properties")
public class JiraReportConfiguration
{
	@Value("${jira.url}")
	private String jiraUrl;
	@Value("${jira.username}")
	private String jiraUserName;
	@Value("${jira.password}")
	private String jiraPassword;
	
	@Value("${jira.assignee}")
	private String jiraAssignee;
	@Value("${jira.labels}")
	private String jiraLabels;
	@Value("${jira.priority}")
	private String jiraPriority;
	@Value("${jira.type}")
	private String jiraType;
	
	
	@Bean(name = "jiraReportWriter")
    public RecordReportWriter createWriter()
    {
		IssueConfig issueConfig = new IssueConfig(
				jiraAssignee, jiraLabels, jiraPriority, jiraType);
        return new JiraReportWriter(issueConfig);
    }

    @Bean
    public JiraClient createClient()
    {
		return new JiraClient(jiraUrl, new BasicCredentials(jiraUserName, jiraPassword));
    }
}

<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
    GroupDisplayContextHelper groupDisplayContextHelper = new GroupDisplayContextHelper(request);

    liveGroup = groupDisplayContextHelper.getLiveGroup();
    liveGroupId = groupDisplayContextHelper.getLiveGroupId();
    UnicodeProperties liveGroupTypeSettings = liveGroup.getTypeSettingsProperties();

    LayoutSet privateLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroup.getGroupId(), true);
    LayoutSet publicLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroup.getGroupId(), false);

    boolean liveGroupRemoteStaging = liveGroup.hasRemoteStagingGroup() && PropsValues.STAGING_LIVE_GROUP_REMOTE_STAGING_ENABLED;
    boolean stagedLocally = liveGroup.isStaged() && !liveGroup.isStagedRemotely();
    boolean stagedRemotely = liveGroup.isStaged() && !stagedLocally;

    if (stagedLocally) {
        stagingGroup = liveGroup.getStagingGroup();
        stagingGroupId = stagingGroup.getGroupId();
    }

    BackgroundTask lastCompletedInitialPublicationBackgroundTask = BackgroundTaskManagerUtil.fetchFirstBackgroundTask(liveGroupId, BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR, true, new BackgroundTaskCreateDateComparator(false));
%>

<c:choose>
    <c:when test="<%= GroupPermissionUtil.contains(permissionChecker, liveGroup, ActionKeys.MANAGE_STAGING) && GroupPermissionUtil.contains(permissionChecker, liveGroup, ActionKeys.VIEW_STAGING) %>">
        <c:if test="<%= liveGroupRemoteStaging %>">
            <liferay-staging:alert type="warning" dismissible="<%= true %>">
                <liferay-ui:message key="<%= LanguageUtil.get(request, "live-group-remote-staging-alert") %>" />
                <liferay-ui:message arguments='<%= "javascript:" + renderResponse.getNamespace() + "saveGroup(true);" %>' key="you-can-also-forcibly-disable-remote-staging" />
            </liferay-staging:alert>
        </c:if>

        <c:if test="<%= (lastCompletedInitialPublicationBackgroundTask != null) && (lastCompletedInitialPublicationBackgroundTask.getStatus() == BackgroundTaskConstants.STATUS_FAILED) %>">
            <liferay-staging:alert type="warning" dismissible="true">
                <liferay-ui:message key="an-unexpected-error-occurred-with-the-initial-staging-publication" />

                <portlet:actionURL name="deleteBackgroundTask" var="deleteBackgroundTaskURL">
                    <portlet:param name="redirect" value="<%= currentURL %>" />
                    <portlet:param name="backgroundTaskId" value="<%= String.valueOf(lastCompletedInitialPublicationBackgroundTask.getBackgroundTaskId()) %>" />
                </portlet:actionURL>

                <liferay-ui:icon-delete
                    confirmation="are-you-sure-you-want-to-remove-the-initial-staging-publication"
                    label="<%= true %>"
                    message="clear"
                    url="<%= deleteBackgroundTaskURL %>"
                />
            </liferay-staging:alert>

            <liferay-util:include page="/publish_process_message_task_details.jsp" servletContext="<%= application %>">
                <liferay-util:param name="backgroundTaskId" value="<%= String.valueOf(lastCompletedInitialPublicationBackgroundTask.getBackgroundTaskId()) %>" />
            </liferay-util:include>
        </c:if>

        <c:if test="<%= stagedLocally && (BackgroundTaskManagerUtil.getBackgroundTasksCount(liveGroupId, BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR, false) > 0) %>">
            <liferay-staging:alert type="warning">
                <liferay-ui:message key="an-inital-staging-publication-is-in-progress" />

                <a id="<portlet:namespace />publishProcessesLink"><liferay-ui:message key="the-status-of-the-publication-can-be-checked-on-the-publish-screen" /></a>
            </liferay-staging:alert>

            <aui:script>
                AUI.$('#<portlet:namespace />publishProcessesLink').on(
                'click',
                function(event) {
                Liferay.Util.openWindow(
                {
                id: 'publishProcesses',
                title: '<liferay-ui:message key="initial-publication" />',

                <liferay-portlet:renderURL portletName="<%= PortletKeys.EXPORT_IMPORT %>" var="publishProcessesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
                    <portlet:param name="mvcRenderCommandName" value="publishLayouts" />
                    <portlet:param name="<%= Constants.CMD %>" value="view_processes" />
                    <portlet:param name="<%= SearchContainer.DEFAULT_CUR_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_CUR_PARAM) %>" />
                    <portlet:param name="<%= SearchContainer.DEFAULT_DELTA_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_DELTA_PARAM) %>" />
                    <portlet:param name="groupId" value="<%= String.valueOf(stagingGroupId) %>" />
                    <portlet:param name="liveGroupId" value="<%= String.valueOf(liveGroupId) %>" />
                    <portlet:param name="localPublishing" value="<%= String.valueOf(stagedLocally) %>" />
                </liferay-portlet:renderURL>

                uri: '<%= HtmlUtil.escapeJS(publishProcessesURL.toString()) %>'
                }
                );
                }
                );
            </aui:script>
        </c:if>

        <liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="staging" />

        <c:if test="<%= privateLayoutSet.isLayoutSetPrototypeLinkActive() || publicLayoutSet.isLayoutSetPrototypeLinkActive() %>">
            <liferay-staging:alert type="warning">
                <liferay-ui:message key="staging-cannot-be-used-for-this-site-because-the-propagation-of-changes-from-the-site-template-is-enabled" />

                <c:choose>
                    <c:when test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.UNLINK_LAYOUT_SET_PROTOTYPE) %>">
                        <liferay-ui:message key="change-the-configuration-in-the-details-section" />
                    </c:when>
                    <c:otherwise>
                        <liferay-ui:message key="contact-your-administrator-to-change-the-configuration" />
                    </c:otherwise>
                </c:choose>
            </liferay-staging:alert>
        </c:if>

        <liferay-ui:error exception="<%= Exception.class %>">
            <liferay-ui:message key="an-unexpected-error-occurred-with-the-initial-staging-publication" />

            <%= errorException.toString() %>
        </liferay-ui:error>

        <liferay-ui:error exception="<%= LocaleException.class %>">

            <%
                LocaleException le = (LocaleException)errorException;
            %>

            <c:if test="<%= le.getType() == LocaleException.TYPE_EXPORT_IMPORT %>">
                <liferay-ui:message arguments="<%= new String[] {StringUtil.merge(le.getSourceAvailableLocales(), StringPool.COMMA_AND_SPACE), StringUtil.merge(le.getTargetAvailableLocales(), StringPool.COMMA_AND_SPACE)} %>" key="the-default-language-x-does-not-match-the-portal's-available-languages-x" translateArguments="<%= false %>" />
            </c:if>
        </liferay-ui:error>

        <liferay-ui:error exception="<%= SystemException.class %>">

            <%
                SystemException se = (SystemException)errorException;
            %>

            <liferay-ui:message key="<%= se.getMessage() %>" />
        </liferay-ui:error>

        <div class="container-fluid-960">
            <liferay-ui:success key="stagingDisabled" message="staging-is-successfully-disabled" />

            <portlet:actionURL name="editStagingConfiguration" var="editStagingConfigurationURL">
                <portlet:param name="mvcPath" value="/view.jsp" />
            </portlet:actionURL>

            <portlet:renderURL var="redirectURL">
                <portlet:param name="mvcRenderCommandName" value="staging" />
            </portlet:renderURL>

            <aui:form action="<%= editStagingConfigurationURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveGroup();" %>'>
                <aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />
                <aui:input name="groupId" type="hidden" value="<%= liveGroupId %>" />
                <aui:input name="liveGroupId" type="hidden" value="<%= liveGroupId %>" />
                <aui:input name="stagingGroupId" type="hidden" value="<%= stagingGroupId %>" />
                <aui:input name="forceDisable" type="hidden" value="<%= false %>" />

                <c:if test="<%= !privateLayoutSet.isLayoutSetPrototypeLinkActive() && !publicLayoutSet.isLayoutSetPrototypeLinkActive() %>">
                    <aui:fieldset-group markupView="lexicon">
                        <aui:fieldset>
                            <h1 class="staging-configuration-title"><%= LanguageUtil.get(request, "javax.portlet.title.com_liferay_staging_configuration_web_portlet_StagingConfigurationPortlet") %></h1>

                            <div><%= LanguageUtil.get(request, "select-one-of-the-options") %></div>

                            <div id="<portlet:namespace />stagingTypes">
                                <liferay-util:buffer var="noneText">
                                    <span class="staging-configuration-radio-label"><%= LanguageUtil.get(request, "none") %></span>
                                </liferay-util:buffer>
                                <aui:input checked="<%= !liveGroup.isStaged() %>" id="none" label="<%= noneText %>" name="stagingType" type="radio" value="<%= StagingConstants.TYPE_NOT_STAGED %>" cssClass="staging-configuration-radio" />

                                <c:if test="<%= !liveGroupRemoteStaging && !stagedRemotely %>">
                                    <liferay-util:buffer var="localLiveText">
                                        <span class="staging-configuration-radio-label"><%= LanguageUtil.get(request, "local-live") %>: <span class="staging-configuration-radio-help-message"><%= LanguageUtil.get(request, "staging-type-local") %></span></span>
                                    </liferay-util:buffer>
                                    <aui:input checked="<%= stagedLocally %>" id="local" label="<%= localLiveText %>" name="stagingType" type="radio" value="<%= StagingConstants.TYPE_LOCAL_STAGING %>" cssClass="staging-configuration-radio" />
                                </c:if>

                                <c:if test="<%= !stagedLocally %>">
                                    <liferay-util:buffer var="remoteLiveText">
                                        <span class="staging-configuration-radio-label"><%= LanguageUtil.get(request, "remote-live") %>: <span class="staging-configuration-radio-help-message"><%= LanguageUtil.get(request, "staging-type-remote") %></span></span>
                                    </liferay-util:buffer>
                                    <aui:input checked="<%= stagedRemotely %>" id="remote" label="<%= remoteLiveText %>" name="stagingType" type="radio" value="<%= StagingConstants.TYPE_REMOTE_STAGING %>" cssClass="staging-configuration-radio" />
                                </c:if>
                            </div>
                        </aui:fieldset>

                        <%
                            boolean showRemoteOptions = stagedRemotely;

                            int stagingType = ParamUtil.getInteger(request, "stagingType");

                            if (stagingType == StagingConstants.TYPE_REMOTE_STAGING) {
                                showRemoteOptions = true;
                            }
                        %>

                        <div class="<%= showRemoteOptions ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />remoteStagingOptions">
                            <%@ include file="/error_auth_exception.jspf" %>

                            <%@ include file="/error_remote_export_exception.jspf" %>

                            <aui:fieldset cssClass="staging-configuration-fieldset-title">
                                <div class="staging-configuration-section-title-holder">
                                    <span class="staging-configuration-section-title"><%= LanguageUtil.get(request, "remote-live-connection-settings" )%></span>
                                </div>

                                <%@ include file="/error_remote_options_exception.jspf" %>

                                <liferay-staging:alert type="info">
                                    <liferay-ui:message key="remote-publish-help" />
                                </liferay-staging:alert>

                                <aui:input label="remote-host-ip" name="remoteAddress" size="20" type="text" value='<%= liveGroupTypeSettings.getProperty("remoteAddress") %>' />

                                <aui:input label="remote-port" name="remotePort" size="10" type="text" value='<%= liveGroupTypeSettings.getProperty("remotePort") %>' cssClass="remote-port" />

                                <aui:input label="remote-path-context" name="remotePathContext" size="10" type="text" value='<%= liveGroupTypeSettings.getProperty("remotePathContext") %>' />

                                <aui:input label='<%= LanguageUtil.get(request, "remote-site-id") %>' name="remoteGroupId" size="10" type="text" value='<%= liveGroupTypeSettings.getProperty("remoteGroupId") %>' />

                                <div class="flex-container">

                                    <%
                                        boolean secureConnection = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("secureConnection"));
                                    %>
                                    <aui:input label="use-a-secure-network-connection" name="secureConnection" type="checkbox" value="<%= secureConnection %>" cssClass="staging-configuration-checkbox" />
                                </div>
                            </aui:fieldset>
                        </div>

                        <div class="<%= ((liveGroup.isStaged() || (stagingType != StagingConstants.TYPE_NOT_STAGED)) ? StringPool.BLANK : "hide") %>" id="<portlet:namespace />stagedPortlets">
                            <c:if test="<%= !liveGroup.isCompany() && !liveGroupRemoteStaging %>">
                                <aui:fieldset>
                                    <div class="staging-configuration-section-title-holder">
                                        <span class="staging-configuration-section-title"><%= LanguageUtil.get(request, "page-versioning" )%></span>
                                    </div>

                                    <p class="staging-configuration-help-label">
                                        <liferay-ui:message key="page-versioning-help" />
                                    </p>

                                    <%
                                        boolean branchingPublic = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("branchingPublic"));
                                    %>
                                    <aui:input label="enabled-on-public-pages" name="branchingPublic" type="checkbox" value="<%= branchingPublic %>" cssClass="staging-configuration-checkbox" />
                                    <%
                                        boolean branchingPrivate = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("branchingPrivate"));
                                    %>
                                    <aui:input label="enabled-on-private-pages" name="branchingPrivate" type="checkbox" value="<%= branchingPrivate %>" cssClass="staging-configuration-checkbox" />
                                </aui:fieldset>
                            </c:if>

                            <aui:fieldset markupView="lexicon">
                                <div class="staging-configuration-section-title-holder">
                                    <span class="staging-configuration-section-title"><%= LanguageUtil.get(request, "staged-content" )%></span>
                                    <span class="staging-configuration-icon-holder">
                                        <svg aria-hidden="true" class="lexicon-icon lexicon-icon-question-circle-full">
                                            <use xlink:href="<%=themeDisplay.getPathThemeImages()%>/lexicon/icons.svg#question-circle-full" />
                                        </svg>
                                    </span>
                                    <div class="popover-wrapper">
                                        <div class="arrow"></div>
                                        <span class="popover bs-popover-right">
                                                <div class="inline-scroller">
                                                <div class="popover-header"><%= LanguageUtil.get(request, "staged-content") %></div>
                                                    <div class="popover-body">
                                                        <p><%= LanguageUtil.get(request, "staged-portlets-help") %></p>
                                                    </div>
                                                </div>
                                            </span>
                                    </div>

                                    <aui:script use="aui-base, aui-popover">
                                        var icon = A.one(".staging-configuration-section-title-holder > .staging-configuration-icon-holder");
                                        var arrow = A.one(".staging-configuration-section-title-holder > .popover-wrapper > .arrow");
                                        var popover = A.one(".staging-configuration-section-title-holder > .popover-wrapper > span.popover");
                                        var popoverLeft = -(icon.getDOMNode().getBoundingClientRect().right - (popover.getStyle("width").slice(0, -2) / 2) - 23);
                                        var popoverTop = -(popover.getStyle("height").slice(0, -2) / 2);
                                        popover.setStyle('top', popoverTop);
                                        popover.setStyle('left', popoverLeft);
                                        arrow.setStyle('left', popoverLeft - 3);
                                    </aui:script>
                                </div>

                                <div id="<portlet:namespace />trashWarning">
                                    <c:if test="<%= TrashEntryLocalServiceUtil.getEntriesCount(liveGroup.getGroupId()) > 0 %>">
                                        <liferay-staging:alert type="warning">
                                            <liferay-ui:message key="local-staging-trash-warning" />
                                        </liferay-staging:alert>
                                    </c:if>
                                </div>

                                <p class="staging-configuration-help-label">
                                    <liferay-ui:message key="staged-portlets-alert" />
                                </p>

                                <aui:input disabled="<%= (liveGroupRemoteStaging && liveGroup.isStagedRemotely()) || liveGroup.isStaged() %>" id="selectAllCheckbox" label="select-all" name="selectAll" type="checkbox" cssClass="staging-configuration-checkbox select-all-checkbox"  />

                                <div id="stagingConfigurationControls">
                                    <%
                                        Set<String> portletDataHandlerClassNames = new HashSet<String>();

                                        List<Portlet> dataSiteLevelPortlets = ExportImportHelperUtil.getDataSiteLevelPortlets(company.getCompanyId(), true);

                                        dataSiteLevelPortlets = ListUtil.sort(dataSiteLevelPortlets, new PortletTitleComparator(application, locale));

                                        for (Portlet curPortlet : dataSiteLevelPortlets) {
                                            PortletDataHandler portletDataHandler = curPortlet.getPortletDataHandlerInstance();

                                            Class<?> portletDataHandlerClass = portletDataHandler.getClass();

                                            String portletDataHandlerClassName = portletDataHandlerClass.getName();

                                            if (!portletDataHandlerClassNames.contains(portletDataHandlerClassName)) {
                                                portletDataHandlerClassNames.add(portletDataHandlerClassName);
                                            }
                                            else {
                                                continue;
                                            }

                                            boolean staged = portletDataHandler.isPublishToLiveByDefault();

                                            if (stagingGroup != null) {
                                                staged = stagingGroup.isStagedPortlet(StagingUtil.getStagedPortletId(curPortlet.getRootPortletId()));
                                            }
                                    %>

                                    <aui:input disabled="<%= (liveGroupRemoteStaging && liveGroup.isStagedRemotely()) || liveGroup.isStaged() %>" label="<%= PortalUtil.getPortletTitle(curPortlet, application, locale) %>" name="<%= StagingConstants.STAGED_PREFIX + StagingUtil.getStagedPortletId(curPortlet.getRootPortletId()) + StringPool.DOUBLE_DASH %>" type="checkbox" value="<%= staged %>" cssClass="staging-configuration-checkbox" />

                                    <%
                                        }
                                    %>
                                </div>
                            </aui:fieldset>
                        </div>

                        <div class="staging-configuration-submit-button-holder">
                            <button class="btn btn-primary"><span class="lfr-btn-label"><%= LanguageUtil.get(request, "save")%></span></button>
                        </div>
                    </aui:fieldset-group>

                    <aui:script sandbox="<%= true %>">
                        var remoteStagingOptions = $('#<portlet:namespace />remoteStagingOptions');
                        var stagedPortlets = $('#<portlet:namespace />stagedPortlets');
                        var trashWarning = $('#<portlet:namespace />trashWarning');

                        var stagingTypes = $('#<portlet:namespace />stagingTypes');

                        stagingTypes.on(
                        'click',
                        'input',
                        function(event) {
                        var value = $(event.currentTarget).val();

                        stagedPortlets.toggleClass('hide', value == '<%= StagingConstants.TYPE_NOT_STAGED %>');

                        remoteStagingOptions.toggleClass('hide', value != '<%= StagingConstants.TYPE_REMOTE_STAGING %>');

                        trashWarning.toggleClass('hide', value != '<%= StagingConstants.TYPE_LOCAL_STAGING %>');
                        }
                        );
                    </aui:script>

                </c:if>
            </aui:form>
        </div>
    </c:when>
    <c:otherwise>
        <liferay-staging:alert type="info">
            <liferay-ui:message key="you-do-not-have-permission-to-manage-settings-related-to-staging" />
        </liferay-staging:alert>
    </c:otherwise>
</c:choose>

<aui:script>
    function <portlet:namespace />saveGroup(forceDisable) {
    var $ = AUI.$;

    var form = $(document.<portlet:namespace />fm);

    var ok = true;

    <c:if test="<%= liveGroup != null %>">
        var stagingTypeEl = $('input[name=<portlet:namespace />stagingType]:checked');

        var oldValue;

        <c:choose>
            <c:when test="<%= liveGroup.isStaged() && !liveGroup.isStagedRemotely() %>">
                oldValue = <%= StagingConstants.TYPE_LOCAL_STAGING %>;
            </c:when>
            <c:when test="<%= liveGroup.isStaged() && liveGroup.isStagedRemotely() %>">
                oldValue = <%= StagingConstants.TYPE_REMOTE_STAGING %>;
            </c:when>
            <c:otherwise>
                oldValue = <%= StagingConstants.TYPE_NOT_STAGED %>;
            </c:otherwise>
        </c:choose>

        var currentValue = stagingTypeEl.val();

        if (stagingTypeEl.length && (currentValue != oldValue)) {
        ok = false;

        if (currentValue == <%= StagingConstants.TYPE_NOT_STAGED %>) {
        ok = confirm('<%= UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-deactivate-staging-for-x", liveGroup.getDescriptiveName(locale), false) %>');
        }
        else if (currentValue == <%= StagingConstants.TYPE_LOCAL_STAGING %>) {
        ok = confirm('<%= UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-activate-local-staging-for-x", liveGroup.getDescriptiveName(locale), false) %>');
        }
        else if (currentValue == <%= StagingConstants.TYPE_REMOTE_STAGING %>) {
        ok = confirm('<%= UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-activate-remote-staging-for-x", liveGroup.getDescriptiveName(locale), false) %>');
        }
        }
    </c:if>

    if (ok) {
    if (forceDisable) {
    form.fm('forceDisable').val(true);
    form.fm('local').prop('checked', false);
    form.fm('none').prop('checked', true);
    form.fm('redirect').val('<portlet:renderURL><portlet:param name="mvcPath" value="/view.jsp" /><portlet:param name="historyKey" value='<%= renderResponse.getNamespace() + "staging" %>' /></portlet:renderURL>');
    form.fm('remote').prop('checked', false);
    }

    submitForm(form);
    }
    }
</aui:script>

<aui:script sandbox="<%= true %>">
    var stagingConfigurationControls = $('#stagingConfigurationControls');

    var allCheckboxes = stagingConfigurationControls.find('input[type=checkbox]');

    $('#<portlet:namespace />selectAllCheckbox').on(
    'change',
    function() {
    allCheckboxes.prop('checked', this.checked);
    }
    );
</aui:script>
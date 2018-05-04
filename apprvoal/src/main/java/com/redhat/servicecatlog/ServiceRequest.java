package com.redhat.servicecatlog;

/**
 * This class was automatically generated by the data modeler tool.
 */

@org.kie.api.definition.type.Label("ServiceRequest")
public class ServiceRequest implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   @org.kie.api.definition.type.Label("Requester")
   private java.lang.String requester;

   @org.kie.api.definition.type.Label("Request Item")
   private com.redhat.servicecatlog.RequestItem item;

   @org.kie.api.definition.type.Label("Service Catalog Name")
   private java.lang.String serviceName;

   @org.kie.api.definition.type.Label("User Approval Label")
   private java.lang.String userLabel;

   @org.kie.api.definition.type.Label("Project Approval Label")
   private java.lang.String projectLabel;

   @org.kie.api.definition.type.Label("Service Item Approval Label")
   private java.lang.String serviceLabel;

   @org.kie.api.definition.type.Label("Approval Method")
   private java.lang.String approvalMethod;

   @org.kie.api.definition.type.Label("Final Approval Status")
   private java.lang.String approvalStatus;

   @org.kie.api.definition.type.Label("List of Approvers")
   private java.util.List<com.redhat.servicecatlog.Approver> approvers;

   @org.kie.api.definition.type.Label("Indicates current approver")
   private int activeIndex;

   @org.kie.api.definition.type.Label("Quota Status")
   private java.lang.String quotaStatus;

   @org.kie.api.definition.type.Label("Quota Approver")
   private com.redhat.servicecatlog.Approver quotaApprover;

   @org.kie.api.definition.type.Label("Project Name")
   private java.lang.String project;

   @org.kie.api.definition.type.Label(value = "Quota Failure Message")
   private java.lang.String quotaErrorMsg;

   public ServiceRequest()
   {
   }

   public java.lang.String getRequester()
   {
      return this.requester;
   }

   public void setRequester(java.lang.String requester)
   {
      this.requester = requester;
   }

   public com.redhat.servicecatlog.RequestItem getItem()
   {
      return this.item;
   }

   public void setItem(com.redhat.servicecatlog.RequestItem item)
   {
      this.item = item;
   }

   public java.lang.String getServiceName()
   {
      return this.serviceName;
   }

   public void setServiceName(java.lang.String serviceName)
   {
      this.serviceName = serviceName;
   }

   public java.lang.String getUserLabel()
   {
      return this.userLabel;
   }

   public void setUserLabel(java.lang.String userLabel)
   {
      this.userLabel = userLabel;
   }

   public java.lang.String getProjectLabel()
   {
      return this.projectLabel;
   }

   public void setProjectLabel(java.lang.String projectLabel)
   {
      this.projectLabel = projectLabel;
   }

   public java.lang.String getServiceLabel()
   {
      return this.serviceLabel;
   }

   public void setServiceLabel(java.lang.String serviceLabel)
   {
      this.serviceLabel = serviceLabel;
   }

   public java.lang.String getApprovalMethod()
   {
      return this.approvalMethod;
   }

   public void setApprovalMethod(java.lang.String approvalMethod)
   {
      this.approvalMethod = approvalMethod;
   }

   public java.lang.String getApprovalStatus()
   {
      return this.approvalStatus;
   }

   public void setApprovalStatus(java.lang.String approvalStatus)
   {
      this.approvalStatus = approvalStatus;
   }

   public java.util.List<com.redhat.servicecatlog.Approver> getApprovers()
   {
      return this.approvers;
   }

   public void setApprovers(
         java.util.List<com.redhat.servicecatlog.Approver> approvers)
   {
      this.approvers = approvers;
   }

   public int getActiveIndex()
   {
      return this.activeIndex;
   }

   public void setActiveIndex(int activeIndex)
   {
      this.activeIndex = activeIndex;
   }

   public java.lang.String getQuotaStatus()
   {
      return this.quotaStatus;
   }

   public void setQuotaStatus(java.lang.String quotaStatus)
   {
      this.quotaStatus = quotaStatus;
   }

   public com.redhat.servicecatlog.Approver getQuotaApprover()
   {
      return this.quotaApprover;
   }

   public void setQuotaApprover(com.redhat.servicecatlog.Approver quotaApprover)
   {
      this.quotaApprover = quotaApprover;
   }

   public java.lang.String getProject()
   {
      return this.project;
   }

   public void setProject(java.lang.String project)
   {
      this.project = project;
   }

   public java.lang.String getQuotaErrorMsg()
   {
      return this.quotaErrorMsg;
   }

   public void setQuotaErrorMsg(java.lang.String quotaErrorMsg)
   {
      this.quotaErrorMsg = quotaErrorMsg;
   }

   public ServiceRequest(java.lang.String requester,
         com.redhat.servicecatlog.RequestItem item,
         java.lang.String serviceName, java.lang.String userLabel,
         java.lang.String projectLabel, java.lang.String serviceLabel,
         java.lang.String approvalMethod, java.lang.String approvalStatus,
         java.util.List<com.redhat.servicecatlog.Approver> approvers,
         int activeIndex, java.lang.String quotaStatus,
         com.redhat.servicecatlog.Approver quotaApprover,
         java.lang.String project, java.lang.String quotaErrorMsg)
   {
      this.requester = requester;
      this.item = item;
      this.serviceName = serviceName;
      this.userLabel = userLabel;
      this.projectLabel = projectLabel;
      this.serviceLabel = serviceLabel;
      this.approvalMethod = approvalMethod;
      this.approvalStatus = approvalStatus;
      this.approvers = approvers;
      this.activeIndex = activeIndex;
      this.quotaStatus = quotaStatus;
      this.quotaApprover = quotaApprover;
      this.project = project;
      this.quotaErrorMsg = quotaErrorMsg;
   }

}
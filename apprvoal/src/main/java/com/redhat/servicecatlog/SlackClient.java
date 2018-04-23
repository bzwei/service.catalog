package com.redhat.servicecatlog;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class SlackClient implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   @org.kie.api.definition.type.Label("Slack Web Hook URL")
   private java.lang.String slackAddress;
   @org.kie.api.definition.type.Label("Slack Channel or User")
   private java.lang.String slackChannel;
   @org.kie.api.definition.type.Label("Service Request")
   private com.redhat.servicecatlog.ServiceRequest serviceRequest;

   @org.kie.api.definition.type.Label("Approval Status")
   private java.lang.String approvalStatus;

   @org.kie.api.definition.type.Label(value = "Approval ID")
   private java.lang.String approvalID;

   public SlackClient()
   {
   }

   public java.lang.String getSlackAddress()
   {
      return this.slackAddress;
   }

   public void setSlackAddress(java.lang.String slackAddress)
   {
      this.slackAddress = slackAddress;
   }

   public java.lang.String getSlackChannel()
   {
      return this.slackChannel;
   }

   public void setSlackChannel(java.lang.String slackChannel)
   {
      this.slackChannel = slackChannel;
   }

   public com.redhat.servicecatlog.ServiceRequest getServiceRequest()
   {
      return this.serviceRequest;
   }

   public void setServiceRequest(
         com.redhat.servicecatlog.ServiceRequest serviceRequest)
   {
      this.serviceRequest = serviceRequest;
   }

   public java.lang.String getApprovalStatus()
   {
      return this.approvalStatus;
   }

   public void setApprovalStatus(java.lang.String approvalStatus)
   {
      this.approvalStatus = approvalStatus;
   }

   public SlackClient(java.lang.String slackAddress,
         java.lang.String slackChannel,
         com.redhat.servicecatlog.ServiceRequest serviceRequest,
         java.lang.String approvalStatus)
   {
      this.slackAddress = slackAddress;
      this.slackChannel = slackChannel;
      this.serviceRequest = serviceRequest;
      this.approvalStatus = approvalStatus;
   }

    public void notifySlack() {
		approvalId = catalog + java.util.UUID.randomUUID();
		String title = String.format("\"%s want to order from catalog %s for %s.\"", requester, catalog, item);
		String body = String.join("\n"
				, "{"
				, "    \"text\": " + title + ","
				, "    \"attachments\":[{"
				, "        \"text\": \"Do you want to approve the request?\","
				, "        \"fallback\": \"Shame... buttons aren't supported in this land\","
				, "        \"callback_id\": \"" + approvalId + "\","
				, "        \"color\": \"#3AA3E3\","
				, "        \"attachment_type\": \"default\","
				, "        \"actions\": ["
				, "        {"
				, "            \"name\": \"yes\","
				, "            \"text\": \"Approve\","
				, "            \"type\": \"button\","
				, "            \"value\": \"yes\""
				, "        },"
				, "        {"
				, "            \"name\": \"no\","
				, "            \"text\": \"Deny\","
				, "            \"type\": \"button\","
				, "            \"value\": \"no\""
				, "        }]"
				, "    }]"
				, "}"
		);
		
		java.net.URL url = new java.net.URL(slackAddress);
		java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		java.io.OutputStream os = conn.getOutputStream();
		os.write(body.getBytes());
		os.flush();
		
		int responseCode = conn.getResponseCode();
        if (responseCode!= java.net.HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed to send message to slack : " + responseCode);
        }
        conn.disconnect();        
    }
    
    public void waitForAction() {
        String pollAddress = "http://nodejs-ex-slackapproval.7e14.starter-us-west-2.openshiftapps.com/slack/approval";
        pollAddress += "?requestId=" + approvalId;
        String url = new java.net.URL(pollAddress);
        boolean finished = false;
        for (int x = 0; x < 100; x++) {
          java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
          conn.setRequestMethod("GET");
          int responseCode = conn.getResponseCode();
          if (responseCode != java.net.HttpURLConnection.HTTP_OK) {
        	    throw new RuntimeException("Failed to poll approval status from http server" + responseCode);
          }
          java.io.BufferedReader in = new java.io.BufferedReader(
                  new java.io.InputStreamReader(conn.getInputStream()));
          String inputLine;
          StringBuffer response = new StringBuffer();
          while ((inputLine = in.readLine()) != null) {
          	response.append(inputLine);
          }
          in.close();
          //print in String
          System.out.println(response.toString());
          //Read JSON response and print
          org.json.JSONObject jsonResponse = new org.json.JSONObject(response.toString());
          String probeApprovalStatus = jsonResponse.getString("approvalStatus");
          if (probeApprovalStatus.equals("Denied") || probeApprovalStatus.equals("Approved")) {
        	    System.out.println(slackChannel + " approval Status: " + probApprovalStatus);
        	    finished = true;
        	    approvalStatus = probeApprovalStatus;
        	    break;
          }
          Thread.sleep(5000);
        }
        if (!finished) {
        	  throw new RuntimeException("Timeout waiting for user approval");
        }         
    }
}
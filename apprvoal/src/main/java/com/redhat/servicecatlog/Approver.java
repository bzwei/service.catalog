package com.redhat.servicecatlog;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class Approver implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   @org.kie.api.definition.type.Label("Slack URL")
   private java.lang.String slackAddress;
   @org.kie.api.definition.type.Label("Name of Approver")
   private java.lang.String name;
   @org.kie.api.definition.type.Label("Approval Status")
   private java.lang.String status;

   @org.kie.api.definition.type.Label(value = "When it is notified")
   private java.lang.String notifyTime;

   @org.kie.api.definition.type.Label(value = "When it is approved")
   private java.lang.String actionTime;
   
   private java.lang.String approvalId;

   public Approver()
   {
   }
   
   public void notifySlack(String idPrefix, String message) throws Exception
   {
		this.approvalId = idPrefix + java.util.UUID.randomUUID();

		String body = String.join("\n"
				, "{"
				, "    \"text\": " + message + ","
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
        this.status = "Notified";
        this.notifyTime = java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC).format(java.time.format.DateTimeFormatter.ISO_INSTANT);
   }
   
   public void waitForAction(String pollAddress) throws Exception
   {
	   if (pollAddress == null) {
         pollAddress = "http://nodejs-ex-slackapproval.7e14.starter-us-west-2.openshiftapps.com/slack/approval";
	   }
       pollAddress += "?requestId=" + approvalId;
       java.net.URL url = new java.net.URL(pollAddress);
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
       	    System.out.println(this.name + " action: " + probeApprovalStatus);
       	    finished = true;
       	    this.actionTime = java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC).format(java.time.format.DateTimeFormatter.ISO_INSTANT);
       	    this.status = probeApprovalStatus;
       	    break;
         }
         Thread.sleep(5000);
       }
       if (!finished) {
       	  throw new RuntimeException("Timeout waiting for user approval");
       } 
   }

   public java.lang.String getSlackAddress()
   {
      return this.slackAddress;
   }

   public void setSlackAddress(java.lang.String slackAddress)
   {
      this.slackAddress = slackAddress;
   }

   public java.lang.String getName()
   {
      return this.name;
   }

   public void setName(java.lang.String name)
   {
      this.name = name;
   }

   public java.lang.String getStatus()
   {
      return this.status;
   }

   public void setStatus(java.lang.String status)
   {
      this.status = status;
   }

   public java.lang.String getNotifyTime()
   {
      return this.notifyTime;
   }

   public void setNotifyTime(java.lang.String notifyTime)
   {
      this.notifyTime = notifyTime;
   }

   public java.lang.String getActionTime()
   {
      return this.actionTime;
   }

   public void setActionTime(java.lang.String actionTime)
   {
      this.actionTime = actionTime;
   }

   public Approver(java.lang.String slackAddress, java.lang.String name,
         java.lang.String status, java.lang.String notifyTime,
         java.lang.String actionTime)
   {
      this.slackAddress = slackAddress;
      this.name = name;
      this.status = status;
      this.notifyTime = notifyTime;
      this.actionTime = actionTime;
   }
}
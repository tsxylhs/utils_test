package org.example;

import com.azure.identity.AzureAuthorityHosts;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.models.*;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.kiota.RequestAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;

public class EmailClientTest {

        String resourceId = "";
           private static String serviceRoot = "https://microsoftgraph.chinacloudapi.cn/v1.0";
//        final String[] scopes = new String[]{resourceId};
            private static String authority = "https://login.chinacloudapi.cn/a07axx5fxx5d-2xx29xxxx1-4551-9c0e-5b98ax8a5dffd/";
            private static String accessToken = "";


    public static void main(String[] args) throws Exception {
        final String[] scopes = new String[] { "https://microsoftgraph.chinacloudapi.cn/.default" };
        String clientId = "";
        String tenantId = "";
        String clientSecret = "";
        final ClientSecretCredential credential = new ClientSecretCredentialBuilder()
                .clientId(clientId).tenantId(tenantId).clientSecret(clientSecret).authorityHost(AzureAuthorityHosts.AZURE_CHINA).build();

        if (null == scopes || null == credential) {
            throw new Exception("Unexpected error");
        }

        final GraphServiceClient graphClient = new GraphServiceClient(credential, scopes);
        RequestAdapter requestAdapter= graphClient.getRequestAdapter();
        requestAdapter.setBaseUrl("https://microsoftgraph.chinacloudapi.cn/v1.0");
        graphClient.setRequestAdapter(requestAdapter);
        com.microsoft.graph.users.item.sendmail.SendMailPostRequestBody sendMailPostRequestBody = new com.microsoft.graph.users.item.sendmail.SendMailPostRequestBody();
        Message message = new Message();
        message.setSubject("Meet for lunch?");
        ItemBody body = new ItemBody();
        body.setContentType(BodyType.Text);
        body.setContent("The new cafeteria is open.");
        message.setBody(body);
        LinkedList<Recipient> toRecipients = new LinkedList<Recipient>();
        Recipient recipient = new Recipient();
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setAddress("lvhoushuai@outlook.com");
        recipient.setEmailAddress(emailAddress);
        toRecipients.add(recipient);
        message.setToRecipients(toRecipients);
        LinkedList<Attachment> attachments = new LinkedList<Attachment>();
        FileInputStream fileInputStream=new FileInputStream(new File("./Test file send-2024-04-24 14:37:18.eml"));
        String fileName = "test.eml";
        FileAttachment attachment = new FileAttachment();
        attachment.setOdataType("#microsoft.graph.fileAttachment");
        attachment.setName(fileName);
        attachment.setContentType("text/plain");
        byte[] contentBytes = fileInputStream.readAllBytes();
        attachment.setContentBytes(contentBytes);
        attachments.add(attachment);
        message.setAttachments(attachments);
        sendMailPostRequestBody.setMessage(message);
        graphClient.users().byUserId("support@outlook.com").sendMail().post(sendMailPostRequestBody);
        System.out.println("ok");
//        java.util.List<String> scopes = Arrays.asList("https://microsoftgraph.chinacloudapi.cn/.default");
//        ClientSecretCredential credential = new ClientSecretCredentialBuilder()
//                .authorityHost("https://login.partner.microsoftonline.cn")
//                .clientId(clientId).tenantId(tenantId).clientSecret(secret).build();
//        if (null == scopes || null == credential) {
//            throw new Exception("Unexpected error");
//        }
//
//        TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(
//                scopes, credential);
//        GraphServiceClient<okhttp3.Request> graphClient = GraphServiceClient.builder()
//                .authenticationProvider(authProvider).buildClient();
//
//        // Specify the user principal name
//        String userPrincipalName = "sensesupport@outlook.com";
//        graphClient.setServiceRoot("https://microsoftgraph.chinacloudapi.cn/v1.0");
//
//        // Use the GraphServiceClient to get the user by user principal name
////
//        MessageCreateReplyAllParameterSet messageReplyAllParameterSet=new MessageCreateReplyAllParameterSet();
//        Message message=new Message();
//
//       java.util.List<Recipient> list= new ArrayList<>();
//        Recipient recipient=new Recipient();
//        EmailAddress emailAddress=new EmailAddress();
//        emailAddress.setAddress("tsxylhs@outlook.com");
//        emailAddress.setName("tsxylhs");
//        recipient.setEmailAddress(emailAddress);
//        list.add(recipient);
       // Message result1 = graphClient.users().byUserId("sensesupport@outlook.com").messages().byMessageId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAACm4Y28AAA=").get();
//        list.addAll(result1.getCcRecipients());
//        message.setCcRecipients(list);
//        messageReplyAllParameterSet.message=message;
//        java.util.List<Recipient> list= new ArrayList<>();
//        Recipient recipient=new Recipient();
//        EmailAddress emailAddress=new EmailAddress();
//        emailAddress.address="lvhoushuai@outlook.com";
//        emailAddress.name="lvhoushuai";
//        recipient.emailAddress=emailAddress;
//        list.add(recipient);
//        message.ccRecipients=list;
//        messageReplyAllParameterSet.comment="test";
       //Message message1= graphClient.users(userPrincipalName).messages().byId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAAChqd6IAAA=").createReplyAll(messageReplyAllParameterSet).buildRequest().post();
//       System.out.println(message1.body.content);
//       message1.ccRecipients.add(recipient);
//       UserSendMailParameterSet userSendMailParameterSet=new UserSendMailParameterSet();
//       userSendMailParameterSet.message=message1;
       // Message result = graphClient.users().byUserId("sensesupport@outlook.com").messages().byMessageId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAACm4Y28AAA=").patch(message);
//         Message message1=new Message();
//         ItemBody itemBody=new ItemBody();
//         itemBody.setContent("我回复的邮件内容");
//         itemBody.setContentType(BodyType.Text);
//         message1.setBody(itemBody);
//          //body.setMessage(message1);
//
//      MessageCollectionResponse messageCollectionResponse=  graphClient.users().byUserId("sensesupport@outlook.com").messages().get(requestConfiguration->{
//            requestConfiguration.queryParameters.top=5;
//            requestConfiguration.queryParameters.orderby=new String []{"receivedDateTime desc"};
//            String lastMessageDate= "2024-04-23T06:02:36.000Z";
//            StringBuilder stringBuilder=new StringBuilder("receivedDateTime gt ").append(lastMessageDate);
//            requestConfiguration.queryParameters.filter=stringBuilder.toString();
//
//
//        });
//     // graphClient.users().byUserId("").messages().byMessageId("").content().get();
//      List<Message> messageList=  messageCollectionResponse.getValue();
//        messageList.forEach(res->{
//
//        });
//        ReplyAllPostRequestBody replyAllPostRequestBody=new ReplyAllPostRequestBody();
//        replyAllPostRequestBody.setMessage(message1);
//        //replyAllPostRequestBody.setComment("我回复的邮件内容");
//        graphClient.users().byUserId("sensesupport@outlook.com").messages().byMessageId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAACm4Y28AAA=").replyAll().post(replyAllPostRequestBody);
//       // CreateReplyAllPostRequestBody createReplyAllPostRequestBody=new CreateReplyAllPostRequestBody();
//        //createReplyAllPostRequestBody.setMessage(message);
//       //graphClient.users().byUserId("sensesupport@outlook.com").messages().byMessageId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAACm4Y25AAA=").createReplyAll().post(createReplyAllPostRequestBody);
////       graphClient.users().byUserId(userPrincipalName).messages().byMessageId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAAChqd6dAAA=").patch(message);
//      // graphClient.users(userPrincipalName).sendMail(userSendMailParameterSet).buildRequest().post();
////        User user = graphClient.users(userPrincipalName)
////                .buildRequest()
////                .get();
////         Get the user object ID
////        String objectId = user.id;
//        System.out.println("ok");

    }
    }
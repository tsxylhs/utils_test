package org.example;


import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.graph.users.item.authentication.AuthenticationRequestBuilder;
import com.microsoft.kiota.RequestAdapter;

public class EmailClientTest {
    public static void main(String[] args)  throws  Exception{
        final String  resourceId="https://microsoftgraph.chinacloudapi.cn/.default";
        final  String serviceRoot="https://microsoftgraph.chinacloudapi.cn/v1.0";
    /* ArrayList<String> scope = new ArrayList();
     scope.add(resourceId );
     ClientCredentialProvider authProvider = new ClientCredentialProvider(
             clientId,
             scope,
             clientSecret,
             tenantId,
             NationalCloud.China);
     IGraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider( authProvider ).buildClient();
     graphClient.setServiceRoot(serviceRoot);
     graphClient.me().calendar().getClient();
*/


     //graphClient.me().messages().byId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAAChqd6IAAA=").content().buildRequest().get();

//     final String clientId = "YOUR_CLIENT_ID";
//     final String tenantId = "YOUR_TENANT_ID";
//     final String clientSecret = "YOUR_CLIENT_SECRET";

// The client credentials flow requires that you request the
// /.default scope, and pre-configure your permissions on the
// app registration in Azure. An administrator must grant consent
// to those permissions beforehand.


// This is the incoming token to exchange using on-behalf-of flow
        final String[] scopes = new String[] { resourceId };

        final ClientSecretCredential credential = new ClientSecretCredentialBuilder()
                .clientId(clientId).tenantId(tenantId).clientSecret(clientSecret).build();

        if (null == scopes || null == credential) {
            throw new Exception("Unexpected error");
        }

         GraphServiceClient graphClient = new GraphServiceClient(credential, scopes);
       RequestAdapter requestAdapter= graphClient.getRequestAdapter();
       requestAdapter.setBaseUrl(serviceRoot);
        AuthenticationRequestBuilder authenticationRequestBuilder= graphClient.me().authentication();

       graphClient.setRequestAdapter(requestAdapter);

       String s=  graphClient.me().get().getAboutMe();

//        Message message = new Message();
//        LinkedList<Recipient> toRecipients = new LinkedList<>();
//        Recipient recipient = new Recipient();
//        EmailAddress emailAddress = new EmailAddress();
//        emailAddress.setAddress("sensesupport@sensetime.com");
//        emailAddress.setName("Samantha Booth");
//        recipient.setEmailAddress(emailAddress);
//        toRecipients.add(recipient);
//        Recipient recipient1 = new Recipient();
//        EmailAddress emailAddress1 = new EmailAddress();
//        emailAddress1.setAddress("sensesupport@sensetime.com");
//        emailAddress1.setName("Randi Welch");
//        recipient1.setEmailAddress(emailAddress1);
//        toRecipients.add(recipient1);
//        message.setToRecipients(toRecipients);
//        createReplyPostRequestBody.setMessage(message);
//        createReplyPostRequestBody.setComment("Samantha, Randi, would you name the group if the project is approved, please?");
//
//
//        Message result = graphClient.me().messages().byMessageId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAAChqd6IAAA=").createReply().post(createReplyPostRequestBody);
//        System.out.println("test");
//        com.microsoft.graph.users.item.messages.item.createreplyall.CreateReplyAllPostRequestBody createReplyAllPostRequestBody = new CreateReplyAllPostRequestBody();
//        createReplyAllPostRequestBody.setComment("test");
//        Message message= graphClientv1.me().messages().byMessageId("AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAAChqd6IAAA=").createReplyAll().post(null
//        );
//        List list=message.getCcRecipients();
//        list.add("xuezhumei@sensetime.com");
//        message.setBccRecipients(list);
//        graphClientv1.me().messages().byMessageId(message.getInternetMessageId()).patch(message);
//
//        com.microsoft.graph.users.item.messages.item.replyall.ReplyAllPostRequestBody replyAllPostRequestBody = new com.microsoft.graph.users.item.messages.item.replyall.ReplyAllPostRequestBody();
//        replyAllPostRequestBody.setComment("test");
//        graphClientv1.me().messages().byMessageId("{message-id}").replyAll().post(replyAllPostRequestBody);






    }
}

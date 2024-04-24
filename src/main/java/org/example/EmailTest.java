//package org.example;
//
//import com.azure.identity.ClientSecretCredential;
//import com.azure.identity.ClientSecretCredentialBuilder;
//import com.microsoft.graph.models.*;
//
//import okhttp3.Request;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.LinkedList;
//
////
////import com.azure.identity.ClientSecretCredential;
////import com.azure.identity.ClientSecretCredentialBuilder;
////import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
////import com.microsoft.graph.models.Message;
////import com.microsoft.graph.models.User;
////import com.microsoft.graph.requests.GraphServiceClient;
////import com.microsoft.graph.requests.MessageCollectionPage;
////import com.microsoft.graph.requests.UserRequestBuilder;
////
////import java.util.ArrayList;
////
//class EmailTest {
//        //
//
//        public static void main(String[] args) throws  Exception {
//
//                final String  resourceId="https://microsoftgraph.chinacloudapi.cn/.default";
//                String serviceRoot="https://microsoftgraph.chinacloudapi.cn/v1.0";
//
//                ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
//                        .clientId(clientId)
//                        .clientSecret(clientSecret)
//                        .tenantId(tenantId)
//                        .build();
//                final TokenCredentialAuthProvider tokenCredAuthProvider = new TokenCredentialAuthProvider(Arrays.asList(resourceId), clientSecretCredential);
//                System.out.println("First Step Reached. ");
//               //tokenCredAuthProvider.setCustomHosts(new String[]{serviceRoot});
//                GraphServiceClient<Request> graphClient = GraphServiceClient.builder().authenticationProvider(tokenCredAuthProvider).buildClient();
//                graphClient.setServiceRoot(serviceRoot);
//                test(graphClient,serviceRoot);
//        }
//
//        public static void test( GraphServiceClient graphClient,String serviceRoot) throws IOException {
//                // GraphServiceClient<Request> graphClient = GraphServiceClient.builder().authenticationProvider(authProvider).buildClient();
//
//                Message message = new Message();
//                message.subject = "Meet for lunch?";
//                ItemBody body = new ItemBody();
//                body.contentType = BodyType.TEXT;
//                body.content = "The new cafeteria is open.";
//                message.body = body;
//                LinkedList<Recipient> toRecipientsList = new LinkedList<>();
//                Recipient toRecipients = new Recipient();
//                EmailAddress emailAddress = new EmailAddress();
//                emailAddress.address = "tsxylhs@outlook.com";
//                toRecipients.emailAddress = emailAddress;
//                toRecipientsList.add(toRecipients);
//                message.toRecipients = toRecipientsList;
//                //构建附件
//                LinkedList<Attachment> attachmentsList = new LinkedList<Attachment>();
//                FileAttachment attachments = new FileAttachment();
//                attachments.name = "1111.txt";
//                attachments.oDataType = "#microsoft.graph.fileAttachment";
//                attachments.contentType = "text/plain";
//                attachments.contentBytes = Base64.getDecoder().decode("SGVsbG8gV29ybGQh");
//                attachmentsList.add(attachments);
//                AttachmentCollectionResponse attachmentCollectionResponse = new AttachmentCollectionResponse();
//                attachmentCollectionResponse.value = attachmentsList;
//
//                AttachmentCollectionPage attachmentCollectionPage = new AttachmentCollectionPage(attachmentCollectionResponse, null);
//                message.attachments = attachmentCollectionPage;
//                //以指定用户邮箱发送邮件
//                graphClient.setServiceRoot(serviceRoot);
//                graphClient.users("sensesupport@outlook.com")
//                        .sendMail(UserSendMailParameterSet.newBuilder().
//                                withMessage(message).
//                                withSaveToSentItems(null).build())
//                        .buildRequest()
//                        .post();
//
//        }
//}
//

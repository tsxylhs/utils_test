//package org.example;
//
//
///*import com.azure.identity.ClientSecretCredential;
//import com.azure.identity.ClientSecretCredentialBuilder;
//import com.microsoft.aad.msal4j.ClientCredentialFactory;
//import com.microsoft.aad.msal4j.ClientCredentialParameters;
//import com.microsoft.aad.msal4j.ConfidentialClientApplication;
//import com.microsoft.aad.msal4j.IAuthenticationResult;
//import com.microsoft.graph.authentication.IAuthenticationProvider;
//import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
//import com.microsoft.graph.models.*;
//import com.microsoft.graph.requests.*;
//import com.nimbusds.oauth2.sdk.http.HTTPResponse;
//import okhttp3.Request;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.time.LocalDateTime;*/
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.gson.JsonObject;
//import com.microsoft.graph.auth.enums.NationalCloud;
//import com.microsoft.graph.models.extensions.*;
//import com.microsoft.graph.auth.confidentialClient.ClientCredentialProvider;
//import com.microsoft.graph.models.generated.BodyType;
//import com.microsoft.graph.options.HeaderOption;
//import com.microsoft.graph.requests.extensions.*;
//import microsoft.exchange.webservices.data.core.PropertySet;
//import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
//import org.apache.commons.io.IOUtils;
//
//import javax.swing.text.html.Option;
//
//class GraphLoad {
//
//    private static String clientId = "31618fcd-333a-4dd8-b227-a894bc559885";
//
//   public static IGraphServiceClient graphClient = null;
//    private static String clientSecret="xZ61r-.33VmPu-jHxmLJ571ALbjw8.XE6_";
//    private static String grantType = "client_credentials";
//    private static String tokenEndpoint = "https://login.partner.microsoftonline.cn/a07a5f5d-2291-4551-9c0e-5b98a8a5dffd/oauth2/v2.0/token";
//    private static String resourceId = "https://microsoftgraph.chinacloudapi.cn/.default";
//    private static String teantId = "a07a5f5d-2291-4551-9c0e-5b98a8a5dffd";
//    private static String mailbox = "abg.products@outlook.com";
//
//    public static void main(String[] args) throws InterruptedException, IOException {
//        getClient();
//        IMessageCollectionPage messages = GraphLoad.listMessages(graphClient, mailbox);
//        while (messages.getNextPage() !=null){
//            Thread.sleep(2000);
//            //TODO 获取所有邮件
//            //messages= messages.getNextPage().buildRequest().get();
//            //TODO 获取最新10条
//            messages = GraphLoad.listMessages(graphClient, mailbox);
//            for (Message message : messages.getCurrentPage()){
//                System.out.println("Id--->" + message.id);
//                InputStream stream = graphClient.me().messages(message.id).content()
//                        .buildRequest()
//                        .get();
//                String subject = message.subject;
//                System.out.println(subject);
//                String bodyContent = message.body.content;
//                System.out.println(bodyContent);
//                StringBuilder stringBuilder = new StringBuilder("D:\\平台\\开发\\").append(subject.replaceAll("/|:","")).append(".eml");
//                FileOutputStream fs = new FileOutputStream(stringBuilder.toString());
//                //fs.write(bodyContent.getBytes(StandardCharsets.UTF_8), 0, bodyContent.length());
//                fs.write(IOUtils.toByteArray(stream));
//                fs.close();
//               return;
//            }
//        }
//        //TODO 发送邮件
//       // test(graphClient);
//
//       /* for (Message message : messages.getCurrentPage()) {
//            //System.out.println("Id--->" + message.id);
//            String senderName = message.from.emailAddress.name;
//            String senderAddress = message.from.emailAddress.address;
//            String subject = message.subject;
//           *//* String importance = message.importance.name();
//            String webLink = message.webLink;
//            String bodyContent = message.body.content;*//*
//
//          *//*  LocalDateTime receivedDateTime = message.receivedDateTime.toLocalDateTime();
//            LocalDateTime sentDateTime = message.sentDateTime.toLocalDateTime();
//            List<Recipient> toRecipients = message.toRecipients;
//            System.out.println(toRecipients.listIterator().next().emailAddress.address);*//*
//
//        }*/
//    }
//    public static void getClient()
//    {
//        if (graphClient == null) {
//            try {
//                ArrayList<String> scope = new ArrayList();
//                scope.add( resourceId );
//
//                ClientCredentialProvider authProvider = new ClientCredentialProvider(
//                        clientId,
//                        scope,
//                        clientSecret,
//                        teantId,
//                        NationalCloud.China);
//
//                graphClient = GraphServiceClient.builder().authenticationProvider( authProvider ).buildClient();
//                graphClient.setServiceRoot( "https://microsoftgraph.chinacloudapi.cn/v1.0" );
//            }
//            catch (Exception e)
//            {
//                throw new Error("Could not create a graph client: " + e.getLocalizedMessage());
//
//            }
//
//        }
//    }
//
//    public static IMessageCollectionPage listMessages(IGraphServiceClient graphClient, String mailbox)  {
//      /*  LinkedList<HeaderOption> requestOptions = new LinkedList<HeaderOption>();
//        requestOptions.add(new HeaderOption("Prefer", "outlook.body-content-type=\"text\""));*/
//        IMessageCollectionPage messages = graphClient.users(mailbox).messages()
//                .buildRequest().top(1)
//                .get();
//        return messages;
//    }
//
//    public static void test(IGraphServiceClient iGraphServiceClient){
//        Message message = new Message();
//        message.subject = "Meet for lunch?";
//        ItemBody body = new ItemBody();
//        body.contentType = BodyType.TEXT;
//        body.content = "The new cafeteria is open.";
//        message.body = body;
//        LinkedList<Recipient> toRecipientsList = new LinkedList<Recipient>();
//        Recipient toRecipients = new Recipient();
//        EmailAddress emailAddress = new EmailAddress();
//        emailAddress.address = "xiangjianbo_vendor@outlook.com";
//        toRecipients.emailAddress = emailAddress;
//        toRecipientsList.add(toRecipients);
//        message.toRecipients = toRecipientsList;
//        //构建附件
//        LinkedList<Attachment> attachmentsList = new LinkedList<Attachment>();
//        FileAttachment attachments = new FileAttachment();
//        attachments.name = "1111.txt";
//        attachments.oDataType = "#microsoft.graph.fileAttachment";
//        attachments.contentType="text/plain";
//        attachments.contentBytes = Base64.getDecoder().decode("SGVsbG8gV29ybGQh");
//        attachmentsList.add(attachments);
//        AttachmentCollectionResponse attachmentCollectionResponse = new AttachmentCollectionResponse();
//        attachmentCollectionResponse.value = attachmentsList;
//
//        AttachmentCollectionPage attachmentCollectionPage = new AttachmentCollectionPage(attachmentCollectionResponse, null);
//        message.attachments = attachmentCollectionPage;
//        //以指定用户邮箱发送邮件
//        iGraphServiceClient.users("abg.products@outlook.com")
//                .sendMail(message,false)
//                .buildRequest()
//                .post();
//
//    }
//
//
//
//}
//
//
//

//package org.example;
//
//import com.azure.identity.ClientSecretCredential;
//import com.azure.identity.ClientSecretCredentialBuilder;
//import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
//import com.microsoft.graph.models.Message;
//import com.microsoft.graph.models.User;
//import com.microsoft.graph.requests.GraphServiceClient;
//import com.microsoft.graph.requests.MessageCollectionPage;
//import com.microsoft.graph.requests.UserRequestBuilder;
//
//import java.util.ArrayList;
//
//class EmailTest {
//
//   private static String tenantId = "a07a5f5d-2291-4551-9c0e-5b98a8a5dffd";
//   private static String cltId = "31618fcd-333a-4dd8-b227-a894bc559885";
//  private static String secret = "xZ61r-.33VmPu-jHxmLJ571ALbjw8.XE6_";
//
//    public static void main(String[] args) {
//        // 客户端ID、客户端机密和租户ID
//        String clientId = "31618fcd-333a-4dd8-b227-a894bc559885";
//        String clientSecret = "xZ61r-.33VmPu-jHxmLJ571ALbjw8.XE6_";
//        String tenantId = "a07a5f5d-2291-4551-9c0e-5b98a8a5dffd";
//
//        // 创建Microsoft Graph客户端
//
//        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .tenantId(tenantId)
//                .build();
//      ArrayList l=  new ArrayList<String>();
//        l.add("https://microsoftgraph.chinacloudapi.cn/.default");
//
//        TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(
//                clientSecretCredential
//        );
//
//        GraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider(authProvider).buildClient();
//        graphClient.setServiceRoot("https://microsoftgraph.chinacloudapi.cn/v1.0/");
//        try {
//            // 验证邮箱
//            UserRequestBuilder userRequestBuilder = graphClient.me();
//            User user = userRequestBuilder.buildRequest().get();
//
//            System.out.println("用户姓名: " + user.displayName);
//            System.out.println("用户邮箱: " + user.mail);
//            System.out.println("---------------------");
//
//            // 获取邮件
//            MessageCollectionPage messages = graphClient.me().messages().buildRequest().get();
//            for (Message message : messages.getCurrentPage()) {
//                System.out.println("主题: " + message.subject);
//                System.out.println("发件人: " + message.sender.emailAddress.address);
//                System.out.println("正文: " + message.body.content);
//                System.out.println("---------------------");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
////    private static String authority = "https://login.chinacloudapi.cn/a07a5f5d-2291-4551-9c0e-5b98a8a5dffd/oauth2/token";
////    private static String tenantId = "a07a5f5d-2291-4551-9c0e-5b98a8a5dffd";
////    private static String clientId = "31618fcd-333a-4dd8-b227-a894bc559885";
////    private static String secret = "xZ61r-.33VmPu-jHxmLJ571ALbjw8.XE6_";
////    private static String accessToken = "";
////    private static String mailbox = "abg.products@sensetime.com";
////
////    private static List<String> scope = Arrays.asList("https://microsoftgraph.chinacloudapi.cn/.default".split(","));
////
////    private static final ClientSecretCredential clientSecretCredential = null;
////
////    private static final TokenCredentialAuthProvider tokenCredAuthProvider = null;
////
////    private static GraphServiceClient graphClient = null;
////
////    public static void main(String args[]) throws Exception {
////       try{
////           getAccessToken();
////        MessageCollectionPage messages = EmailTest.listMessages(graphClient, mailbox);
////
////        for (Message message:messages.getCurrentPage()){
////            System.out.println("Id--->"+message.id);
////            String senderName = message.from.emailAddress.name;
////            String senderAddress = message.from.emailAddress.address;
////            String subject = message.subject;
////            String importance = message.importance.name();
////            String webLink = message.webLink;
////            String bodyContent = message.body.content;
////            LocalDateTime receivedDateTime = message.receivedDateTime.toLocalDateTime();
////            LocalDateTime sentDateTime = message.sentDateTime.toLocalDateTime();
////            List<Recipient> toRecipients  = message.toRecipients;
////            System.out.println(toRecipients.listIterator().next().emailAddress.address);
////
////        }
////    } catch (Exception ex) {
////        System.out.println("Exception message - " + ex.getMessage());
////        throw ex;
////    }
////}
////
////    public static Message getMessageById(GraphServiceClient graphClient,String mailbox,String mailId) throws IOException {
////        Message message = graphClient.users(mailbox).messages(mailId)
////                .buildRequest()
////                .get();
////        return message;
////    }
////
////    public static String getAccessToken() throws Exception {
////        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder().clientId(clientId)
////                .tenantId(tenantId).clientSecret(secret).build();
////
////        TokenCredentialAuthProvider tokenCredAuthProvider = new TokenCredentialAuthProvider(scope,
////                clientSecretCredential);
////
////         graphClient = GraphServiceClient.builder().authenticationProvider(tokenCredAuthProvider)
////                .buildClient();
////
////        IAuthenticationResult result = getAccessTokenByClientCredentialGrant();
////        return accessToken = result.accessToken();
////
////    }
////
////    public static MessageCollectionPage listMessages(GraphServiceClient graphClient,String mailbox) throws IOException {
////        MessageCollectionPage messages = graphClient.users(mailbox).messages()
////                .buildRequest()
////                .get();
////        return messages;
////    }
////
////    public static IAuthenticationResult getAccessTokenByClientCredentialGrant() throws Exception {
////
////        ConfidentialClientApplication app = ConfidentialClientApplication.builder(
////                        clientId,
////                        ClientCredentialFactory.createFromSecret(secret))
////                .authority(authority)
////                .build();
////
////        // With client credentials flows the scope is ALWAYS of the shape "resource/.default", as the
////        // application permissions need to be set statically (in the portal), and then granted by a tenant administrator
////        ClientCredentialParameters clientCredentialParam = ClientCredentialParameters.builder(
////                        Collections.singleton(scope.toString()))
////                .build();
////
////        CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParam);
////        return future.get();
////
////    }
//
//}
//

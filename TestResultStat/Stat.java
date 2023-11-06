import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Stat {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        System.out.println("Hello!");

        String user = "yvhffapnyurtaug2izxtw4omjvb7ng7h6tk334hgc4zpjklenvfq";

        String userEncoded = Base64.getEncoder().encodeToString(user.getBytes());
        String strUrl = "https://ittfs.avp.ru/tfs/DefaultCollection/KORM/_apis/test/runs?minLastUpdatedDate=2023-11-04&maxLastUpdatedDate=2023-11-07T01:01:01&api-version=6.0";
        
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());


        HttpClient httpClient = HttpClient
            .newBuilder()
            .sslContext(sslContext)
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, "123".toCharArray());
                }
            })
            .build();
        
        //objectXXX.header("Authorization", "Basic " + authStringEnc);
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(new URI(strUrl))
            .build();

        System.out.println(userEncoded);

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        System.out.println("Result: " + response.statusCode());
        //System.out.println(response.body());
    }

}

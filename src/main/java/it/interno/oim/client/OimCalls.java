package it.interno.oim.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public abstract class OimCalls {

    public static ResponseEntity<Object> exchange(String url, String auth, HttpMethod method, HttpHeaders headers) {

        headers.add("Content-Type", "application/scim+json");
        headers.add("X-Requested-By", "12");
        headers.add("Accept", "*/*");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Authorization", auth);

        return new RestTemplate().exchange(url, method, new HttpEntity<>(headers), Object.class);
    }

    public static ResponseEntity<Object> exchange(String url, String auth, HttpMethod method, Object body) {
        return new RestTemplate().exchange(url, method, new HttpEntity<>(body), Object.class);
    }

    public static ResponseEntity<Object> exchange(String url, String auth, HttpMethod method, HttpHeaders headers, Object body) {

        headers.add("Content-Type", "application/scim+json");
        headers.add("X-Requested-By", "12");
        headers.add("Accept", "*/*");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Authorization", auth);

        return new RestTemplate().exchange(url, method, new HttpEntity<>(body, headers), Object.class);
    }

    public static Object patch(String url, String auth, HttpHeaders headers, Object body){

        headers.add("Content-Type", "application/scim+json");
        headers.add("X-Requested-By", "12");
        headers.add("Accept", "*/*");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Authorization", auth);

        RestTemplate rt = new RestTemplate();
        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return rt.patchForObject(url, new HttpEntity<>(body, headers), Object.class);
    }

}

package springcloud.controller;

import com.netflix.zuul.context.RequestContext;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by 10326 on 2020/1/17.
 * Zuul的回退机制
 */
@Component
public class ZuulFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String s, Throwable throwable) {
        return new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                RequestContext ctx = RequestContext.getCurrentContext();
                Throwable throwable1 = ctx.getThrowable();
                if(throwable1 != null){
                    System.out.println(throwable1.getCause());
                }
                return  new ByteArrayInputStream("服务器内部错误".getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }
        };
    }
}

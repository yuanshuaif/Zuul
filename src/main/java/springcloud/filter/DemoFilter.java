package springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 10326 on 2019/7/27.
 */

public class DemoFilter extends ZuulFilter{
    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
         pre：路由之前
         route：路由之时
         post： 路由之后
         error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序 数字越小级别越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，***********true:使用该过滤器过滤*************
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑，这里只是将请求的URL简单些到日志中
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        /**
         * RequestContext 的原理用的就是ThreadLocal
         */
        String str = null;
        str.length();
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("param", "requestParam");
        HttpServletRequest request = ctx.getRequest();
        String s = String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString());
        System.out.println(s);
        /**
         * 请求是否继续路由，该请求是否被过滤掉
         */
        if(s.contains("GET")){
            ctx.setSendZuulResponse(true);//通过
        }else {
            ctx.setSendZuulResponse(false);//不通过
        }
        return null;
    }
}

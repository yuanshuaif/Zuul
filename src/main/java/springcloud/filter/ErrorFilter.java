package springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 10326 on 2019/7/27.
 */

public class ErrorFilter extends ZuulFilter{
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
        return "error";
    }

    /**
     * filterOrder：过滤的顺序 数字越小级别越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 100;
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
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        System.out.println(ctx.get("param") + "--------------" + throwable.getMessage());
        return null;
    }
}

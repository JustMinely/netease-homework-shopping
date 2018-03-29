import base.SpringTestUnit;
import com.springmvc.common.utils.GsonUtils;
import com.springmvc.export.request.PublishReq;
import com.springmvc.export.response.Result;
import com.springmvc.service.PublishService;
import org.apache.ibatis.annotations.Results;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by qudi on 2018/3/29.
 */
public class PublishServiceTest extends SpringTestUnit {
    @Resource(name = "publishService")
    private PublishService publishService;

    @Test
    public void getProductOfMerchantTest() {
        PublishReq publishReq = new PublishReq();
        publishReq.setMerchantId(1L);
        Result result = publishService.getProductsOfMerchant(publishReq);
        LOGGER.info("getProductOfMerchantTest...result is {}", GsonUtils.toJSONString(result));
    }
}

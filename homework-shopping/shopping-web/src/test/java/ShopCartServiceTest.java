import base.SpringTestUnit;
import com.springmvc.common.utils.GsonUtils;
import com.springmvc.export.request.ShopCartReq;
import com.springmvc.export.response.Result;
import com.springmvc.export.response.ShopCartResp;
import com.springmvc.service.ShopCartService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qudi on 2018/3/5.
 */
public class ShopCartServiceTest extends SpringTestUnit {
    @Resource(name = "shopCartService")
    private ShopCartService shopCartService;

    @Test
    public void addProduct2ShopCartTest() {
        ShopCartReq req = new ShopCartReq();
        req.setCustomerId(1L);
//        req.setProductId(4L);
//        req.setProductId(2L);
        req.setProductId(3L);
        req.setProductQuantity(3L);
        shopCartService.addOrUpdateProduct2ShopCart(req);
    }

    @Test
    public void findProductsByCustomerId() {
        ShopCartReq req = new ShopCartReq();
        req.setCustomerId(1L);
        List<ShopCartResp> shopCartRespList = shopCartService.findProductsByCustomerId(req);
        LOGGER.info("result is \n {}", GsonUtils.toJSONString(shopCartRespList));
    }

    @Test
    public void deleteProductFromShopCartTest() {
        ShopCartReq req = new ShopCartReq();
        req.setCustomerId(1L);
        req.setProductId(2L);
        Result result = shopCartService.deleteProductFromShopCart(req);
        LOGGER.info("result is {}", GsonUtils.toJSONString(result));


    }
}

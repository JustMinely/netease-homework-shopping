import base.SpringTestUnit;
import com.springmvc.common.utils.GsonUtils;
import com.springmvc.domain.po.Product;
import com.springmvc.export.request.ProductReq;
import com.springmvc.export.response.Result;
import com.springmvc.service.ProductService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * productService 单元测试
 * Created by qudi on 2018/3/2.
 */
public class ProductServiceTest extends SpringTestUnit {
    @Resource(name = "productService")
    private ProductService productService;

    @Test
    public void addProductTest() {
        ProductReq req = new ProductReq();
        req.setMerchantId(1L);
        req.setProductName("iPhone7");
        req.setProductPrice("6000");
        req.setProductAmount("100");
        req.setProductDesc("苹果手机");
        req.setProductContent("苹果手机，高端大气上档次");
        req.setProductUrl("http://www.chinairn.com/UserFiles/image/20161229/20161229103748_3222.jpg");

        try {
            Result result = productService.addProduct(req);
            LOGGER.info("addProductTest...req = {}\n  result is {}", GsonUtils.toJSONString(req), GsonUtils.toJSONString(result));
        } catch (Exception e) {
            LOGGER.error("addProductTest...fail", e);
        }
    }

    @Test
    public void getAllProductsTest() {
        List<Product> allProducts = productService.getAllProducts();
        LOGGER.info("getAllProductsTest...result is: {}", GsonUtils.toJSONString(allProducts));
    }

    @Test
    public void getAllUnPurchaseProductsTest() {
        List<Product> allUnPurchasedProducts = productService.getAllUnPurchasedProducts();
        LOGGER.info("getAllUnPurchaseProductsTest...result is:{}", GsonUtils.toJSONString(allUnPurchasedProducts));
    }

    @Test
    public void removeProduct() {
        ProductReq req = new ProductReq();
        req.setMerchantId(1L);
        req.setId(5L);
        Result result = productService.deleteUnPurcharseProduct(req);
        LOGGER.info("removeProduct...result is {}", GsonUtils.toJSONString(result));

    }
    @Test
    public void purchaseProductTest(){
        ProductReq req = new ProductReq();
        req.setId(3L);
        req.setProductNum(1L);
        Result result = productService.purchaseProduct(req);
        LOGGER.info("purchaseProductTest..result is \n{}",GsonUtils.toJSONString(result));
    }


}

import base.SpringTestUnit;
import com.springmvc.common.utils.GsonUtils;
import com.springmvc.domain.po.Customer;
import com.springmvc.service.CustomerService;
import org.junit.Test;


import javax.annotation.Resource;

/**
 * Created by qudi on 2018/2/19.
 */
public class CustomerServiceTest extends SpringTestUnit {
    @Resource(name = "customerService")
    private CustomerService customerService;

    @Test
    public void getCustomerByIdTest(){
        Customer result;
        try {
            result = customerService.getCustomerById(1L);
            LOGGER.info(GsonUtils.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

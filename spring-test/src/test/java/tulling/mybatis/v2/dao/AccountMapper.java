package tulling.mybatis.v2.dao;

import tulling.mybatis.v2.anno.TulingSelect;
import tulling.mybatis.v2.entity.AccountInfo;
import org.springframework.stereotype.Component;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: smlz
 * @date 2020/5/4 16:44
 */

public interface AccountMapper {

	@TulingSelect(value = "select * from account_info where account_id=?")
	AccountInfo qryAccount(Integer accountId);
}

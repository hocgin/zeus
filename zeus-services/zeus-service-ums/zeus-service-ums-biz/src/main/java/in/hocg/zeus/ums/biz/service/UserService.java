package in.hocg.zeus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.api.pojo.ro.CreateAccountRo;
import in.hocg.zeus.ums.api.pojo.vo.AccountVo;
import in.hocg.zeus.ums.api.pojo.vo.UserDetailVo;
import in.hocg.zeus.ums.biz.entity.User;
import in.hocg.zeus.ums.biz.pojo.ro.RoleGrantUserRo;
import in.hocg.zeus.ums.biz.pojo.ro.UpdateAccountEmailRo;
import in.hocg.zeus.ums.biz.pojo.ro.UpdateAccountPhoneRo;
import in.hocg.zeus.ums.biz.pojo.ro.UpdateAccountRo;
import in.hocg.zeus.ums.biz.pojo.ro.UserCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.UserPagingRo;
import in.hocg.zeus.ums.biz.pojo.vo.AccountComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [用户模块] 账号表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
public interface UserService extends AbstractService<User> {

    UserDetailVo createAccount(CreateAccountRo ro);

    UserDetailVo getUserDetailVoByUsername(String username);

    UserDetailVo getUserDetailVoByUsernameOrEmailOrPhone(String unique);

    Optional<User> getAccountByUsernameOrEmailOrPhone(String unique);

    List<User> listAccountById(List<Long> values);

    String getToken(String username);

    String getUsername(String token);

    UserDetailVo getUserByPhone(String phone);

    List<AccountVo> listAccountVoById(List<Long> id);

    AccountVo getAccountVoById(Long userId);

    AccountComplexVo getComplexById(Long userId);

    Long updateAccount(Long userId, UpdateAccountRo ro);

    Long updatePhone(UpdateAccountPhoneRo ro);

    Long updateEmail(UpdateAccountEmailRo ro);

    Optional<User> getByUsername(String username);

    List<AuthorityTreeNodeVo> listTreeCurrentAuthority(String projectSn, Long userId);

    List<String> listCurrentAuthorityCode(String projectSn, Long userId);

    IPage<AccountComplexVo> paging(UserPagingRo ro);

    List<AccountComplexVo> complete(UserCompleteRo ro);

    void grantRole(Long userId, RoleGrantUserRo ro);

    AccountComplexVo getComplex(Long id);
}

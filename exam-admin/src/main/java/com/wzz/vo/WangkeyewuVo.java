package com.wzz.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/19 13:53
 *
 *  这个是查询用户下的业务和 银行 对应的业务
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WangkeyewuVo {

    private String username; //登录的用户

    private String threevalue; //角色

    private String zhihangvalue; //支行区域

    private String yewuQuBinkname; //业务名称
}

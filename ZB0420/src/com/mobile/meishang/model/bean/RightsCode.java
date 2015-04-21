package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class RightsCode extends Head {
	// {
	// id // id
	// code //权益号
	// type //权益类型(1：串码 2：测试码 3：银行卡 4：二维码)
	// buyerCode //权益采购商编码
	// buyerType //采购商类型(1：银行 2：商户 3：伯乔4：第三方渠道)
	// startTime //权益开始日期
	// endTime //权益结束日期
	// expiredTime //权益失效日期
	// experedInterval //权益失效时间间隔
	// bcBalanceInit //期初余额
	// bcBalance //余额
	// limitType // 1：不可红字 2：可现金补差
	// archiveType // 1：一次使用 2：余额归零回收 3：不归档
	// userid //持有者ID
	// actids //活动IDS
	// goodsids//商品IDS
	// status //状态(0：不可用 1：可用 2：已发送 3：已使用完 4：已失效)
	// createdTime //创建时间
	// updatedTime //使用时间
	// desp //权益描述
	// isDeleted //逻辑删除
	// reamrk1 //备用字段1
	// remark2 //备用字段2
	// remark3 //备用字段3
	// bankCode //银行编号
	// bankSeq //银行流水
	// }

	private String rcode;
	private String bankCode;
	private String bankSeq;
	private String callBackUrl;
	private int status;

	public RightsCode() {
	}

	public RightsCode(JSONObject json) throws JSONException {
		super(json);
		JSONObject body = getJsonObject(json, "body");
		callBackUrl = getJsonStrValue(body, "callBackUrl");
		JSONObject rightInfo = getJsonObject(body, "rightInfo");
		rcode = getJsonStrValue(rightInfo, "code");
		bankCode = getJsonStrValue(rightInfo, "bankCode");
		bankSeq = getJsonStrValue(rightInfo, "bankSeq");
		status = getJsonIntValue(rightInfo, "status");
	}

	public String getRcode() {
		return rcode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public String getBankSeq() {
		return bankSeq;
	}

	public String getcallBackUrl() {
		return callBackUrl;
	}

	public int getStatus() {
		return status;
	}

}

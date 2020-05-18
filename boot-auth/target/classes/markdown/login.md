# 我的:权限:登录

## 我的:权限:登录


**接口地址**:`/auth/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`application/json`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResult«CustomToken»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|count|适用于分页查询返回数据总量|integer(int64)|integer(int64)|
|data|响应数据|CustomToken|CustomToken|
|&emsp;&emsp;access_token|登录的凭证，请求其他需要登录权限的接口时规则：请求头参数：Authorization，其值为：type + access_token|string||
|&emsp;&emsp;expires_in|凭证过期时间，用户进入app时间判断token过期前1天(不固定)可刷新的token|string(date-time)||
|&emsp;&emsp;login_in|登录时间|string(date-time)||
|&emsp;&emsp;type|类型|string||
|message|响应消息|string||
|status|响应码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"count": 0,
	"data": {
		"access_token": "",
		"expires_in": "",
		"login_in": "",
		"type": ""
	},
	"message": "操作成功",
	"status": 200
}
```

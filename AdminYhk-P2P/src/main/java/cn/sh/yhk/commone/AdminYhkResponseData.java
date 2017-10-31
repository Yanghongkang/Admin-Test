package cn.sh.yhk.commone;

public class AdminYhkResponseData {

	private boolean success;
	private String errCode;
	private String errInfo;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public AdminYhkResponseData() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AdminYhkResponseData [success=" + success + ", errCode=" + errCode + ", errInfo=" + errInfo
				+ ", message=" + message + "]";
	}

}

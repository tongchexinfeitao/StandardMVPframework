package com.ali.framework.model.bean;

public class LoginBean {

    /**
     * result : {"headPic":"http://172.17.8.100/images/small/head_pic/2018-11-21/20181121100733.jpg","nickName":"OP_8mY65","phone":"16619958760","sessionId":"154276714558512","sex":1,"userId":12}
     * message : 登录成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * headPic : http://172.17.8.100/images/small/head_pic/2018-11-21/20181121100733.jpg
         * nickName : OP_8mY65
         * phone : 16619958760
         * sessionId : 154276714558512
         * sex : 1
         * userId : 12
         */

        private String headPic;
        private String nickName;
        private String phone;
        private String sessionId;
        private int sex;
        private int userId;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "headPic='" + headPic + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", phone='" + phone + '\'' +
                    ", sessionId='" + sessionId + '\'' +
                    ", sex=" + sex +
                    ", userId=" + userId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

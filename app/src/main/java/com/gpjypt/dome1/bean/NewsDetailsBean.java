package com.gpjypt.dome1.bean;

import java.io.Serializable;

public class NewsDetailsBean implements Serializable {

    /**
     * uniquekey : 4bc2b0c9bf0177abf1dc22befbbafcb4
     * detail : {"title":"休息休假、加班工资\u2026\u2026本期节目与您交流这些话题","date":"2021-07-05 15:49:00","category":"国内","author_name":"胶东头条","url":"https://mini.eastday.com/mobile/210705154920763571091.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210705/20210705154920_8e74b34273e1962b83b3606070571486_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"","thumbnail_pic_s03":""}
     * content : <p >7月6日早8:00至8:30，烟台广播电视台《民生热线》节目将邀请市劳动人事争议调解仲裁院的嘉宾做客直播间，就休息休假、加班工资等话题与市民互动交流，欢迎拨打热线电话6251234参与节目。</p><p >您可以通过FM101烟台综合广播、烟台时刻APP、烟台广播视频号、烟台爆料视频号同步收听收看节目。</p><p >您也可以扫描下方二维码，在线提交问题，我们将第一时间反馈给上线单位。 ↓</p><p >
     <img width='100%' src='https://dfzximg01.dftoutiao.com/news/20210705/20210705154920_8e74b34273e1962b83b3606070571486_1.jpeg' data-weight='1080' data-width='1080' data-height='1230'></p><p >来源：胶东头条</p>
     */

    private String uniquekey;
    private DetailBean detail;
    private String content;

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class DetailBean implements Serializable {
        /**
         * title : 休息休假、加班工资……本期节目与您交流这些话题
         * date : 2021-07-05 15:49:00
         * category : 国内
         * author_name : 胶东头条
         * url : https://mini.eastday.com/mobile/210705154920763571091.html
         * thumbnail_pic_s : https://dfzximg02.dftoutiao.com/news/20210705/20210705154920_8e74b34273e1962b83b3606070571486_1_mwpm_03201609.jpeg
         * thumbnail_pic_s02 :
         * thumbnail_pic_s03 :
         */

        private String title;
        private String date;
        private String category;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }

        private String author_name;
        private String url;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;
    }
}

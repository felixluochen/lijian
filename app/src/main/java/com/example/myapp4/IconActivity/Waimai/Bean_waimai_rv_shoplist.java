package com.example.myapp4.IconActivity.Waimai;

import java.util.List;

public class Bean_waimai_rv_shoplist {
    private String total;
    private List<Rows> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    public class Rows {
        private String id;
        private String name;
        private String address;
        private String introduction;
        private String themeId;
        private String score;
        private String saleQuantity;
        private String deliveryTime;
        private String imgUrl;
        private String avgCost;
        private String other;
        private String recommend;
        private String distance;
        private String saleNum3hour;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getThemeId() {
            return themeId;
        }

        public void setThemeId(String themeId) {
            this.themeId = themeId;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(String saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(String avgCost) {
            this.avgCost = avgCost;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getSaleNum3hour() {
            return saleNum3hour;
        }

        public void setSaleNum3hour(String saleNum3hour) {
            this.saleNum3hour = saleNum3hour;
        }
    }


}

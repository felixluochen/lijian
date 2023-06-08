package com.example.mytext2.banner;

import java.util.List;

public class Bean_banner {
    private List<Rows> rows;

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    public class Rows {
        private String advImg;

        public String getAdvImg() {
            return advImg;
        }

        public void setAdvImg(String advImg) {
            this.advImg = advImg;
        }
    }
}

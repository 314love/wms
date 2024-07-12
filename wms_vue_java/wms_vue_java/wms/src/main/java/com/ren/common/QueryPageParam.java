package com.ren.common;

import lombok.Data;

import java.util.HashMap;
@Data
public class QueryPageParam {
    private static int  PAGE_SIZE=10;
    private static int PAGE_CURRENT=1;
    private int pageCurrent=PAGE_CURRENT;
    private int pageSize=PAGE_SIZE;
    private HashMap param=new HashMap();
}
